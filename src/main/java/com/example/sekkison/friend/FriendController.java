package com.example.sekkison.friend;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @ResponseBody
    @DeleteMapping("/{friendId}")
    // 친구초대 거절
    public ResponseForm deny(@PathVariable("friendId") Long friendId) {
        return friendService.deny(friendId);
    }

    @ResponseBody
    @PostMapping("")
    // 친구초대 보내기
    public ResponseForm send(Friend friend) {
        return friendService.send(friend);
    }

    @ResponseBody
    @PostMapping("/accept/{friendId}")
    // 친구초대 수락
    public ResponseForm accept(@PathVariable("friendID") Long friendId) {
        return friendService.accept(friendId);
    }

    @ResponseBody
    @GetMapping("/list/{userId}")
    // 친구목록
    public ResponseForm list(@PathVariable("userId") Long userId) {
        return friendService.friendList(userId);
    }

}
