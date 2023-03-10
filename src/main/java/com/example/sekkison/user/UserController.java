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
    // 회원가입
//    public ResponseForm join(@RequestBody @Validated User user) {
    public ResponseForm join(User user) {
        return userService.register(user);
    }

    @ResponseBody
    @PostMapping("/login")
    // 로그인
    public ResponseForm login(User user, HttpSession session) {
        return userService.login(user, session);
    }

    @ResponseBody
    @GetMapping("/{userId}")
    // id로 user객체 받기
    public ResponseForm returnUser(@PathVariable("userId") Long userId) {

        return userService.getUser(userId);
    }

    @ResponseBody
    @PutMapping("/{userId}")
    // 회원정보수정
    public ResponseForm updateUser(
            @PathVariable("userId") Long userId,
            User user) {
        return userService.updateUser(userId, user);
    }

    @ResponseBody
    @DeleteMapping("/{userId}")
    // 회원탈퇴
    public ResponseForm deleteUser(
            @PathVariable("userId") Long userId) {
        return userService.deleteUser(userId);
    }

    @ResponseBody
    @GetMapping("/duplicated/{parameter}")
    // 아이디, 별명, 전화번호 중복체크
    public ResponseForm duplicateUser(@Validated @PathVariable("parameter") Integer parameter, String str) {
        return userService.duplicate(str, parameter);
    }

    @ResponseBody
    @GetMapping("/my_list/{userId}/{parameter}")
    // 친구 초대 리스트, 약속 초대 리스트
    public ResponseForm myList(
            @PathVariable("userId") Long userId,
            @PathVariable("parameter") Integer parameter) {
        return userService.myList(userId, parameter);
    }
    @ResponseBody
    @GetMapping("/search/{userId}")
    // 유저 검색
    public ResponseForm search(String str, @PathVariable("userId") Long userId) {
        return userService.searchUser(str, userId);
    }
    @ResponseBody
    @GetMapping("/phoneCheck")
    // 휴대폰 인증번호
    public String sendSMS(String phone) { // 휴대폰 문자보내기
        int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성
        userService.certifiedPhoneNumber(phone,randomNumber);
        return Integer.toString(randomNumber);
    }

    @ResponseBody
    @PutMapping("/{param}/{userId}")
    // 회원정보수정
    public ResponseForm updateName(
            @PathVariable("param") Integer param,
            @PathVariable("userId") Long userId,
            String str) {
        return userService.updateName(param, userId, str);
    }

    @ResponseBody
    @GetMapping("/search/invite/{userId}/{appointId}")
    // 약속에 초대할 유저 검색
    public ResponseForm search(
            String str,
            @PathVariable("userId") Long userId,
            @PathVariable("appointId") Long appointId) {
        return userService.searchInviteUser(str, userId, appointId);
    }
}
