package com.shop.soldphonev2.api.auth;

import com.shop.soldphonev2.api.auth.web.RegisterDto;
import com.shop.soldphonev2.api.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapStruct {
    User created(RegisterDto registerDto);
}
