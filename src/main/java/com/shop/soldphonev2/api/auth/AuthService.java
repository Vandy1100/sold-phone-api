package com.shop.soldphonev2.api.auth;

import com.shop.soldphonev2.api.auth.web.LoginDto;
import com.shop.soldphonev2.api.auth.web.RegisterDto;
import com.shop.soldphonev2.base.BaseResponseMessage;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

public interface AuthService {
    BaseResponseMessage register(RegisterDto registerDto) throws MessagingException;
    BaseResponseMessage login(LoginDto loginDto);
    BaseResponseMessage getMe();
    BaseResponseMessage refreshToken(Token tokenDto);

}
