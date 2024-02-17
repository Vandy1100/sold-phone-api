package com.shop.soldphonev2.api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int id;
    private String uuid;
    private String role;
    private boolean isDelete;
    private String description;
}
