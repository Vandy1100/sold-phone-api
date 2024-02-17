package com.shop.soldphonev2.api.user;

import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserMapper userMapper;
    @Override
    public BaseResponseMessage<List<User>> selectAllUser() {
        List<User> userList = userMapper.selectAll();
        return new BaseResponseMessage<List<User>>().setMessage("You retrieve data")
                .setStatus(true)
                .setCode(String.valueOf(HttpStatus.OK.value()))
                .setTimestamp(LocalDateTime.now())
                .setData(userList);
    }
}
