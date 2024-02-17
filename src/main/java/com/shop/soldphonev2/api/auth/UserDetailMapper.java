package com.shop.soldphonev2.api.auth;

import com.shop.soldphonev2.api.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDetailMapper {
    @Select("select * from users where email like #{email}")
    @Results({
            @Result(column = "user_id", property = "id"),
            @Result(column = "is_verified", property = "verified"),
            @Result(property = "roles", column = "user_id", many = @Many(select = "findRolesByUserId"))
    })
    Auth loadUserByUsername(String email);

    @SelectProvider(AuthProvider.class)
    List<String> findRolesByUserId(int id);
}
