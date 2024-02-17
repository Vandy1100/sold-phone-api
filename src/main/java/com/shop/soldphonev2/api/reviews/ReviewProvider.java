package com.shop.soldphonev2.api.reviews;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ReviewProvider implements ProviderMethodResolver {
    public String insertReview(){
        return new SQL(){{
            INSERT_INTO("reviews");
            VALUES("user_id","#{review.userId}");
            VALUES("product_id","#{review.product.id}");
            VALUES("rating","#{review.rating}");
            VALUES("comments","#{review.comments}");
            VALUES("review_date","#{review.reviewDate}");
            VALUES("uuid","#{review.uuid}");
        }}.toString();
    }
    public String selectAllReviews(){
        return new SQL(){{
            SELECT("*");
            FROM("reviews");
        }}.toString();
    }
    public String selectReviewUuid(){
        return new SQL(){{
            SELECT("*");
            FROM("reviews");
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
    public String updateReview(){
        return new SQL(){{
            UPDATE("reviews");
            SET("rating=#{review.rating}");
            SET("comments=#{review.comments}");
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
    public String deleteReview(){
        return new SQL(){{
            DELETE_FROM("reviews");
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
    public String selectTopProduct(){
        return new SQL(){{
        SELECT("SUM(rating) AS sum_rating,AVG(rating) AS rating , product_id");
        FROM("reviews");
        GROUP_BY("product_id");
        ORDER_BY("rating DESC");
        }}.toString();
    }
}
