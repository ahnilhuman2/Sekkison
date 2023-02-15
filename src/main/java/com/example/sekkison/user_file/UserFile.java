package com.example.sekkison.user_file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_files")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "file")
    private String file;
}
