package com.shop.soldphonev2.api.brand.web;

public record BrandResponseDto(
        int id,
        String uuid,
        String brand,
        String description
) {
}
