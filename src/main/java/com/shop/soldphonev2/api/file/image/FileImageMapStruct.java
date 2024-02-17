package com.shop.soldphonev2.api.file.image;

import com.shop.soldphonev2.api.file.image.web.FileImageResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileImageMapStruct {
    List<FileImageResponseDto> selectAllFile(List<FileImage > fileImage);
    FileImageResponseDto selectFileByUuid(FileImage fileImage);
}
