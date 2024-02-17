package com.shop.soldphonev2.api.reviews;


import com.shop.soldphonev2.api.reviews.web.ReviewDto;
import com.shop.soldphonev2.api.reviews.web.ReviewResponseDto;
import com.shop.soldphonev2.api.reviews.web.ReviewResponseTopProductDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ReviewMapStruct {
    Review create(ReviewDto reviewDto);
    List<ReviewResponseDto> selectAll(List<Review> reviewList);
    ReviewResponseDto selectReviewByUuid(Review review);
    Review update(ReviewDto reviewDto);
    List<ReviewResponseTopProductDto> selectAllTopProduct(List<Review> reviewList);
}
