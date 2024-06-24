package com.Assignment.FileStorageSys.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "shared_files")
@Getter
@Setter
public class SharedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File file;

    @ManyToOne
    @JoinColumn(name = "shared_by", nullable = false)
    private User sharedBy;

    @ManyToOne
    @JoinColumn(name = "shared_with", nullable = false)
    private User sharedWith;

    @Column(nullable = false)
    private LocalDateTime sharedAt;
}
