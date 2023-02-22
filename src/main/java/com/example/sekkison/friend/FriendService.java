package com.example.sekkison.friend;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

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
}
