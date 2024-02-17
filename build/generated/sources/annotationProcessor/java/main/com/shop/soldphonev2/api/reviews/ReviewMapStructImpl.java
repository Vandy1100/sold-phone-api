package com.shop.soldphonev2.api.reviews;

import com.shop.soldphonev2.api.product.Product;
import com.shop.soldphonev2.api.reviews.web.ReviewDto;
import com.shop.soldphonev2.api.reviews.web.ReviewResponseDto;
import com.shop.soldphonev2.api.reviews.web.ReviewResponseTopProductDto;
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
public class ReviewMapStructImpl implements ReviewMapStruct {

    @Override
    public Review create(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        Review review = new Review();

        review.setUserId( reviewDto.userId() );
        if ( reviewDto.rating() != null ) {
            review.setRating( reviewDto.rating().floatValue() );
        }
        review.setComments( reviewDto.comments() );
        review.setUuid( reviewDto.uuid() );

        return review;
    }

    @Override
    public List<ReviewResponseDto> selectAll(List<Review> reviewList) {
        if ( reviewList == null ) {
            return null;
        }

        List<ReviewResponseDto> list = new ArrayList<ReviewResponseDto>( reviewList.size() );
        for ( Review review : reviewList ) {
            list.add( selectReviewByUuid( review ) );
        }

        return list;
    }

    @Override
    public ReviewResponseDto selectReviewByUuid(Review review) {
        if ( review == null ) {
            return null;
        }

        Integer id = null;
        String uuid = null;
        Integer userId = null;
        Product product = null;
        Integer rating = null;
        Date reviewDate = null;
        String comments = null;

        id = review.getId();
        uuid = review.getUuid();
        userId = review.getUserId();
        product = review.getProduct();
        if ( review.getRating() != null ) {
            rating = review.getRating().intValue();
        }
        reviewDate = review.getReviewDate();
        comments = review.getComments();

        Boolean deleted = null;

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto( id, uuid, userId, product, rating, reviewDate, comments, deleted );

        return reviewResponseDto;
    }

    @Override
    public Review update(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        Review review = new Review();

        review.setUserId( reviewDto.userId() );
        if ( reviewDto.rating() != null ) {
            review.setRating( reviewDto.rating().floatValue() );
        }
        review.setComments( reviewDto.comments() );
        review.setUuid( reviewDto.uuid() );

        return review;
    }

    @Override
    public List<ReviewResponseTopProductDto> selectAllTopProduct(List<Review> reviewList) {
        if ( reviewList == null ) {
            return null;
        }

        List<ReviewResponseTopProductDto> list = new ArrayList<ReviewResponseTopProductDto>( reviewList.size() );
        for ( Review review : reviewList ) {
            list.add( reviewToReviewResponseTopProductDto( review ) );
        }

        return list;
    }

    protected ReviewResponseTopProductDto reviewToReviewResponseTopProductDto(Review review) {
        if ( review == null ) {
            return null;
        }

        Float rating = null;
        Product product = null;

        rating = review.getRating();
        product = review.getProduct();

        ReviewResponseTopProductDto reviewResponseTopProductDto = new ReviewResponseTopProductDto( rating, product );

        return reviewResponseTopProductDto;
    }
}
