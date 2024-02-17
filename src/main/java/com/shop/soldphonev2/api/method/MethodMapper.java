package com.shop.soldphonev2.api.method;

import com.shop.soldphonev2.api.model.Model;
import com.shop.soldphonev2.api.model.ModelProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MethodMapper {
    @InsertProvider(MethodProvider.class)
    void insertMethod(@Param("method") Method method);
    @SelectProvider(MethodProvider.class)
    @Results({
            @Result(column = "method_id",property = "id"),
            @Result(column = "method_account_numbers",property = "methodAccountNumber"),
            @Result(column = "method_names",property = "methodName"),
            @Result(column = "method_types",property = "methodType"),
            @Result(column = "descriptions",property = "description"),
            @Result(column = "method_link",property = "methodLink")
    })
    List<Method> selectAllMethods();
    @SelectProvider(MethodProvider.class)
    @Results({
            @Result(column = "method_id",property = "id"),
            @Result(column = "method_account_numbers",property = "methodAccountNumber"),
            @Result(column = "method_names",property = "methodName"),
            @Result(column = "method_types",property = "methodType"),
            @Result(column = "descriptions",property = "description"),
            @Result(column = "method_link",property = "methodLink")
    })
    Method selectMethodUuid(String uuid);

    @SelectProvider(MethodProvider.class)
    @Results({
            @Result(column = "method_id",property = "id"),
            @Result(column = "method_account_numbers",property = "methodAccountNumber"),
            @Result(column = "method_names",property = "methodName"),
            @Result(column = "method_types",property = "methodType"),
            @Result(column = "descriptions",property = "description"),
            @Result(column = "method_link",property = "methodLink")
    })
    Method selectMethodType(Integer id);
    @UpdateProvider(MethodProvider.class)
    void updateMethod(@Param("method") Method method, String uuid);

    @DeleteProvider(MethodProvider.class)
    void deleteMethod(String uuid);
}
