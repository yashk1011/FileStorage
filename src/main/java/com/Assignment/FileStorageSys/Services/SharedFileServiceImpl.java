package com.Assignment.FileStorageSys.Services;

import com.Assignment.FileStorageSys.Models.File;
import com.Assignment.FileStorageSys.Models.SharedFile;
import com.Assignment.FileStorageSys.Models.User;
import com.Assignment.FileStorageSys.Repositories.SharedFileRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SharedFileServiceImpl implements SharedFileService {

    private SharedFileRepository sharedFileRepository;

    public SharedFileServiceImpl(SharedFileRepository sharedFileRepository) {
        this.sharedFileRepository = sharedFileRepository;
    }

    @Override
    public SharedFile shareFileWithUser(File file, User sharedBy, User sharedWith) {
        SharedFile sharedFile = new SharedFile();
        sharedFile.setFile(file);
        sharedFile.setSharedBy(sharedBy);
        sharedFile.setSharedWith(sharedWith);
        sharedFile.setSharedAt(LocalDateTime.now());
        return sharedFileRepository.save(sharedFile);
    }

    @Override
    public List<SharedFile> getFilesSharedWithUser(User user) {
        return sharedFileRepository.findBySharedWith(user);
    }

    @Override
    public void revokeSharing(File file, User sharedWith) {
        sharedFileRepository.deleteByFileAndSharedWith(file, sharedWith);
    }
}


