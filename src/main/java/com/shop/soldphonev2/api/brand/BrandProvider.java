package com.shop.soldphonev2.api.brand;

import org.apache.ibatis.jdbc.SQL;

public class BrandProvider {

    public String insertBrand(Brand brand) {
        return new SQL() {{
            INSERT_INTO("brands");
            VALUES("uuid", "#{brand.uuid}");
            VALUES("brand", "#{brand.brand}");
            VALUES("description", "#{brand.description}");
        }}.toString();
    }

    public String selectAllBrand(){
        return new SQL(){{
            SELECT("*");
            FROM("brands");
        }}.toString();
    }
    public String selectBrandUuid(){
        return new SQL(){{
            SELECT("*");
            FROM("brands");
            WHERE("uuid = #{uuid}");
        }}.toString();

    }
    public String updateBrand(){
        return new SQL(){{
            UPDATE("brands");
            SET("brand = #{brand.brand}");
            SET("description = #{brand.description}");
            WHERE("uuid = #{uuid}");
        }}.toString();
    }
    public String deleteBrand(){
        return new SQL(){{
            DELETE_FROM("brands");
            WHERE("uuid =#{uuid}");
        }}.toString();
    }
}
