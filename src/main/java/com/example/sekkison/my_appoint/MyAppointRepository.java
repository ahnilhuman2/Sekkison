package com.example.sekkison.my_appoint;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyAppointRepository extends JpaRepository<MyAppoint, Long> {
    MyAppoint findByUser_idAndAppoint_id(Long userId, Long appointId);

    List<MyAppoint> findByAppoint_id(Long appointId);
}
