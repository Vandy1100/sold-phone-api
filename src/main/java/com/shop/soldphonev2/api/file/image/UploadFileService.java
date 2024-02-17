package com.shop.soldphonev2.api.file.image;


import com.shop.soldphonev2.api.file.image.web.FileImageResponseDto;
import com.shop.soldphonev2.base.BaseResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**

 This is an interface for defining methods that handle file upload operations.
 */
public interface UploadFileService {

    /**

     Inserts a new file into the system.
     @param file the file to be inserted.
     @return BaseResponseMessage indicating success or failure of the operation.
     @throws Exception if there is an error inserting the file.
     */
    BaseResponseMessage<?> insertFile(MultipartFile file) throws Exception;
    /**

     Selects all files currently in the system.
     @return BaseResponseMessage containing a list of FileImageResponseDto objects.
     */
    BaseResponseMessage<List<FileImageResponseDto>> selectAllFile();
    /**

     Deletes a file from the system.
     @param filename the name of the file to be deleted.
     @return BaseResponseMessage indicating success or failure of the operation.
     */
    BaseResponseMessage<FileImage> deleteFile(String filename);
    /**

     Selects a specific file by its unique identifier.
     @param uuid the unique identifier of the file to select.
     @return BaseResponseMessage containing a FileImageResponseDto object.
     */
    BaseResponseMessage<FileImageResponseDto> SelectFileByUuid(String uuid);

}
