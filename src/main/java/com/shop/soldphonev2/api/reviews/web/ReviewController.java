package com.shop.soldphonev2.api.reviews.web;

import com.shop.soldphonev2.api.reviews.ReviewService;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping("")
    BaseResponseMessage<?> create(@RequestBody ReviewDto reviewDto){
        return reviewService.insertReview(reviewDto);
    }
    @GetMapping("")
    BaseResponseMessage<?> selectAll(){
        return reviewService.selectAllReviews();
    }
    @GetMapping("/{uuid}")
    BaseResponseMessage<?> selectByUuid(@PathVariable String uuid){
        return reviewService.selectReviewByUuid(uuid);
    }
    @PutMapping("/{uuid}")
    BaseResponseMessage<?> update(@RequestBody ReviewDto reviewDto,@PathVariable String uuid){
        return reviewService.update(reviewDto,uuid);
    }
    @DeleteMapping("/{uuid}")
    BaseResponseMessage<?> delete(@PathVariable String uuid){
        return reviewService.delete(uuid);
    }
    @GetMapping("/top")
    BaseResponseMessage<?> selectTopProduct(){
        return reviewService.selectAllTopProduct();
    }
}
