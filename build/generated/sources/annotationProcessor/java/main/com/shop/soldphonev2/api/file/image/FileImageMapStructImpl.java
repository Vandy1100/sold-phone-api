package com.shop.soldphonev2.api.file.image;

import com.shop.soldphonev2.api.file.image.web.FileImageResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-29T11:39:46+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class FileImageMapStructImpl implements FileImageMapStruct {

    @Override
    public List<FileImageResponseDto> selectAllFile(List<FileImage> fileImage) {
        if ( fileImage == null ) {
            return null;
        }

        List<FileImageResponseDto> list = new ArrayList<FileImageResponseDto>( fileImage.size() );
        for ( FileImage fileImage1 : fileImage ) {
            list.add( selectFileByUuid( fileImage1 ) );
        }

        return list;
    }

    @Override
    public FileImageResponseDto selectFileByUuid(FileImage fileImage) {
        if ( fileImage == null ) {
            return null;
        }

        String uuid = null;
        String name = null;
        String filepath = null;
        Long size = null;
        String extension = null;

        uuid = fileImage.getUuid();
        name = fileImage.getName();
        filepath = fileImage.getFilepath();
        size = fileImage.getSize();
        extension = fileImage.getExtension();

        FileImageResponseDto fileImageResponseDto = new FileImageResponseDto( uuid, name, filepath, size, extension );

        return fileImageResponseDto;
    }
}
