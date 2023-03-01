package com.example.sekkison.friend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByToIdAndIsAccepted(Long userId, boolean b);

    Friend findByToIdAndFromIdAndIsAccepted(Long toId, Long fromId, boolean b);
}
