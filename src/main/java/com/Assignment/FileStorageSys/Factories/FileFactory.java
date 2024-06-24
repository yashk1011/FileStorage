package com.Assignment.FileStorageSys.Factories;
import com.Assignment.FileStorageSys.Models.File;
import com.Assignment.FileStorageSys.Models.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Component
public class FileFactory {
    public File createFile(MultipartFile file, User user, Path filePath) throws IOException {
        File fileEntity = new File();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFilePath(filePath.toString());
        fileEntity.setUploadTime(LocalDateTime.now());
        fileEntity.setOwner(user);
        return fileEntity;
    }
}

