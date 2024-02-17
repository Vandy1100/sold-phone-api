package com.shop.soldphonev2.api.file.image.web;

public record FileImageResponseDto(
        String uuid,
        String name,
        String filepath,
        Long size,
        String extension
) {
}
