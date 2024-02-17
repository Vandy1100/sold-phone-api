package com.shop.soldphonev2.api.product;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    @InsertProvider(ProductProvider.class)
    void insertProduct(@Param("product")Product product,@Param("data") String data);
    @SelectProvider(ProductProvider.class)
    @Results({
            @Result(column = "image_url",property = "imageUrl")
    })
    List<Product> selectAllProduct();
    @SelectProvider(ProductProvider.class)
    Product selectProductUuid(String uuid);
    @UpdateProvider(ProductProvider.class)
    void updateProduct(@Param("product")Product product, String uuid);

    @DeleteProvider(ProductProvider.class)
    void deleteProduct(String uuid);


    @SelectProvider(ProductProvider.class)
    @Results({
            @Result(column = "image_url",property = "imageUrl")
    })
    List<Product> selectRecentProduct();
}
