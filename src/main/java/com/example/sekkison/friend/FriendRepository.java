package com.example.sekkison.friend;

import com.example.sekkison.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<User> findByTo_idAndIs_accepted(User userId, boolean b);
}
