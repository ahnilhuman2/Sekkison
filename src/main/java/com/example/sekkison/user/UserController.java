package com.example.sekkison.user;

import com.example.sekkison.common.ResponseForm;
import com.mysql.cj.protocol.x.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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


}
