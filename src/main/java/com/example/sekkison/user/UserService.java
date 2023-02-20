package com.example.sekkison.user;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;
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

        if (username == null) return responseForm.setError("아이디를 입력해주세요", false);
        if (password == null) return responseForm.setError("비밀번호를 입력해주세요", false);
        if (name == null) return responseForm.setError("별명을 입력해주세요", false);
        if (phone == null) return responseForm.setError("전화번호를 입력해주세요", false);
        if (gender != 'M' && gender != 'F') return responseForm.setError("성별을 입력해주세요", false);

        if (username.length() < 4 || username.length() >10) {
            responseForm.setError("아이디는 4자 이상 10자 이하여야 합니다", false);
            return responseForm;
        }

        if (!Pattern.matches("^[a-zA-Z0-9]*$", username)) {
            responseForm.setError("아이디는 영문자와 숫자만 포함되어야 합니다", false);
            return responseForm;
        }
        // 비밀번호 영어 및 숫자를 허용하며, 숫자키와 관련된 특수문자만 허용한다
        if (!Pattern.matches("^[a-zA-Z\\d`~!@#$%^&*()-_=+]{8,16}$", password)) {
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

    public ResponseForm login(User user, HttpSession session) {
        String username = user.getUsername();
        String password = user.getPassword();

        User dbUser = userRepository.findByUsername(username);
        ResponseForm responseForm = new ResponseForm();

        if (dbUser == null) {
            return responseForm.setError("일치하는 아이디가 없습니다", false);
        }

        if (!dbUser.getPassword().equals(password)) {
            return responseForm.setError("유효하지 않은 아이디와 비밀번호입니다.", false);
        } else {
            return responseForm.setSuccess(true, "로그인 성공");
        }
    }

    public ResponseForm getUser(Long userId) {
        ResponseForm responseForm = new ResponseForm();
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            responseForm.setError("존재하지 않는 회원입니다", false);
            return responseForm;
        }
        return responseForm.setSuccess(true, user);
    }

    public ResponseForm updateUser(Long userId, User user) {
        String name = user.getName();
        String password = user.getPassword();
        String content = user.getContent();

        User updateUser = userRepository.findById(userId).orElse(null);
        ResponseForm responseForm = new ResponseForm();

        if (name == null && Pattern.matches("^[가-힣]{2,4}$", name)) {
            responseForm.setError("이름은 한글표기, 2-4자여야 합니다", false);
            return responseForm;
        }

        if (password == null && Pattern.matches("^[a-zA-Z\\d`~!@#$%^&*()-_=+]{8,16}$", password)) {
            responseForm.setError("비밀번호는 8자 이상 16자 이하여야 합니다", false);
            return  responseForm;
        }

        updateUser.setName(name);
        updateUser.setPassword(password);
        updateUser.setContent(content);

        userRepository.save(updateUser);
        responseForm.setSuccess(true, null);

        return responseForm;
    }

    public ResponseForm deleteUser(User user, Long userId) {
        ResponseForm responseForm = new ResponseForm();
        User deleteUser = userRepository.findById(userId).orElse(null);

        userRepository.delete(deleteUser);
        responseForm.setSuccess(true, null);

        return responseForm;
    }

    public ResponseForm duplicate(User user) {
        ResponseForm responseForm = new ResponseForm();
        User duplicateUsername = userRepository.findByUsername(user.getUsername());
        User duplicateName = userRepository.findByName(user.getName());
        User duplicatePhone = userRepository.findByPhone(user.getPhone());

        if (duplicateUsername == null) {
            responseForm.setError("이미 존재하는 회원입니다", false);
            return responseForm;
        }

        if (duplicateName == null) {
            responseForm.setError("이미 존재하는 별명입니다", false);
            return responseForm;
        }

        if (duplicatePhone == null) {
            responseForm.setError("이미 존재하는 전화번호입니다", false);
            return responseForm;
        }

        responseForm.setSuccess(true, null);
        return responseForm;
    }
}
