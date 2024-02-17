package com.shop.soldphonev2.api.reviews;


import com.shop.soldphonev2.api.product.Product;
import com.shop.soldphonev2.api.reviews.web.ReviewDto;
import com.shop.soldphonev2.api.reviews.web.ReviewResponseDto;
import com.shop.soldphonev2.api.reviews.web.ReviewResponseTopProductDto;
import com.shop.soldphonev2.api.user.User;
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
public class ReviewServiceImp implements ReviewService{
    private final ReviewMapStruct reviewMapStruct;
    private final ReviewMapper reviewMapper;
    @Override
    public BaseResponseMessage insertReview(ReviewDto reviewDto) {
        try {
            String uuid = UUID.randomUUID().toString();
            Date date = new Date();
            Review review = reviewMapStruct.create(reviewDto);
            review.setReviewDate(date);
            review.setUuid(uuid);
            review.setProduct(new Product().setId(reviewDto.productId()));
            reviewMapper.insertReview(review);

            return new BaseResponseMessage()
                    .setMessage("Review has been created successfully.")
                    .setData(reviewDto)
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
    public BaseResponseMessage<List<ReviewResponseDto>> selectAllReviews() {
        try {
            List<Review> reviewList = reviewMapper.selectAllReviews();
            List<ReviewResponseDto> reviewResponseDtos = reviewMapStruct.selectAll(reviewList);
            if(!ObjectUtils.isEmpty(reviewResponseDtos)){
                return new BaseResponseMessage<List<ReviewResponseDto>>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(reviewResponseDtos)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved all reviews")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<List<ReviewResponseDto>>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select reviews!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            return new BaseResponseMessage<List<ReviewResponseDto>>().setMessage("Exception occurred!!!")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);

        }
    }

    @Override
    public BaseResponseMessage<ReviewResponseDto> selectReviewByUuid(String uuid) {
        try {
            Review review = reviewMapper.selectReviewUuid(uuid);
            ReviewResponseDto reviewResponseDto = reviewMapStruct.selectReviewByUuid(review);
            if(!ObjectUtils.isEmpty(reviewResponseDto)){
                return new BaseResponseMessage<ReviewResponseDto>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(reviewResponseDto)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved review")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<ReviewResponseDto>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select review!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            return new BaseResponseMessage<ReviewResponseDto>().setMessage("Exception occurred!!")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);
        }
    }

    @Override
    public BaseResponseMessage update(ReviewDto reviewDto, String uuid) {
        try {
            Review review = reviewMapStruct.update(reviewDto);
            reviewMapper.updateReview(review,uuid);
            return new BaseResponseMessage()
                    .setMessage("Review has been updated successfully.")
                    .setData(reviewDto)
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
            reviewMapper.deleteReview(uuid);
            return new BaseResponseMessage()
                    .setMessage("Review has been deleted successfully.")
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
    public BaseResponseMessage<List<ReviewResponseTopProductDto>> selectAllTopProduct() {
        try {
            List<Review> reviewList = reviewMapper.selectTopProduct();
            List<ReviewResponseTopProductDto> reviewResponseTopProductDtos = reviewMapStruct.selectAllTopProduct(reviewList);
            if(!ObjectUtils.isEmpty(reviewResponseTopProductDtos)){
                return new BaseResponseMessage<List<ReviewResponseTopProductDto>>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(reviewResponseTopProductDtos)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved all reviews")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<List<ReviewResponseTopProductDto>>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select reviews!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResponseMessage<List<ReviewResponseTopProductDto>>().setMessage("Exception occurred!!!")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);

        }
    }
}
