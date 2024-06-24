package com.Assignment.FileStorageSys.Repositories;

import com.Assignment.FileStorageSys.Models.FileVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileVersionRepository extends JpaRepository<FileVersion, Long> {
}
