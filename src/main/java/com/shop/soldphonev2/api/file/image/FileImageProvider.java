package com.shop.soldphonev2.api.file.image;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class FileImageProvider implements ProviderMethodResolver {
    public static String insertFile(){
        return new SQL(){{
            INSERT_INTO("images");
            VALUES("uuid","#{f.uuid}");
            VALUES("name","#{f.name}");
            VALUES("filepath","#{f.filepath}");
            VALUES("size","#{f.size}");
            VALUES("extension","#{f.extension}");
        }}.toString();
    }
    public static String selectedAllFile(){
        return new SQL(){{
            SELECT("*");
            FROM("images");
        }}.toString();

    }
    public static String deleteFile(){
        return new SQL(){{
            DELETE_FROM("images");
            WHERE("name=#{name}");
        }}.toString();
    }
public static String selectedFileByUuid(){
        return new SQL(){{
            SELECT("*");
            FROM("images");
            WHERE("uuid=#{uuid}");
        }}.toString();
}
}
