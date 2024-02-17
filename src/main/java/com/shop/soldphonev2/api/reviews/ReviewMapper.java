package com.shop.soldphonev2.api.reviews;

import com.shop.soldphonev2.api.brand.Brand;
import com.shop.soldphonev2.api.brand.BrandProvider;
import com.shop.soldphonev2.api.product.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewMapper {
    @InsertProvider(ReviewProvider.class)
    void insertReview(@Param("review") Review review);
    @SelectProvider(ReviewProvider.class)
    List<Review> selectAllReviews();
    @SelectProvider(ReviewProvider.class)
    Review selectReviewUuid(String uuid);
    @UpdateProvider(ReviewProvider.class)
    void updateReview(@Param("review") Review review, String uuid);

    @DeleteProvider(ReviewProvider.class)
    void deleteReview(String uuid);

    @SelectProvider(ReviewProvider.class)
    @Results({
            @Result(column = "product_id", property = "product", one = @One(select = "selectBrandById"))
    })
    List<Review> selectTopProduct();

    @Select("SELECT uuid,name,image_Url,description,price FROM products WHERE product_id = #{id}")
    @Results({
            @Result(column = "image_url",property = "imageUrl")
    })
    Product selectBrandById(int id);

}
