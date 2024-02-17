package com.shop.soldphonev2.api.file.image;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface FileImageMapper {
    @InsertProvider(FileImageProvider.class)
    void insertFile(@Param("f") FileImage fileImage);
    @DeleteProvider(FileImageProvider.class)
    void deleteFile(@Param("name")String name);
    @SelectProvider(FileImageProvider.class)
    FileImage selectedFileByUuid(@Param("uuid")String uuid);
    @SelectProvider(FileImageProvider.class)
    List<FileImage> selectedAllFile();
}
