package com.shop.soldphonev2.api.product;

import com.shop.soldphonev2.api.product.web.ProductDto;
import com.shop.soldphonev2.api.product.web.ProductResponseCardDto;
import com.shop.soldphonev2.api.product.web.ProductResponseDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-29T11:39:46+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class ProductMapStructImpl implements ProductMapStruct {

    @Override
    public Product create(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productDto.name() );
        product.setDescription( productDto.description() );
        product.setPrice( productDto.price() );
        product.setStockQuantity( productDto.stockQuantity() );
        product.setManufacturerId( productDto.manufacturerId() );
        product.setImageUrl( productDto.imageUrl() );
        product.setBrandId( productDto.brandId() );

        return product;
    }

    @Override
    public List<ProductResponseDto> selectAllProducts(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResponseDto> list = new ArrayList<ProductResponseDto>( products.size() );
        for ( Product product : products ) {
            list.add( selectProductByUuid( product ) );
        }

        return list;
    }

    @Override
    public ProductResponseDto selectProductByUuid(Product product) {
        if ( product == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String description = null;
        Float price = null;
        Integer stockQuantity = null;
        Integer manufacturerId = null;
        String imageUrl = null;
        String uuid = null;
        Date releaseDate = null;
        Boolean isEnable = null;
        Boolean isDeleted = null;
        Integer brandId = null;
        String data = null;

        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        stockQuantity = product.getStockQuantity();
        manufacturerId = product.getManufacturerId();
        imageUrl = product.getImageUrl();
        uuid = product.getUuid();
        releaseDate = product.getReleaseDate();
        isEnable = product.getIsEnable();
        isDeleted = product.getIsDeleted();
        brandId = product.getBrandId();
        data = product.getData();

        ProductResponseDto productResponseDto = new ProductResponseDto( id, name, description, price, stockQuantity, manufacturerId, imageUrl, uuid, releaseDate, isEnable, isDeleted, brandId, data );

        return productResponseDto;
    }

    @Override
    public Product update(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productDto.name() );
        product.setDescription( productDto.description() );
        product.setPrice( productDto.price() );
        product.setStockQuantity( productDto.stockQuantity() );
        product.setManufacturerId( productDto.manufacturerId() );
        product.setImageUrl( productDto.imageUrl() );
        product.setBrandId( productDto.brandId() );

        return product;
    }

    @Override
    public List<ProductResponseCardDto> selectAllProductsCard(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResponseCardDto> list = new ArrayList<ProductResponseCardDto>( products.size() );
        for ( Product product : products ) {
            list.add( productToProductResponseCardDto( product ) );
        }

        return list;
    }

    protected ProductResponseCardDto productToProductResponseCardDto(Product product) {
        if ( product == null ) {
            return null;
        }

        String name = null;
        String description = null;
        Float price = null;
        String imageUrl = null;
        String uuid = null;

        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imageUrl = product.getImageUrl();
        uuid = product.getUuid();

        ProductResponseCardDto productResponseCardDto = new ProductResponseCardDto( name, description, price, imageUrl, uuid );

        return productResponseCardDto;
    }
}
