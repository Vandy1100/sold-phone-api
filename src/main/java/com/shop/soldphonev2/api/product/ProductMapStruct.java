package com.shop.soldphonev2.api.product;

import com.shop.soldphonev2.api.brand.web.BrandDto;
import com.shop.soldphonev2.api.product.web.ProductDto;
import com.shop.soldphonev2.api.product.web.ProductResponseCardDto;
import com.shop.soldphonev2.api.product.web.ProductResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapStruct {
    Product create(ProductDto productDto);
    List<ProductResponseDto> selectAllProducts(List<Product> products);
    ProductResponseDto selectProductByUuid(Product product);
    Product update(ProductDto productDto);
    List<ProductResponseCardDto> selectAllProductsCard(List<Product> products);

}
