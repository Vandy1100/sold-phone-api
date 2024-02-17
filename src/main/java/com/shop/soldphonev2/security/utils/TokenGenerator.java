package com.shop.soldphonev2.security.utils;


import com.shop.soldphonev2.api.auth.Token;
import com.shop.soldphonev2.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@Slf4j
public class TokenGenerator {
    final JwtEncoder accessTokenEncoder;
    final JwtEncoder refreshTokenEncoder;

    public TokenGenerator(JwtEncoder accessTokenEncoder, @Qualifier("jwtRefreshTokenEncoder") JwtEncoder refreshTokenEncoder) {
        this.accessTokenEncoder = accessTokenEncoder;
        this.refreshTokenEncoder = refreshTokenEncoder;
    }
    // access token
    public String createAccessToken(Authentication authentication){
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Instant now = Instant.now();
        // id, username, password, role
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("vindy")
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.HOURS))
                .subject(user.getUsername())
                .build();
        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
    // refresh token
    public String createRefreshToken(Authentication authentication){
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimSet  = JwtClaimsSet.builder()
                .issuer("vidy")
                .issuedAt(now)
                .expiresAt(now.plus(15,ChronoUnit.DAYS))
                .subject(user.getUsername())
                .build();
        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimSet)).getTokenValue();
    }
    public Token tokenResponse(Authentication authentication){
        if (!(authentication.getPrincipal() instanceof UserDetailsImpl user)) {
            throw new BadCredentialsException(
                    MessageFormat.format("principle {0} is not of User type ", authentication.getPrincipal().getClass())
            );
        }
        Token tokenDto = new Token();
        tokenDto.setId(user.getId());
        // create an access token
        tokenDto.setAccessToken(createAccessToken(authentication));
        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {
            Instant now = Instant.now();
            Instant expireAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expireAt);

            long daysUntilExpired = duration.toDays();
            if (daysUntilExpired < 7) {
                refreshToken = createRefreshToken(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = createRefreshToken(authentication);
        }
        tokenDto.setRefreshToken(refreshToken);
        return tokenDto;
    }
}