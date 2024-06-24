package com.Assignment.FileStorageSys.Repositories;

import com.Assignment.FileStorageSys.Models.File;
import com.Assignment.FileStorageSys.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByOwner(User owner);

}
