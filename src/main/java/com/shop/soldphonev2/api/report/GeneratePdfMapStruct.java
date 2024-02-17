package com.shop.soldphonev2.api.report;

import com.shop.soldphonev2.api.report.web.ProductDto;
import com.shop.soldphonev2.api.report.web.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneratePdfMapStruct {
    List<ProductReport> create(List<ProductDto> productDtos);
    ProductReport user(UserDto userDtos);
}
