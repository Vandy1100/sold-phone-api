package com.shop.soldphonev2.api.method;

import com.shop.soldphonev2.api.method.web.MethodDto;
import com.shop.soldphonev2.api.method.web.MethodResponseDto;
import com.shop.soldphonev2.api.product.Product;
import com.shop.soldphonev2.api.reviews.Review;
import com.shop.soldphonev2.api.reviews.web.ReviewResponseDto;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MethodServiceImp implements MethodService
{
    private final MethodMapper methodMapper;
    private final MethodMapStruct methodMapStruct;
    @Override
    public BaseResponseMessage insertMethod(MethodDto methodDto) {
        try {
            String uuid = UUID.randomUUID().toString();
            Method method = methodMapStruct.create(methodDto);
            method.setUuid(uuid);
            methodMapper.insertMethod(method);

            return new BaseResponseMessage()
                    .setMessage("Method has been created successfully.")
                    .setData(methodDto)
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
    public BaseResponseMessage<List<MethodResponseDto>> selectAllMethods() {
        try {
            List<Method> methodList = methodMapper.selectAllMethods();
            List<MethodResponseDto> methodResponseDtoList = methodMapStruct.selectAll(methodList);
            if(!ObjectUtils.isEmpty(methodResponseDtoList )){
                return new BaseResponseMessage<List<MethodResponseDto>>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(methodResponseDtoList )
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved all methods")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<List<MethodResponseDto>>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select methods!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            return new BaseResponseMessage<List<MethodResponseDto>>().setMessage("Exception occurred!!!")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);

        }
    }

    @Override
    public BaseResponseMessage<MethodResponseDto> selectMethodByUuid(String uuid) {
        try {
            Method method = methodMapper.selectMethodUuid(uuid);
            MethodResponseDto methodResponseDto = methodMapStruct.selectMethodByUuid(method);
            if(!ObjectUtils.isEmpty(methodResponseDto)){
                return new BaseResponseMessage<MethodResponseDto>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(methodResponseDto)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved method")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<MethodResponseDto>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select method!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            return new BaseResponseMessage<MethodResponseDto>().setMessage("Exception occurred!!")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);
        }
    }

    @Override
    public BaseResponseMessage<MethodResponseDto> selectMethodByType(Integer id) {
        try {
            Method method = methodMapper.selectMethodType(id);
            MethodResponseDto methodResponseDto = methodMapStruct.selectMethodByUuid(method);
            if(!ObjectUtils.isEmpty(methodResponseDto)){
                return new BaseResponseMessage<MethodResponseDto>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(methodResponseDto)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved method")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<MethodResponseDto>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select method!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResponseMessage<MethodResponseDto>().setMessage("Exception occurred!!")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);
        }

    }

    @Override
    public BaseResponseMessage update(MethodDto methodDto, String uuid) {
        try {
            Method method = methodMapStruct.update(methodDto);
            methodMapper.updateMethod(method, uuid);
            return new BaseResponseMessage()
                    .setMessage("Method has been updated successfully.")
                    .setData(methodDto)
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
    public BaseResponseMessage delete(String uuid) {
        try {
            methodMapper.deleteMethod(uuid);
            return new BaseResponseMessage()
                    .setMessage("Method has been deleted successfully.")
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.OK.value()))
                    .setStatus(true);
        } catch (Exception e) {
            return new BaseResponseMessage()
                    .setMessage("Exception occurred!!")
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setStatus(false);
        }
    }
}
