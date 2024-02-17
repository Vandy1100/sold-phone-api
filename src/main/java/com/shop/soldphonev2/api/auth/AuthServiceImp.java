package com.shop.soldphonev2.api.auth;

import com.shop.soldphonev2.api.auth.web.LoginDto;
import com.shop.soldphonev2.api.auth.web.RegisterDto;
import com.shop.soldphonev2.api.auth.web.UserDto;
import com.shop.soldphonev2.api.user.User;
import com.shop.soldphonev2.base.BaseResponseMessage;
import com.shop.soldphonev2.security.UserDetailsImpl;
import com.shop.soldphonev2.security.utils.TokenGenerator;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService{
//    private final UserMapStruct userMapStruct;
//    private final EmailService emailService;
    private final AuthMapper authMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final TokenGenerator tokenGenerator;
    private final AuthMapStruct authMapStruct;
    public BaseResponseMessage register(RegisterDto registerDto) throws MessagingException {
        try {
            String uuid = UUID.randomUUID().toString();
            User user = authMapStruct.created(registerDto);
            String encoder = bCryptPasswordEncoder.encode(registerDto.password());
            user.setPassword(encoder);
            user.setUuid(uuid);
            user.setVerified(true);
            Role role = authMapper.findRoleByName("ADMIN");
            log.info("USER, {}",role);
//            emailService.sendVerificationEmail(registerDTO.email(), user.getUuid(), registerDTO.username());
            if (authMapper.createUser(user)) {
                log.info("USER, {}", user);
                authMapper.createUserRole(user.getId(), role.getId());
            }
            return new BaseResponseMessage<>().setMessage("User has been registered successfully,Please check your email for verify.")
                    .setCode(String.valueOf(HttpStatus.OK.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setData(registerDto)
                    .setStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponseMessage<>().setMessage("Failed to created user!!!")
                    .setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);
        }
    }

    @Override
    public BaseResponseMessage login(LoginDto loginDto) {
        try {
            Authentication authentication = daoAuthenticationProvider.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(
                            loginDto.email(), loginDto.password()
                    )
            );
            Token response = tokenGenerator.tokenResponse(authentication);
            return new BaseResponseMessage<>().setMessage("You have been login successfully.")
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(true)
                    .setData(response)
                    .setCode(String.valueOf(HttpStatus.OK.value()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("username and password is incorrect!!");
        }
    }

    @Override
    public BaseResponseMessage<?> getMe() {
        try {
            System.out.println("hello");
            Authentication authentication = SecurityContextHolder.getContext()
                    .getAuthentication();
            System.out.println(authentication);
            UserDetailsImpl userDetail = (UserDetailsImpl) authentication.getPrincipal();
            System.out.println(userDetail);
            List<String> userRoles = userDetail.getAuthorities().stream()
                    .map(Objects::toString).toList();
            log.info("user id is {}", userDetail.getId());
            UserDto userDto = authMapper.findUserById(userDetail.getId());
            log.info("user role is {}", userRoles);
            if (userRoles.contains("ADMIN")) {
                return new BaseResponseMessage<>().setMessage("Welcome to the admin dashboard.")
                        .setCode(String.valueOf(HttpStatus.OK.value()))
                        .setTimestamp(LocalDateTime.now())
                        .setStatus(true)
                        .setData(userDto);
            } else {
                return new BaseResponseMessage<>().setMessage("Welcome to the user feed")
                        .setCode(String.valueOf(HttpStatus.OK.value()))
                        .setTimestamp(LocalDateTime.now())
                        .setStatus(true)
                        .setData(userDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponseMessage<>().setMessage("Exception Occurred,failed to get user.")
                    .setCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);
        }
    }

    @Override
    public BaseResponseMessage refreshToken(Token tokenDto) {
        Authentication authentication = jwtAuthenticationProvider.authenticate(
                new BearerTokenAuthenticationToken(
                        tokenDto.getRefreshToken()
                )
        );
        return new BaseResponseMessage<>().setData(tokenGenerator.tokenResponse(authentication))
                .setCode(String.valueOf(HttpStatus.OK.value()))
                .setTimestamp(LocalDateTime.now())
                .setStatus(true);
    }
}
