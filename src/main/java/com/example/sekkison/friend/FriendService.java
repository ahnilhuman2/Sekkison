package com.example.sekkison.friend;

import com.example.sekkison.common.ResponseForm;
import com.example.sekkison.user.User;
import com.example.sekkison.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public ResponseForm deny(Long friendId) {
        ResponseForm responseForm = new ResponseForm();
        Friend deleteFriend = friendRepository.findById(friendId).orElse(null);

        if (deleteFriend == null) {
            return responseForm.setError("존재하지 않는 친구입니다", false);
        }

        friendRepository.delete(deleteFriend);
        return responseForm.setSuccess(true, null);
    }

    public ResponseForm send(Friend friend) {
        ResponseForm responseForm = new ResponseForm();
        friend.setIsAccepted(false);
        friendRepository.save(friend);
        return responseForm.setSuccess(true, null);
    }

    public ResponseForm accept(Long friendId) {
        ResponseForm responseForm = new ResponseForm();
        Friend acceptedFriend = friendRepository.findById(friendId).orElse(null);

        if (acceptedFriend == null) {
            responseForm.setError("존재하지 않는 유저입니다.", false);
        }

        acceptedFriend.setIsAccepted(true);
        friendRepository.save(acceptedFriend);

        Friend friend = Friend.builder()
                .fromId(acceptedFriend.getId())
                .toId(acceptedFriend.getFromId())
                .isAccepted(true)
                .build();
        friendRepository.save(friend);

        return responseForm.setSuccess(true, null);
    }


    public ResponseForm friendList(Long userId) {
        ResponseForm responseForm = new ResponseForm();
        List<Friend> list = friendRepository.findByToIdAndIsAccepted(userId, true);

        List<User> list2 = new ArrayList<>();

        for (Friend friend : list) {
            Long friendId = friend.getFromId();
            User user = userRepository.findById(friendId).orElse(null);
            list2.add(user);
        }

        list2.sort(new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user1.getName().compareTo(user2.getName());
            }
        });

        return responseForm.setSuccess(true, list2);
    }
}
