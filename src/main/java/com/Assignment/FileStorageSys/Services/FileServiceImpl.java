package com.Assignment.FileStorageSys.Services;

import com.Assignment.FileStorageSys.Factories.FileFactory;
import com.Assignment.FileStorageSys.Models.File;
import com.Assignment.FileStorageSys.Models.FileVersion;
import com.Assignment.FileStorageSys.Models.SharedFile;
import com.Assignment.FileStorageSys.Models.User;
import com.Assignment.FileStorageSys.Repositories.FileRepository;
import com.Assignment.FileStorageSys.Repositories.FileVersionRepository;
import com.Assignment.FileStorageSys.Repositories.SharedFileRepository;
import com.Assignment.FileStorageSys.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private Path root = Paths.get("uploads");
    private FileRepository fileRepository;
    private UserRepository userRepository;
    private FileFactory fileFactory;
    private FileVersionRepository fileVersionRepository;
    private SharedFileRepository sharedFileRepository;

    public FileServiceImpl(FileRepository fileRepository, UserRepository userRepository, FileFactory fileFactory, FileVersionRepository fileVersionRepository, SharedFileRepository sharedFileRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.fileFactory = fileFactory;
        this.fileVersionRepository = fileVersionRepository;
        this.sharedFileRepository = sharedFileRepository;
    }

    @Override
    public File uploadFile(MultipartFile file, String username) throws IOException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        String fileName = file.getOriginalFilename();
        Path filePath = this.root.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        File fileEntity = fileFactory.createFile(file, user, filePath);
        return fileRepository.save(fileEntity);
    }

    @Override
    public File getFile(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    @Override
    public List<File> getFilesByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return fileRepository.findByOwner(user);
    }

    @Override
    public List<SharedFile> getSharedFiles(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return sharedFileRepository.findBySharedWith(user);
    }

    @Override
    public FileVersion uploadFileVersion(MultipartFile file, Long fileId, String versionName) throws IOException {
        File existingFile = fileRepository.findById(fileId).orElseThrow(() -> new RuntimeException("File not found"));
        String fileName = file.getOriginalFilename();
        Path filePath = this.root.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        FileVersion fileVersion = new FileVersion();
        fileVersion.setVersionName(versionName);
        fileVersion.setFilePath(filePath.toString());
        fileVersion.setUploadTime(LocalDateTime.now());
        fileVersion.setFile(existingFile);

        return fileVersionRepository.save(fileVersion);
    }
    @Override
    public List<FileVersion> getVersions(Long fileId) {
        File file = fileRepository.findById(fileId).orElseThrow(() -> new RuntimeException("File not found"));
        return file.getVersions();
    }
}
