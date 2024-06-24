package com.Assignment.FileStorageSys.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "file_versions")
@Getter
@Setter
public class FileVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String versionName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private LocalDateTime uploadTime;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File file;
}

