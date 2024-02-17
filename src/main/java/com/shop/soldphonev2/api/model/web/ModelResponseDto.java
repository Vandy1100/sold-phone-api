package com.shop.soldphonev2.api.model.web;

import com.shop.soldphonev2.api.brand.Brand;

public record ModelResponseDto(
        int id,
        String uuid,
        String model,
        String description,
        Brand brand,
        String operatingSystem,
        String cellularStorageCapacity,
        String connectivityTechnology,
        String color,
        String screenSize,
        String wirelessNetworkTechnology
) {
}
