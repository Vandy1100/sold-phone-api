package com.shop.soldphonev2.api.reviews.web;

import com.shop.soldphonev2.api.product.Product;

import java.util.Date;

public record ReviewResponseDto(
        Integer id,
        String uuid,
        Integer userId,
        Product product,
        Integer rating,
        Date reviewDate,
        String comments,
        Boolean deleted
) {
}
