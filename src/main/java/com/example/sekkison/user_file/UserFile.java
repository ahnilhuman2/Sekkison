package com.example.sekkison.user_file;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_files")
@Data
public class UserFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "file", unique = true)
    private String file;
}
