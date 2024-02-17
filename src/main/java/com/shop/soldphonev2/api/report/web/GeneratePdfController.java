package com.shop.soldphonev2.api.report.web;

import com.shop.soldphonev2.api.report.GeneratePdfServiceImp;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/reports")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class GeneratePdfController {
    public final GeneratePdfServiceImp generatePdfServiceImp;
    @PostMapping("")
    BaseResponseMessage<?> generatePdf(@RequestBody RequestWrapper requestWrapper) throws FileNotFoundException {
        List<ProductDto> productDtoList = requestWrapper.productDto();
        UserDto userDto = requestWrapper.userDto();
        // Your logic here
        System.out.println(productDtoList);
        return generatePdfServiceImp.generate(productDtoList,userDto);
    }

}
