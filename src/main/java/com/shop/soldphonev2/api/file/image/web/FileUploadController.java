package com.shop.soldphonev2.api.file.image.web;


import com.shop.soldphonev2.api.file.image.UploadFileService;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class FileUploadController {
    private final UploadFileService uploadFileService;

    //size profile image
    @PostMapping
    public BaseResponseMessage<?> fileUpload(@RequestParam("file") MultipartFile file)throws Exception{
       return  uploadFileService.insertFile(file);
    }
    @GetMapping
    public BaseResponseMessage<?> selectAllFile(){
        return uploadFileService.selectAllFile();
    }
    @DeleteMapping("/{filename}")
    public BaseResponseMessage<?> deleteSingleFile(@PathVariable String filename){
        return uploadFileService.deleteFile(filename);
    }
    @GetMapping("/{uuid}")
    public BaseResponseMessage<?> selectFileByUuid(@PathVariable("uuid")String uuid){
        return uploadFileService.SelectFileByUuid(uuid);
    }
}
