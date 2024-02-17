package com.shop.soldphonev2.api.auth.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String uuid;
    private String username;
    private List<RoleResponseDto> roles;
}
