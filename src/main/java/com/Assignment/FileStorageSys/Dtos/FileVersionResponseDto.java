package com.Assignment.FileStorageSys.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileVersionResponseDto {
    private Long id;
    private String versionName;
    private String filePath;
    private LocalDateTime uploadTime;
    private Long fileId;
}
