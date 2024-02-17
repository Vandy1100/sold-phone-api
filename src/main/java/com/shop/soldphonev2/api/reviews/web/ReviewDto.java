package com.shop.soldphonev2.api.reviews.web;

public record ReviewDto(
        String uuid,
        Integer userId,
        Integer productId,
        Integer rating,
        String comments
) {
}
