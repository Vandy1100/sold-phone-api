package com.shop.soldphonev2.api.user.web;

import com.shop.soldphonev2.api.user.UserService;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("")
    public BaseResponseMessage<?> select(){
        return userService.selectAllUser();
    }
}
