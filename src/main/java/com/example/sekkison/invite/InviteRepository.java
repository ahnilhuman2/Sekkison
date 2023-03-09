package com.example.sekkison.invite;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InviteRepository extends JpaRepository<Invite, Long> {
    List<Invite> findByToId(Long userId);

    List<Invite> findByAppointId(Long appointId);

    List<Invite> findByToIdAndAppointId(Long userId, Long appointId);
}
