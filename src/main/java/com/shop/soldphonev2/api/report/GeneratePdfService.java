package com.shop.soldphonev2.api.report;

import com.shop.soldphonev2.api.report.web.ProductDto;
import com.shop.soldphonev2.api.report.web.UserDto;
import com.shop.soldphonev2.base.BaseResponseMessage;

import java.io.FileNotFoundException;
import java.util.List;

public interface GeneratePdfService {
    BaseResponseMessage<?> generate(List<ProductDto> productDtos, UserDto userDto)throws FileNotFoundException;
}
