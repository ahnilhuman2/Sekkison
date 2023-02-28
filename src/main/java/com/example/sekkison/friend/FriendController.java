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
    public ResponseForm deny(@PathVariable("friendId") Long friendId) {
        return friendService.deny(friendId);
    }

    @ResponseBody
    @PostMapping("")
    public ResponseForm send(Friend friend) {
        return friendService.send(friend);
    }

    @ResponseBody
    @PostMapping("/accept/{friendId}")
    public ResponseForm accept(@PathVariable("friendID") Long friendId) {
        return friendService.accept(friendId);
    }

    @ResponseBody
    @GetMapping("/list/{userId}")
    public ResponseForm list(@PathVariable("userId") Long userId) {
        return friendService.friendList(userId);
    }

}
