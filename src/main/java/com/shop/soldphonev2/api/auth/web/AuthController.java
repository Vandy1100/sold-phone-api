package com.shop.soldphonev2.api.auth.web;

import com.shop.soldphonev2.api.auth.AuthService;
import com.shop.soldphonev2.api.auth.Token;
import com.shop.soldphonev2.base.BaseResponseMessage;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    BaseResponseMessage<?> registers(@RequestBody RegisterDto registerDto) throws MessagingException {
        return authService.register(registerDto);
    }
    @PostMapping("/login")
    public BaseResponseMessage<?> login( @RequestBody LoginDto loginRequest) {
        return authService.login(loginRequest);
    }
    @GetMapping("/me")
    public BaseResponseMessage<?> welcomePage() {
        return authService.getMe();
    }
    @PostMapping("/refresh-token")
    public BaseResponseMessage refreshToken(@RequestBody Token tokenDto) {
        return authService.refreshToken(tokenDto);
    }
}
