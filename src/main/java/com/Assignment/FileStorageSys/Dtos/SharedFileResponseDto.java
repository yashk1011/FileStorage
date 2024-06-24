package com.Assignment.FileStorageSys.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SharedFileResponseDto {
    private Long id;
    private Long fileId;
    private String sharedByUsername;
    private String sharedWithUsername;
    private LocalDateTime sharedAt;
}
