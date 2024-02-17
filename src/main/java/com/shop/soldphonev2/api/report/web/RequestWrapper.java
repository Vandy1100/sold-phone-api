package com.shop.soldphonev2.api.report.web;

import java.util.List;

public record RequestWrapper(
        List<ProductDto> productDto,
        UserDto userDto
) {
}
