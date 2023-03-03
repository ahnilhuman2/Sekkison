package com.example.sekkison.user_file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserFileRepository extends JpaRepository<UserFile, Long> {
    Optional<UserFile> findByUserId(Long userId);
}
