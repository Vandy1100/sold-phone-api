package com.shop.soldphonev2.api.product.web;

import com.shop.soldphonev2.api.product.ProductService;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private final ProductService productService;
    @PostMapping("")
    BaseResponseMessage<?> created(@RequestBody ProductDto productDto){
        return productService.insertProduct(productDto);
    }
    @GetMapping("")
    BaseResponseMessage<?> selectAll(){
        return productService.selectAllProducts();
    }
    @GetMapping("/{uuid}")
    BaseResponseMessage<?> selectUuid(@PathVariable String uuid){
        return productService.selectProductByUuid(uuid);
    }
    @PutMapping("/{uuid}")
    BaseResponseMessage<?> update(@RequestBody ProductDto productDto,@PathVariable String uuid){
        return productService.update(productDto,uuid);
    }
    @DeleteMapping("/{uuid}")
    BaseResponseMessage<?> delete(@PathVariable String uuid){
        return productService.delete(uuid);
    }
    @GetMapping("/recent")
    BaseResponseMessage<?> selectCard(){
        return productService.selectAllProductCard();
    }

}
