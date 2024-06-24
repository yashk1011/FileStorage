package com.Assignment.FileStorageSys.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileVersionRequestDto {
    private MultipartFile file;
    private String versionName;
}
