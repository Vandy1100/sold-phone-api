package com.shop.soldphonev2.api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    private int id;
    private String email;
    private String password;
    private List<String> roles;
    private Boolean verified;
}
