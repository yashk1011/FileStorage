package com.Assignment.FileStorageSys.Services;

import com.Assignment.FileStorageSys.Models.File;
import com.Assignment.FileStorageSys.Models.SharedFile;
import com.Assignment.FileStorageSys.Models.User;

import java.util.List;

public interface SharedFileService {
    SharedFile shareFileWithUser(File file, User sharedBy, User sharedWith);
    List<SharedFile> getFilesSharedWithUser(User user);
    void revokeSharing(File file, User sharedWith);
}

