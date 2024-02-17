package com.shop.soldphonev2.api.file.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileImage {
    private int id;
    private String uuid;
    private String filepath;
    private String name;
    private Long size;
    private String extension;
    private boolean isDeleted;
}
