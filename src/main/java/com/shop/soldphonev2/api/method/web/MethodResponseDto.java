package com.shop.soldphonev2.api.method.web;

public record MethodResponseDto(
        Integer id,
        String uuid,
        String methodAccountNumber,
        String methodType,
        String methodName,
        String description,
        String methodLink,
        Boolean isDeleted
) {
}
