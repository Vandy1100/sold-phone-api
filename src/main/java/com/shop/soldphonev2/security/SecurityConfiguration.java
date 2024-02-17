package com.shop.soldphonev2.security;



import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.shop.soldphonev2.security.utils.JwtToUserConverter;
import com.shop.soldphonev2.security.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UserDetailsService userDetailsService;
    private final JwtToUserConverter jwtToUserConverter;
    private final KeyUtils keyUtils;
    final PasswordEncoder passwordEncoder;
    final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    public SecurityConfiguration(KeyUtils keyUtils, JwtToUserConverter jwtToUserConverter, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.keyUtils = keyUtils;
        this.jwtToUserConverter = jwtToUserConverter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws  Exception{
        // Disable CSRF
        http.csrf(AbstractHttpConfigurer::disable);
        // Cors
//        http.cors(corsConfigurer -> corsConfigurer.configure(http));

        // Authorize URL mapping
        http.authorizeHttpRequests(auth -> {
            //Set permission to admins
            auth.requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll();
            auth.requestMatchers(HttpMethod.PUT, "/api/v1/auth/**").permitAll();
            auth.requestMatchers(HttpMethod.DELETE, "/api/v1/auth/**").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/api/v1/auth/**").permitAll();
            auth.anyRequest().permitAll();
        });


        // Make security http STATELESS
        http.sessionManagement(session
                -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Exception
        http.exceptionHandling(ex
                -> ex.authenticationEntryPoint(customAuthenticationEntryPoint));
        // Spring Security is used to configure a resource server that supports OAuth 2.0 token jwt authentication
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(
                jwt -> jwt.jwtAuthenticationConverter(jwtToUserConverter)));


        return http.build();
    }

    @Bean
    @Primary
    JwtDecoder jwtAccessTokenDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyUtils.getAccessTokenPublicKey()).build();
    }
    @Bean
    @Primary
    JwtEncoder jwtAccessTokenEncoder() {
        JWK jwt = new RSAKey.Builder(keyUtils.getAccessTokenPublicKey()).privateKey(keyUtils.getAccessTokenPrivateKey()).build();
        JWKSource<SecurityContext> jwtSource = new ImmutableJWKSet<>(new JWKSet(jwt));
        return new NimbusJwtEncoder(jwtSource);
    }

    @Bean
    @Qualifier("jwtRefreshTokenDecoder")
    JwtDecoder jwtRefreshTokenDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyUtils.getRefreshTokenPublicKey()).build();
    }
    @Bean
    @Qualifier("jwtRefreshTokenEncoder")
    JwtEncoder jwtRefreshTokenEncoder() {
        JWK jwk = new RSAKey.Builder(keyUtils.getRefreshTokenPublicKey())
                .privateKey(keyUtils.getRefreshTokenPrivateKey())
                .build();

        JWKSource<SecurityContext> jwtSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwtSource);
    }
    /// create the converter in order to convert the token to the object.
    @Bean
    @Qualifier("jwtRefreshTokenAuthProvider")
    JwtAuthenticationProvider jwtRefreshTokenAuthProvider() {
        JwtAuthenticationProvider provider = new JwtAuthenticationProvider(jwtRefreshTokenDecoder());
        provider.setJwtAuthenticationConverter(jwtToUserConverter);
        return provider;
    }
}