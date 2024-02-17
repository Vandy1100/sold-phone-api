package com.shop.soldphonev2.api.model.web;


import com.shop.soldphonev2.api.model.ModelService;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/models")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ModelController {

    private final ModelService modelService;
    @PostMapping("")
    BaseResponseMessage<?> create(@RequestBody ModelDto modelDto){
        return modelService.insertModel(modelDto);
    }
    @GetMapping("")
    BaseResponseMessage<?> selectAll(){
        return modelService.selectAllModel();
    }
}
