package com.example.sekkison.appoint;

import com.example.sekkison.common.C;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointRepository extends JpaRepository<Appoint, Long> {
    Page<Appoint> findByIsPublicAndIsRecruitAndTitleContains(
            Boolean isPublic, Boolean isRecruit, String search, Pageable paging);

    Page<Appoint> findByIsPublicAndIsRecruitAndTypeAndTitleContainsAndDdayAfter(
            Boolean isPublic, Boolean isRecruit, C.appointType isFtf, String search, LocalDateTime now, Pageable pageable);
}
