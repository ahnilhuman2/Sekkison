package com.example.sekkison.my_appoint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyAppointRepository extends JpaRepository<MyAppoint, Long> {
    MyAppoint findByUserIdAndAppointId(Long userId, Long appointId);

    List<MyAppoint> findByAppointId(Long appointId);

    List<MyAppoint> findByAppointIdAndIsMaster(Long appointId, boolean b);

    Page<MyAppoint> findByUserId(Long userId, Pageable pageable);

    List<MyAppoint> findByUserId(Long userId);
}
