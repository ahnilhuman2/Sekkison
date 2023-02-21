package com.example.sekkison.user;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseBody
    @PostMapping("")
    public ResponseForm join(@RequestBody @Validated User user) {

        return userService.register(user);
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseForm login(@Validated User user, HttpSession session) {

        return userService.login(user, session);
    }

    @GetMapping("")
    public ResponseForm returnUser(Long userId) {

        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public ResponseForm updateUser(@Validated @PathVariable("userId") Long userId, User user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("")
    public ResponseForm deleteUser(@Validated User user, Long userId) {
        return userService.deleteUser(user, userId);
    }

    @GetMapping("/duplicated/{parameter}")
    public ResponseForm duplicateUser(@Validated @PathVariable("parameter") Integer parameter, String str) {
        return userService.duplicate(str, parameter);
    }

    @GetMapping("/my_list/{userId}/{parameter}")
    public ResponseForm myList(
            @PathVariable("userId") Long userId,
            @PathVariable("parameter") Integer parameter) {
        return userService.myList(userId, parameter);
    }

    @GetMapping("/search")
    public ResponseForm search(String str, Long userId) {
        return userService.searchUser(str, userId);
    }
}
