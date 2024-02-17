package com.shop.soldphonev2.api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private int id;
    private String accessToken;
    private String refreshToken;
}
