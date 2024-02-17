package com.shop.soldphonev2.api.product;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ProductProvider implements ProviderMethodResolver {
    public String insertProduct(){
        return new SQL(){{
            INSERT_INTO("products");
            VALUES("name","#{product.name}");
            VALUES("description","#{product.description}");
            VALUES("stock_quantity","#{product.stockQuantity}");
            VALUES("manufacturer_id","#{product.manufacturerId}");
            VALUES("release_date","#{product.releaseDate}");
            VALUES("image_url","#{product.imageUrl}");
            VALUES("uuid","#{product.uuid}");
            VALUES("price","#{product.price}");
            VALUES("brand_id","#{product.brandId}");
            VALUES("data","#{data}::jsonb");
        }}.toString();
    }
   public String selectAllProduct(){
        return new SQL(){{
            SELECT("*");
            FROM("products");
        }}.toString();
   }
   public String selectProductUuid(){
        return new SQL(){{
            SELECT("*");
            FROM("products");
            WHERE("uuid=#{uuid}");
        }}.toString();
   }
   public String updateProduct(){
        return new SQL(){{
            UPDATE("products");
            SET("name = #{product.name}");
            SET("description = #{product.description}");
            SET("price = #{product.price}");
            SET("stock_quantity = #{product.stockQuantity}");
            SET("image_url = #{product.imageUrl }");
            WHERE("uuid =#{uuid}");
        }}.toString();
   }
   public String deleteProduct(){
        return new SQL(){{
            DELETE_FROM("products");
            WHERE("uuid =#{uuid}");
        }}.toString();
   }
   public String selectRecentProduct(){
        return new SQL(){{
            SELECT("name,description,price,image_url,uuid");
            FROM("products");
            ORDER_BY("product_id DESC");
        }}.toString();
   }
}
