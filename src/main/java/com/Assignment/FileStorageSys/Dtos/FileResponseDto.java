package com.Assignment.FileStorageSys.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileResponseDto {
    private Long id;
    private String fileName;
    private String fileType;
    private String filePath;
    private LocalDateTime uploadTime;
    private String ownerUsername;
}
