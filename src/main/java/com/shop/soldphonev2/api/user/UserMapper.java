package com.shop.soldphonev2.api.user;

import jdk.dynalink.linker.LinkerServices;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from users")
    List<User> selectAll();
}
