package com.example.sekkison.user;

import com.example.sekkison.common.ErrorForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ErrorForm register(User user) {
        String username = user.getUsername().toUpperCase(Locale.ROOT);
        String password = user.getPassword();
        String name = user.getName();
        String phone = user.getPhone();
        Character gender = user.getGender();

        if (username == null) return new ErrorForm("아이디를 입력해주세요", false);
        if (password == null) return new ErrorForm("비밀번호를 입력해주세요", false);
        if (name == null) return new ErrorForm("별명을 입력해주세요", false);
        if (phone == null) return new ErrorForm("전화번호를 입력해주세요", false);
        if (gender == null) return new ErrorForm("성별을 입력해주세요", false);



        if (username.length() < 4 || username.length() >10) {
            ErrorForm errorId = new ErrorForm("아이디는 4자 이상 10자 이하여야 합니다", false);
            return errorId;
        }

        if (!Pattern.matches("^[a-zA-Z0-9]*$", username)) {
            ErrorForm errorId = new ErrorForm("아이디는 영문자와 숫자만 포함되어야 합니다", false);
            return errorId;
        }
        // 비밀번호 영어 및 숫자를 허용하며, 숫자키와 관련된 특수문자만 허용한다
        if (!Pattern.matches("^[a-zA-Z\\d`~!@#$%^&*()-_=+]{8,16}$", password)) {
            ErrorForm errorPw = new ErrorForm("비밀번호는 8자 이상 16자 이하여야 합니다", false);
            return errorPw;
        }

        if (!Pattern.matches("^[가-힣a-zA-Z]{2,10}$", name)) {
            ErrorForm errorName = new ErrorForm("이름은 한글, 영어표기, 2-10자여야 합니다", false);
            return errorName;
        }

        if (!Pattern.matches("^01([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$", phone)) {
            ErrorForm errorPhone = new ErrorForm("전화번호는 - 제외 11자리로 입력해주세요", false);
            return errorPhone;
        }

        if (gender != 'M' && gender != 'F') {
            ErrorForm errorGender = new ErrorForm("성별은 M이나 F로 입력해주세요", false);
            return errorGender;
        }

        user = userRepository.save(user);
        ErrorForm error = new ErrorForm("", true);

        return error;


    }
}
