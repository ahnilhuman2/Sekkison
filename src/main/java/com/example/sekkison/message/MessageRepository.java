package com.example.sekkison.message;

import com.example.sekkison.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<User> findByTo_idAndIs_accepted(User userId, boolean b);
}
