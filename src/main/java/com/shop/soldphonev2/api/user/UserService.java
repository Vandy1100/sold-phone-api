package com.shop.soldphonev2.api.user;

import com.shop.soldphonev2.base.BaseResponseMessage;

import java.util.List;

public interface UserService {
    BaseResponseMessage<List<User>> selectAllUser();
}
