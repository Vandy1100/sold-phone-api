package com.shop.soldphonev2.api.method.web;

public record MethodDto(
        String methodAccountNumber,
        String methodType,
        String methodName,
        String description,
        String methodLink
) {
}
