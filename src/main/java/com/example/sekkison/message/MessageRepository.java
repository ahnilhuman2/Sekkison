package com.example.sekkison.message;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByToId(Long userId);
    List<Message> findByToId(Long userId, Sort sort);
}
