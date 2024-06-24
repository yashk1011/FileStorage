package com.Assignment.FileStorageSys.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "files")
@Getter
@Setter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private LocalDateTime uploadTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;


    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileVersion> versions = new ArrayList<>();
}
