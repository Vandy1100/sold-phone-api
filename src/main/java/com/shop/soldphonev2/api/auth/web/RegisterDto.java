package com.shop.soldphonev2.api.auth.web;

import lombok.Getter;

public record RegisterDto(

        String username,

        String email,
        @Getter
        String password
) {
}
