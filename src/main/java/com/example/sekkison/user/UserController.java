package com.example.sekkison.user;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseBody
    @PostMapping("")
    public void join(@RequestBody @Validated User user) {
        userService.register(user);
    }
    @ResponseBody
    @PostMapping("/login")
    public void login(User user) {
        userService.login(user);
    }


}
