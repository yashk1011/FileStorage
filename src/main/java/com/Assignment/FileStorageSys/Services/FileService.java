package com.Assignment.FileStorageSys.Services;

import com.Assignment.FileStorageSys.Models.File;
import com.Assignment.FileStorageSys.Models.FileVersion;
import com.Assignment.FileStorageSys.Models.SharedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    File uploadFile(MultipartFile file, String username) throws IOException;

    File getFile(Long id);

    List<File> getFilesByUsername(String username);

    List<SharedFile> getSharedFiles(String username);

    FileVersion uploadFileVersion(MultipartFile file, Long fileId, String versionName) throws IOException;

    List<FileVersion> getVersions(Long fileId);
}

