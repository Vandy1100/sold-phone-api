package com.shop.soldphonev2.api.model;

import com.shop.soldphonev2.api.model.web.ModelDto;
import com.shop.soldphonev2.api.model.web.ModelResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapStruct {

    Model created(ModelDto modelDto);
    List<ModelResponseDto> selects(List<Model> models);
}
