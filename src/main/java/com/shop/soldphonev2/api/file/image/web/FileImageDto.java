package com.shop.soldphonev2.api.file.image.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class FileImageDto {
    private String fileName;
    private String fileType;
    private Long size;
}
