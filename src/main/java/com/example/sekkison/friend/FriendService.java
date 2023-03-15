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

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    // 친구초대거절
    public ResponseForm deny(Long friendId) {
        ResponseForm responseForm = new ResponseForm();
        Friend deleteFriend = friendRepository.findById(friendId).orElse(null);

        if (deleteFriend == null) {
            return responseForm.setError("존재하지 않는 친구입니다");
        }

        friendRepository.delete(deleteFriend);
        return responseForm.setSuccess(null);
    }
    // 친구초대보내기
    public ResponseForm send(Friend friend) {
        ResponseForm responseForm = new ResponseForm();
        Friend isExist = friendRepository.findByToIdAndFromId(friend.getToId(), friend.getFromId());
        if (isExist != null) return responseForm.setError("이미 친구초대를 한 상태입니다");
        friend.setIsAccepted(false);
        friendRepository.save(friend);
        return responseForm.setSuccess(null);
    }
    // 친구초대수락
    public ResponseForm accept(Long friendId) {
        ResponseForm responseForm = new ResponseForm();
        Friend acceptedFriend = friendRepository.findById(friendId).orElse(null);

        if (acceptedFriend == null) {
            return responseForm.setError("존재하지 않는 친구초대입니다.");
        }

        acceptedFriend.setIsAccepted(true);
        friendRepository.save(acceptedFriend);

        Friend friend = Friend.builder()
                .fromId(acceptedFriend.getToId())
                .toId(acceptedFriend.getFromId())
                .isAccepted(true)
                .build();
        friendRepository.save(friend);

        return responseForm.setSuccess(null);
    }

    // 친구목록
    public ResponseForm friendList(Long userId) {
        ResponseForm responseForm = new ResponseForm();
        List<Friend> list = friendRepository.findByToIdAndIsAccepted(userId, true);

        List<User> list2 = new ArrayList<>();

        for (Friend friend : list) {
            Long friendId = friend.getFromId();
            User user = userRepository.findById(friendId).orElse(null);
            user.setMemo(friend.getMemo());
            list2.add(user);
        }

        list2.sort(new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user1.getName().compareTo(user2.getName());
            }
        });

        return responseForm.setSuccess(list2);
    }

    public ResponseForm memo(Long fromId, Long toId, String memo) {
        ResponseForm responseForm = new ResponseForm();
        Friend friend = friendRepository.findByToIdAndFromId(fromId, toId);

        if (friend == null) return responseForm.setError("해당 유저가 없습니다.");

        if (friend.getIsAccepted() == false) {
            return responseForm.setError("친구가 아닙니다");
        } else {
            friend.setMemo(memo);
            friendRepository.save(friend);
            return responseForm.setError("작성 성공");
        }
    }
}
