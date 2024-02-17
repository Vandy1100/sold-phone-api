package com.shop.soldphonev2.api.brand;

import com.shop.soldphonev2.api.brand.web.BrandDto;
import com.shop.soldphonev2.api.brand.web.BrandResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapStruct {

    Brand create(BrandDto brandDto);
    List<BrandResponseDto> selectAll(List<Brand> brand);
    BrandResponseDto selectBrandByUuid(Brand brand);
    Brand update(BrandDto brandDto);
}
