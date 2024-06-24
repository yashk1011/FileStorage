package com.Assignment.FileStorageSys.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharedFileRequestDto {
    private Long fileId;
    private String sharedWithUsername;
    private String sharedByUsername;
}
