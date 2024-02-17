package com.shop.soldphonev2.api.brand.web;

import com.shop.soldphonev2.api.brand.BrandService;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BrandController {
    private final BrandService brandService;
    @PostMapping("")
    BaseResponseMessage<?> created(@RequestBody BrandDto brandDto){
        return brandService.insertBrand(brandDto);
    }
    @GetMapping("")
    BaseResponseMessage<?> select(){
        return brandService.selectAllBrands();
    }
    @GetMapping("/{uuid}")
    BaseResponseMessage<?> selectByUuid(@PathVariable String uuid){
        return brandService.selectBrandByUuid(uuid);
    }
    @PutMapping("/{uuid}")
    BaseResponseMessage<?> update(@RequestBody BrandDto brandDto,@PathVariable String uuid){
        return brandService.update(brandDto,uuid);
    }
    @DeleteMapping("/{uuid}")
    BaseResponseMessage<?> delete(@PathVariable String uuid){
        return brandService.delete(uuid);
    }
}
