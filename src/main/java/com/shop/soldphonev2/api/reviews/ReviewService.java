package com.shop.soldphonev2.api.reviews;

import com.shop.soldphonev2.api.reviews.web.ReviewDto;
import com.shop.soldphonev2.api.reviews.web.ReviewResponseDto;
import com.shop.soldphonev2.api.reviews.web.ReviewResponseTopProductDto;
import com.shop.soldphonev2.base.BaseResponseMessage;

import java.util.List;

public interface ReviewService {
    BaseResponseMessage insertReview(ReviewDto reviewDto);

    BaseResponseMessage<List<ReviewResponseDto>> selectAllReviews();
    BaseResponseMessage<ReviewResponseDto> selectReviewByUuid(String uuid);
    BaseResponseMessage update(ReviewDto reviewDto,String uuid);
    BaseResponseMessage delete(String uuid);
    BaseResponseMessage<List<ReviewResponseTopProductDto>> selectAllTopProduct();
}
