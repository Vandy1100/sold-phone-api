package com.shop.soldphonev2.api.reviews.web;

import com.shop.soldphonev2.api.product.Product;

public record ReviewResponseTopProductDto(
        Float rating,
        Product product
) {
}
