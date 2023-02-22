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
    @DeleteMapping("")
    public ResponseForm deny(Long friendId) {
        return friendService.deny(friendId);
    }

    @ResponseBody
    @PostMapping("")
    public ResponseForm send(Friend friend) {
        return friendService.send(friend);
    }

    @ResponseBody
    @PostMapping("/accept")
    public ResponseForm accept(Long friendId) {
        return friendService.accept(friendId);
    }

    @ResponseBody
    @GetMapping("/list")
    public ResponseForm list(Long userId) {
        return friendService.friendList(userId);
    }

}
