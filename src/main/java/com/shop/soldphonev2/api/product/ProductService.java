package com.shop.soldphonev2.api.product;

import com.shop.soldphonev2.api.brand.web.BrandDto;
import com.shop.soldphonev2.api.brand.web.BrandResponseDto;
import com.shop.soldphonev2.api.product.web.ProductDto;
import com.shop.soldphonev2.api.product.web.ProductResponseCardDto;
import com.shop.soldphonev2.api.product.web.ProductResponseDto;
import com.shop.soldphonev2.base.BaseResponseMessage;

import java.util.List;

public interface ProductService {
    BaseResponseMessage insertProduct(ProductDto productDto);

    BaseResponseMessage<List<ProductResponseDto>> selectAllProducts();
    BaseResponseMessage<ProductResponseDto> selectProductByUuid(String uuid);
    BaseResponseMessage update(ProductDto productDto,String uuid);
    BaseResponseMessage delete(String uuid);
    BaseResponseMessage<List<ProductResponseCardDto>> selectAllProductCard();
}
