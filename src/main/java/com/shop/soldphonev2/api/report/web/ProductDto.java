package com.shop.soldphonev2.api.report.web;

public record ProductDto(
        String productName,
        Integer quantity,
        Float pricePerPiece
) {
}
