package com.example.sekkison.user;

import com.example.sekkison.common.ResponseForm;
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

    public boolean isExist(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) return true;
        return false;
    }

    public ResponseForm register(User user) {
        String username = user.getUsername().toUpperCase(Locale.ROOT);
        String password = user.getPassword();
        String name = user.getName();
        String phone = user.getPhone();
        char gender = user.getGender();

        Pattern pattern = Pattern.compile("^[0-9]*$");

        ResponseForm responseForm = new ResponseForm();

        if (username.length() < 4 || username.length() >10) {
            responseForm.setError("아이디는 4자 이상 10자 이하여야 합니다", false);
            return responseForm;
        }

        if (!Pattern.matches("^[a-zA-Z0-9]*$", username)) {
            responseForm.setError("아이디는 영문자와 숫자만 포함되어야 합니다", false);
            return responseForm;
        }
        // 비밀번호 영어 및 숫자를 허용하며, 숫자키와 관련된 특수문자만 허용한다
        if (!Pattern.matches("^[a-zA-Z\\d`~!@#$%^&*()-_=+]{8,16}$", username)) {
            responseForm.setError("비밀번호는 8자 이상 16자 이하여야 합니다", false);
            return responseForm;
        }

        if (!Pattern.matches("^[가-힣]{2,4}$", name)) {
            responseForm.setError("이름은 한글표기, 2-4자여야 합니다", false);
            return responseForm;
        }

        if (!Pattern.matches("^01([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$", phone)) {
            responseForm.setError("전화번호는 - 제외 11자리로 입력해주세요", false);
            return responseForm;
        }

        if (gender != 'M' || gender != 'F') {
            responseForm.setError("성별은 M이나 F로 입력해주세요", false);
            return responseForm;
        }

        user = userRepository.save(user);
        responseForm.setSuccess(true, null);

        return responseForm;


    }
}
