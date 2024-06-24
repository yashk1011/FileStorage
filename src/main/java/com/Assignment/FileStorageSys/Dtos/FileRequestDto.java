package com.Assignment.FileStorageSys.Dtos;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileRequestDto {
    private MultipartFile file;
    private String username;
}
