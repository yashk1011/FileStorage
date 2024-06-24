package com.Assignment.FileStorageSys.Controllers;

import com.Assignment.FileStorageSys.Dtos.*;
import com.Assignment.FileStorageSys.Models.File;
import com.Assignment.FileStorageSys.Models.FileVersion;
import com.Assignment.FileStorageSys.Services.FileService;
import com.Assignment.FileStorageSys.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileController {

    private FileService fileService;
    private UserService userService;

    @Autowired
    public FileController(@Lazy FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileResponseDto> uploadFile(@ModelAttribute FileRequestDto fileRequestDto) throws IOException {
        File savedFile = fileService.uploadFile(fileRequestDto.getFile(), fileRequestDto.getUsername());
        FileResponseDto responseDto = mapToFileResponseDto(savedFile);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileResponseDto> getFileById(@PathVariable Long id) {
        File file = fileService.getFile(id);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        FileResponseDto responseDto = mapToFileResponseDto(file);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/{fileId}/upload-version")
    public ResponseEntity<FileVersionResponseDto> uploadFileVersion(@PathVariable Long fileId, @ModelAttribute FileVersionRequestDto fileVersionRequestDto) throws IOException {
        FileVersion savedFileVersion = fileService.uploadFileVersion(fileVersionRequestDto.getFile(), fileId, fileVersionRequestDto.getVersionName());
        FileVersionResponseDto responseDto = mapToFileVersionResponseDto(savedFileVersion);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{fileId}/versions")
    public ResponseEntity<List<FileVersionResponseDto>> getFileVersions(@PathVariable Long fileId) {
        List<FileVersion> fileVersions = fileService.getVersions(fileId);
        List<FileVersionResponseDto> responseDtos = fileVersions.stream().map(this::mapToFileVersionResponseDto).collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    private FileResponseDto mapToFileResponseDto(File file) {
        FileResponseDto dto = new FileResponseDto();
        dto.setId(file.getId());
        dto.setFileName(file.getFileName());
        dto.setFileType(file.getFileType());
        dto.setFilePath(file.getFilePath());
        dto.setUploadTime(file.getUploadTime());
        dto.setOwnerUsername(file.getOwner().getUsername());
        return dto;
    }

    private FileVersionResponseDto mapToFileVersionResponseDto(FileVersion fileVersion) {
        FileVersionResponseDto dto = new FileVersionResponseDto();
        dto.setId(fileVersion.getId());
        dto.setVersionName(fileVersion.getVersionName());
        dto.setFilePath(fileVersion.getFilePath());
        dto.setUploadTime(fileVersion.getUploadTime());
        dto.setFileId(fileVersion.getFile().getId());
        return dto;
    }
}
