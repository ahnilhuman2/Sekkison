package com.example.sekkison.my_appoint;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyAppointRepository extends JpaRepository<MyAppoint, Long> {
    MyAppoint findByUser_idAndAppoiny_id(Long userId, Long appointId);
}
