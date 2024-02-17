package com.shop.soldphonev2.api.method;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class MethodProvider implements ProviderMethodResolver {
    public String insertMethod(){
        return new SQL(){{
            INSERT_INTO("methods");
            VALUES("uuid","#{method.uuid}");
            VALUES("method_account_numbers","#{method.methodAccountNumber}");
            VALUES("method_types","#{method.methodType}");
            VALUES("method_names","#{method.methodName}");
            VALUES("descriptions","#{method.description}");
            VALUES("method_link","#{method.methodLink}");
        }}.toString();
    }
    public String selectAllMethods(){
     return new SQL(){{
         SELECT("*");
         FROM("methods");
     }}.toString();
    }
    public String selectMethodUuid(){
        return new SQL(){{
            SELECT("*");
            FROM("methods");
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
    public String updateMethod(){
        return new SQL(){{
            UPDATE("methods");
            SET("method_account_numbers = #{method.methodAccountNumber}");
            SET("method_types = #{method.methodType}");
            SET("method_names = #{method.methodName}");
            SET("descriptions = #{method.description}");
            SET("method_link = #{method.methodLink}");
            WHERE("uuid = #{uuid}");
        }}.toString();
    }
    public String deleteMethod(){
        return new SQL(){{
            DELETE_FROM("methods");
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
    public String selectMethodType(){
        return new SQL(){{
            SELECT("*");
            FROM("methods");
            WHERE("method_id=#{id}");
        }}.toString();
    }
}
