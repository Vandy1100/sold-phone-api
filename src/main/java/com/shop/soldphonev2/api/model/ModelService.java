package com.shop.soldphonev2.api.model;

import com.shop.soldphonev2.api.brand.web.BrandDto;
import com.shop.soldphonev2.api.brand.web.BrandResponseDto;
import com.shop.soldphonev2.api.model.web.ModelDto;
import com.shop.soldphonev2.api.model.web.ModelResponseDto;
import com.shop.soldphonev2.base.BaseResponseMessage;

import java.util.List;

public interface ModelService {
    BaseResponseMessage insertModel(ModelDto modelDto);

    BaseResponseMessage<List<ModelResponseDto>> selectAllModel();
    BaseResponseMessage<ModelResponseDto> selectModelByUuid(String uuid);
    BaseResponseMessage update(ModelDto modelDto,String uuid);
    BaseResponseMessage delete(String uuid);
}
