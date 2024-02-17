package com.shop.soldphonev2.api.auth;

import com.shop.soldphonev2.api.auth.web.RoleResponseDto;
import com.shop.soldphonev2.api.auth.web.UserDto;
import com.shop.soldphonev2.api.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthMapper {
    @InsertProvider(AuthProvider.class)
    @Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "register.id")
    boolean createUser(@Param("register") User user);
    @Select("SELECT role_id FROM roles WHERE role_name=#{role}")
    @Results({
            @Result(property = "id",column = "role_id")
    })
    Role findRoleByName(@Param("role") String role);
    @InsertProvider(type = AuthProvider.class)
    boolean createUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Select("SELECT * FROM users WHERE user_id=#{id}")
    @Results({
            @Result(column = "user_id",property = "id"),
            @Result(column = "user_id", property = "roles", many = @Many(select = "findUserRoles"))
    })
    UserDto findUserById(int id);
    @SelectProvider(AuthProvider.class)
    @Results({
            @Result(column = "role_name",property = "role"),
    })
    List<RoleResponseDto> findUserRoles();
}
