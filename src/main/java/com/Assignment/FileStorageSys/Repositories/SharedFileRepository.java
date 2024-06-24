package com.Assignment.FileStorageSys.Repositories;

import com.Assignment.FileStorageSys.Models.File;
import com.Assignment.FileStorageSys.Models.SharedFile;
import com.Assignment.FileStorageSys.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SharedFileRepository extends JpaRepository<SharedFile, Long> {
    List<SharedFile> findBySharedWith(User user);
    void deleteByFileAndSharedWith(File file, User sharedWith);
}
