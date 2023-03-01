package com.example.sekkison.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByName(String name);
    User findByPhone(String phone);

    List<User> findByNameContains(String str);
}
