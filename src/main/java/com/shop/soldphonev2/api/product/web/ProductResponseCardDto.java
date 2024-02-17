package com.shop.soldphonev2.api.product.web;

public record ProductResponseCardDto (
    String name,
    String description,
    Float price,
    String imageUrl,
    String uuid
    ){}
