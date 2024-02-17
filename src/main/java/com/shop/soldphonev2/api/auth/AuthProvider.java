package com.shop.soldphonev2.api.auth;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class AuthProvider implements ProviderMethodResolver {
    public static String createUser(){
        return new SQL(){{
            INSERT_INTO("users");
            VALUES("uuid","#{register.uuid}");
            VALUES("username","#{register.username}");
            VALUES("email","#{register.email}");
            VALUES("password","#{register.password}");
        }}.toString();
    }
    public String createUserRole() {
        return new SQL() {{
            INSERT_INTO("users_roles");
            VALUES("user_id", "#{userId}");
            VALUES("role_id", "#{roleId}");
        }}.toString();
    }
    public static String findRolesByUserId(){
        return new SQL(){{
            SELECT("role_name");
            FROM("users_roles");
            INNER_JOIN("roles on roles.role_id = users_roles.role_id");
            WHERE("user_id= #{id}");
        }}.toString();
    }
    public static String findUserRoles(){
        return new SQL(){{
            SELECT("role_name");
            FROM("users_roles");
            INNER_JOIN("roles ON users_roles.role_id=roles.role_id");
            WHERE("user_id=#{id}");
        }}.toString();
    }
}
