package com.shop.soldphonev2.api.method.web;

import com.shop.soldphonev2.api.method.MethodService;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/methods")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MethodController {
    private final MethodService methodService;
    @PostMapping("")
    BaseResponseMessage<?> create(@RequestBody MethodDto methodDto){
        return methodService.insertMethod(methodDto);
    }
    @GetMapping("")
    BaseResponseMessage<?> selects(){
        return methodService.selectAllMethods();
    }
    @GetMapping("/{uuid}")
    BaseResponseMessage<?> selectByUuid(@PathVariable String uuid){
        return methodService.selectMethodByUuid(uuid);
    }
    @PutMapping("/{uuid}")
    BaseResponseMessage<?> update(@RequestBody MethodDto methodDto,@PathVariable String uuid){
        return methodService.update(methodDto,uuid);
    }
    @DeleteMapping("/{uuid}")
    BaseResponseMessage<?> delete(@PathVariable String uuid){
        return methodService.delete(uuid);
    }
    @GetMapping("/method-type/{id}")
    BaseResponseMessage<?> selectType(@PathVariable Integer id){
        return methodService.selectMethodByType(id);
    }
}
