package com.shop.soldphonev2.api.method;

import com.shop.soldphonev2.api.method.web.MethodDto;
import com.shop.soldphonev2.api.method.web.MethodResponseDto;
import com.shop.soldphonev2.api.model.web.ModelDto;
import com.shop.soldphonev2.api.model.web.ModelResponseDto;
import com.shop.soldphonev2.base.BaseResponseMessage;

import java.util.List;

public interface MethodService {
    BaseResponseMessage insertMethod(MethodDto methodDto);

    BaseResponseMessage<List<MethodResponseDto>> selectAllMethods();
    BaseResponseMessage<MethodResponseDto> selectMethodByUuid(String uuid);
    BaseResponseMessage<MethodResponseDto> selectMethodByType(Integer id);

    BaseResponseMessage update(MethodDto methodDto,String uuid);
    BaseResponseMessage delete(String uuid);
}
