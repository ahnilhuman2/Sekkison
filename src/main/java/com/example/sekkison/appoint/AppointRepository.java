package com.example.sekkison.appoint;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointRepository extends JpaRepository<Appoint, Long> {
    List<Appoint> findByTitleContainingAndIs_publicAndIs_recruit(String search, Boolean isPublic, Boolean isRecruit, PageRequest paging);
}
