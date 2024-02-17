package com.shop.soldphonev2.api.file.image;

import com.shop.soldphonev2.api.file.image.web.FileImageDto;
import com.shop.soldphonev2.api.file.image.web.FileImageResponseDto;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UploadFileServiceImp implements UploadFileService {
    @Value("${file.server-path}")
    private String likeFilePath;
    @Value("${file.link-path}")
    private String linkPath;

    private String generateServer;
    private final List<String> ALLOWED_EXTENSION = List.of("jpg", "png", "jpeg");
    private final long MAX_FILE_SIZE = 1024 * 1024 * 5;//5MB

    private final FileImageMapper fileImageMapper;
    private final FileImageMapStruct fileImageMapStruct;
    Path fileLocationStorage;
    public String uploadFile(MultipartFile file) {
        fileLocationStorage=Paths.get(likeFilePath);

        String filename = file.getOriginalFilename();

        String[] fileCompartment = filename.split("\\.");
        filename = UUID.randomUUID() + "." + fileCompartment[1];

        Path resolvePath = fileLocationStorage.resolve(filename);

        try {
            Files.copy(file.getInputStream(), resolvePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return filename;
    }
    public String uploadFileGenerate(MultipartFile file) {
        fileLocationStorage=Paths.get(generateServer);
        System.out.println(fileLocationStorage);
        String filename = file.getOriginalFilename();

        String[] fileCompartment = filename.split("\\.");
        filename = UUID.randomUUID() + "." + fileCompartment[1];

        Path resolvePath = fileLocationStorage.resolve(filename);

        try {
            Files.copy(file.getInputStream(), resolvePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return filename;
    }

    @Override
    public BaseResponseMessage<?> insertFile(MultipartFile file) throws Exception{
        FileImage fileImage=new FileImage();
        String uuid =UUID.randomUUID().toString();
        //file empty
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }
        //file size
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new MaxUploadSizeExceededException(MAX_FILE_SIZE);
        }

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if (!ALLOWED_EXTENSION.contains(extension.toLowerCase())) {
            throw new IllegalArgumentException("Allowed extension are 'jpg','jpeg','png'");
        }
        try {
            String filename = uploadFile(file);
            FileImageDto response = new FileImageDto().setFileName(filename)
                    .setFileType(file.getContentType())
                    .setSize(file.getSize());
            fileImage.setFilepath(linkPath + filename);
            fileImage.setName(filename);
            fileImage.setUuid(uuid);
            fileImage.setSize(file.getSize());
            fileImage.setExtension(file.getContentType());
            fileImageMapper.insertFile(fileImage);
            return new BaseResponseMessage<>().setData(response)
                    .setCode(String.valueOf(HttpStatus.OK.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(true)
                    .setMessage("File has been uploaded successfully.");
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResponseMessage<>().setMessage("Failed to insert file!! ")
                    .setStatus(false)
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @Override
    public BaseResponseMessage<List<FileImageResponseDto>> selectAllFile() {
        try {
            List<FileImage> fileImageList = fileImageMapper.selectedAllFile();
            List<FileImageResponseDto> list = fileImageMapStruct.selectAllFile(fileImageList);
            return new BaseResponseMessage<List<FileImageResponseDto>>()
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(true)
                    .setMessage("File has been retrieved successfully.").setData(list)
                    .setCode(String.valueOf(HttpStatus.OK.value()));
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponseMessage<List<FileImageResponseDto>>()
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setStatus(false)
                    .setTimestamp(LocalDateTime.now())
                    .setMessage("Exception occurred,failed to get files!!!");
        }
    }

    @Override
    public BaseResponseMessage<FileImage> deleteFile(String filename) {
        Path imageLocation = Paths.get(likeFilePath);
        List<File> allFile = List.of(imageLocation.toFile().listFiles());
        File deleteFile = allFile.stream().filter(file -> file.getName().equals(filename))
                .findFirst().orElse(null);
        try {
            if (deleteFile != null) {
                fileImageMapper.deleteFile(filename);
                Files.delete(deleteFile.toPath());
                return new BaseResponseMessage<FileImage>().setMessage("File has been deleted successfully")
                        .setTimestamp(LocalDateTime.now())
                        .setCode(String.valueOf(HttpStatus.OK.value()))
                        .setStatus(true);
            } else {
                return new BaseResponseMessage<FileImage>().setMessage("File with " + filename + " doesn't exist")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .setTimestamp(LocalDateTime.now())
                        .setStatus(false);
            }
        } catch (Exception ex) {
            System.out.println("Error deleted " + ex.getMessage());
            return new BaseResponseMessage<FileImage>()
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setStatus(false)
                    .setTimestamp(LocalDateTime.now())
                    .setMessage("Exception occurred ! failed to delete file!!") ;
        }
    }

    @Override
    public BaseResponseMessage<FileImageResponseDto> SelectFileByUuid(String uuid) {
        try {
           FileImage fileImage= fileImageMapper.selectedFileByUuid(uuid);
           FileImageResponseDto fileImageResponseDto=fileImageMapStruct.selectFileByUuid(fileImage);
            return new BaseResponseMessage<FileImageResponseDto>().setData(fileImageResponseDto)
                    .setCode(String.valueOf(HttpStatus.OK.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(true)
                    .setMessage("File has been retrieved successfully.");
        }catch (Exception e){
            return new BaseResponseMessage<FileImageResponseDto>()
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false)
                    .setMessage("Exception occurred ! failed to select file.");        }
    }

}
