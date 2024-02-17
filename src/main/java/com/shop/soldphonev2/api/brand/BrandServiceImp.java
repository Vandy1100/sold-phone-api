package com.shop.soldphonev2.api.brand;

import com.shop.soldphonev2.api.brand.web.BrandDto;
import com.shop.soldphonev2.api.brand.web.BrandResponseDto;
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
public class BrandServiceImp implements BrandService{

    private final BrandMapStruct brandMapStruct;
    private final BrandMapper brandMapper;
    @Override
    public BaseResponseMessage insertBrand(BrandDto brandDto) {
        try {
            String uuid = UUID.randomUUID().toString();
            Brand brand = brandMapStruct.create(brandDto);
            brand.setUuid(uuid);
            brandMapper.insertBrand(brand);

            return new BaseResponseMessage()
                    .setMessage("Brand has been created successfully.")
                    .setData(brandDto)
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

    @Override
    public BaseResponseMessage<List<BrandResponseDto>> selectAllBrands() {
        try {
          List<Brand> brandList = brandMapper.selectAllBrand();
          List<BrandResponseDto> brandResponseDtos = brandMapStruct.selectAll(brandList);
            if(!ObjectUtils.isEmpty(brandResponseDtos)){
                return new BaseResponseMessage<List<BrandResponseDto>>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(brandResponseDtos)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved all brands")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<List<BrandResponseDto>>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select brands!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            return new BaseResponseMessage<List<BrandResponseDto>>().setMessage("Exception occurred")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);

        }
    }

    @Override
    public BaseResponseMessage<BrandResponseDto> selectBrandByUuid(String uuid) {
        try {
            Brand brand = brandMapper.selectBrandUuid(uuid);
            System.out.println("dd"+brand);
            BrandResponseDto brandResponseDto = brandMapStruct.selectBrandByUuid(brand);
            if(!ObjectUtils.isEmpty(brandResponseDto)){
                return new BaseResponseMessage<BrandResponseDto>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(brandResponseDto)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved brands")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<BrandResponseDto>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select brands!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            return new BaseResponseMessage<BrandResponseDto>().setMessage("Exception occurred")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);
        }
    }

    @Override
    public BaseResponseMessage update(BrandDto brandDto, String uuid) {
        try {
            Brand brand = brandMapStruct.update(brandDto);
            System.out.println(brand);
            brandMapper.updateBrand(brand,uuid);
            return new BaseResponseMessage()
                    .setMessage("Brand has been updated successfully.")
                    .setData(brandDto)
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

    @Override
    public BaseResponseMessage delete(String uuid) {
        try {
            brandMapper.deleteBrand(uuid);
            return new BaseResponseMessage()
                    .setMessage("Brand has been deleted successfully.")
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
