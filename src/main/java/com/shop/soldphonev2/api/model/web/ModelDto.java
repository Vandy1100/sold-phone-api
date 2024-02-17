package com.shop.soldphonev2.api.model.web;

public record ModelDto(
        String model,
        String description,
        Integer brandId,
        String operatingSystem,
        String cellularStorageCapacity,
        String connectivityTechnology,
        String color,
        String screenSize,
        String wirelessNetworkTechnology
) {
}
