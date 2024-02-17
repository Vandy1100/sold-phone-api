package com.shop.soldphonev2.api.method;

import com.shop.soldphonev2.api.method.web.MethodDto;
import com.shop.soldphonev2.api.method.web.MethodResponseDto;
import com.shop.soldphonev2.api.reviews.Review;
import com.shop.soldphonev2.api.reviews.web.ReviewDto;
import com.shop.soldphonev2.api.reviews.web.ReviewResponseDto;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface MethodMapStruct {
    Method create(MethodDto methodDto);
    List<MethodResponseDto> selectAll(List<Method> methodList);
    MethodResponseDto selectMethodByUuid(Method method);
    Method update(MethodDto methodDto);
}
