package com.shop.soldphonev2.api.model;

import com.shop.soldphonev2.api.brand.Brand;
import com.shop.soldphonev2.api.brand.web.BrandResponseDto;
import com.shop.soldphonev2.api.model.web.ModelDto;
import com.shop.soldphonev2.api.model.web.ModelResponseDto;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModelServiceImp implements ModelService{
    private final ModelMapStruct modelMapStruct;
    private final ModelMapper modelMapper;
    @Override
    public BaseResponseMessage insertModel(ModelDto modelDto) {
        try {
            String uuid = UUID.randomUUID().toString();
            Model model = modelMapStruct.created(modelDto);
            model.setBrand(new Brand().setId(modelDto.brandId()));
            model.setUuid(uuid);
            modelMapper.insertModel(model);

            return new BaseResponseMessage()
                    .setMessage("Model has been created successfully.")
                    .setData(modelDto)
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.OK.value()))
                    .setStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponseMessage()
                    .setMessage("Exception occurred!!")
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setStatus(false);
        }
    }
    @Override
    public BaseResponseMessage<List<ModelResponseDto>> selectAllModel() {
        try {
            List<Model> brandList = modelMapper.selectAllModel();
            List<ModelResponseDto> modelResponseDtos = modelMapStruct.selects(brandList);
            if(!ObjectUtils.isEmpty(modelResponseDtos)){
                return new BaseResponseMessage<List<ModelResponseDto>>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(modelResponseDtos)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved all Models")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<List<ModelResponseDto>>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select models!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResponseMessage<List<ModelResponseDto>>().setMessage("Exception occurred")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);

        }
    }

    @Override
    public BaseResponseMessage<ModelResponseDto> selectModelByUuid(String uuid) {
        return null;
    }

    @Override
    public BaseResponseMessage update(ModelDto modelDto, String uuid) {
        return null;
    }

    @Override
    public BaseResponseMessage delete(String uuid) {
        return null;
    }
}
