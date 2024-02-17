package com.shop.soldphonev2.api.brand;

import com.shop.soldphonev2.api.brand.web.BrandDto;
import com.shop.soldphonev2.api.brand.web.BrandResponseDto;
import com.shop.soldphonev2.base.BaseResponseMessage;

import java.util.List;

public interface BrandService {
    BaseResponseMessage insertBrand(BrandDto brandDto);

    BaseResponseMessage<List<BrandResponseDto>> selectAllBrands();
    BaseResponseMessage<BrandResponseDto> selectBrandByUuid(String uuid);
    BaseResponseMessage update(BrandDto brandDto,String uuid);
    BaseResponseMessage delete(String uuid);

}
