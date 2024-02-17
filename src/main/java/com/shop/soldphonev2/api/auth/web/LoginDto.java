package com.shop.soldphonev2.api.auth.web;



public record LoginDto(
//        @NotBlank(message = "Email is required!!")
        String email,
//        @NotBlank(message = "Password is required!!")
        String password) {
}
