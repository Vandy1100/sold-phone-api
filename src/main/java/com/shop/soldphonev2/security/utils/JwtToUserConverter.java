package com.shop.soldphonev2.security.utils;


import com.shop.soldphonev2.api.auth.Auth;
import com.shop.soldphonev2.api.auth.UserDetailMapper;
import com.shop.soldphonev2.security.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
    final UserDetailMapper userDetailMapper;
    public JwtToUserConverter(UserDetailMapper userDetailMapper) {
        this.userDetailMapper = userDetailMapper;
    }
    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        Auth user = userDetailMapper.loadUserByUsername(source.getSubject());
        if (user != null) {
            UserDetailsImpl userDetail = new UserDetailsImpl(user);
            userDetail.setId(user.getId());
            userDetail.setEmail(user.getEmail());
            userDetail.setPassword(user.getPassword());
            return new UsernamePasswordAuthenticationToken(userDetail, source, userDetail.getAuthorities());
        } else {
            throw new BadCredentialsException("invalid Token");
        }

    }
}