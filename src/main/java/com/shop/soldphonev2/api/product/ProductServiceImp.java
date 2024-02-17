package com.shop.soldphonev2.api.product;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shop.soldphonev2.api.product.web.ProductDto;
import com.shop.soldphonev2.api.product.web.ProductResponseCardDto;
import com.shop.soldphonev2.api.product.web.ProductResponseDto;
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
public class ProductServiceImp implements ProductService{
    private final ProductMapStruct productMapStruct;
    private final ProductMapper productMapper;

    @Override
    public BaseResponseMessage insertProduct(ProductDto productDto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode rootObject = mapper.createObjectNode();
            ObjectNode imageList = rootObject.putObject("instruction");
            imageList.put("frontImage",productDto.frontImage());
            imageList.put("backImage",productDto.backImage());
            imageList.put("bodyImage",productDto.bodyImage());
            imageList.put("cameraImage",productDto.cameraImage());
            imageList.put("interfaceImage",productDto.interfaceImage());

            ObjectNode materialList = rootObject.putObject("materialList");
            materialList.put("model",productDto.model());
            materialList.put("operationSystem",productDto.operatingSystem());
            materialList.put("CellularStorageCapacity",productDto.cellularStorageCapacity());
            materialList.put("connectivityTechnology",productDto.connectivityTechnology());
            materialList.put("color",productDto.color());
            materialList.put("screenSize",productDto.screenSize());
            materialList.put("wirelessNetworkTechnology",productDto.wirelessNetworkTechnology());

            String data = mapper.writeValueAsString(rootObject);

            Date date = new Date();
            String uuid = UUID.randomUUID().toString();
            Product product = productMapStruct.create(productDto);
            product.setReleaseDate(date);
            product.setUuid(uuid);
            productMapper.insertProduct(product,data);
            return new BaseResponseMessage()
                    .setMessage("Product has been created successfully.")
                    .setData(productDto)
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.OK.value()))
                    .setStatus(true);
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResponseMessage()
                    .setMessage("Exception occurred!!")
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setStatus(false);
        }
    }

    @Override
    public BaseResponseMessage<List<ProductResponseDto>> selectAllProducts() {
        try {
            List<Product> products = productMapper.selectAllProduct();
            List<ProductResponseDto> productResponseDtos = productMapStruct.selectAllProducts(products);
            if(!ObjectUtils.isEmpty(productResponseDtos)){
                return new BaseResponseMessage<List<ProductResponseDto>>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(productResponseDtos)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved all products")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<List<ProductResponseDto>>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select products!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            return new BaseResponseMessage<List<ProductResponseDto>>().setMessage("Exception occurred")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);

        }
    }


    @Override
    public BaseResponseMessage<ProductResponseDto> selectProductByUuid(String uuid) {
        try {
            Product product = productMapper.selectProductUuid(uuid);
            ProductResponseDto productResponseDto = productMapStruct.selectProductByUuid(product);
            if(!ObjectUtils.isEmpty(productResponseDto)){
                return new BaseResponseMessage<ProductResponseDto>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(productResponseDto)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved product")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<ProductResponseDto>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select product!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            return new BaseResponseMessage<ProductResponseDto>().setMessage("Exception occurred")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);

        }
    }

    @Override
    public BaseResponseMessage update(ProductDto productDto, String uuid) {
        try {
            Product product = productMapStruct.update(productDto);
            productMapper.updateProduct(product,uuid);
            return new BaseResponseMessage()
                    .setMessage("Product has been updated successfully.")
                    .setData(productDto)
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
            productMapper.deleteProduct(uuid);
            return new BaseResponseMessage()
                    .setMessage("Product has been deleted successfully.")
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
    public BaseResponseMessage<List<ProductResponseCardDto>> selectAllProductCard() {
        try {
            List<Product> products = productMapper.selectRecentProduct();
            List<ProductResponseCardDto> productResponseDtos = productMapStruct.selectAllProductsCard(products);
            if(!ObjectUtils.isEmpty(productResponseDtos)){
                return new BaseResponseMessage<List<ProductResponseCardDto>>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(productResponseDtos)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved all products")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<List<ProductResponseCardDto>>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select products!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            return new BaseResponseMessage<List<ProductResponseCardDto>>().setMessage("Exception occurred")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);

        }
    }
}
