package com.example.sekkison.user;

import com.example.sekkison.appoint.Appoint;
import com.example.sekkison.appoint.AppointRepository;
import com.example.sekkison.authority.Authority;
import com.example.sekkison.authority.AuthorityRepository;
import com.example.sekkison.common.ResponseForm;
import com.example.sekkison.friend.Friend;
import com.example.sekkison.friend.FriendRepository;
import com.example.sekkison.invite.Invite;
import com.example.sekkison.invite.InviteRepository;
import com.example.sekkison.my_appoint.MyAppoint;
import com.example.sekkison.my_appoint.MyAppointRepository;
import com.example.sekkison.user_authority.UserAuthority;
import com.example.sekkison.user_authority.UserAuthorityRepository;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final InviteRepository inviteRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final AuthorityRepository authorityRepository;
    private final AppointRepository appointRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MyAppointRepository myAppointRepository;

    // 회원 가입(input user)
    public ResponseForm register(User user) {
        String username = user.getUsername().toUpperCase(Locale.ROOT);
        String password = user.getPassword();
        String name = user.getName();
        String phone = user.getPhone();
        Character gender = user.getGender();

        ResponseForm responseForm = new ResponseForm();

        // 회원기입란이 공백일 경우 setError 리턴
        if (username == null) return responseForm.setError("아이디를 입력해주세요");
        if (password == null) return responseForm.setError("비밀번호를 입력해주세요");
        if (name == null) return responseForm.setError("별명을 입력해주세요");
        if (phone == null) return responseForm.setError("전화번호를 입력해주세요");
        if (gender != 'M' && gender != 'F') return responseForm.setError("성별을 입력해주세요");

        // 가입 아이디 길이 제한
        if (username.length() < 4 || username.length() >10) {
            responseForm.setError("아이디는 4자 이상 10자 이하여야 합니다");
            return responseForm;
        }

        // 가입 아이디 영문자와 숫자만 포함
        if (!Pattern.matches("^[a-zA-Z0-9]*$", username)) {
            responseForm.setError("아이디는 영문자와 숫자만 포함되어야 합니다");
            return responseForm;
        }
        // 비밀번호 영어 및 숫자를 허용하며, 숫자키와 관련된 특수문자만 허용한다
        if (!Pattern.matches("^[a-zA-Z\\d`~!@#$%^&*()-_=+]{8,16}$", password)) {
            responseForm.setError("비밀번호는 8자 이상 16자 이하여야 합니다");
            return responseForm;
        }

        // 별명은 한글표기 2-4자로 제한
        if (!Pattern.matches("^[가-힣]{2,8}$", name)) {
            responseForm.setError("별명은 한글표기, 2-8자여야 합니다");
            return responseForm;
        }

        // 전화번호는 - 제외 11자리 입력
        if (!Pattern.matches("^01([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$", phone)) {
            responseForm.setError("전화번호는 - 제외 11자리로 입력해주세요");
            return responseForm;
        }

        // 성별은 버튼 처리 예정
        if (gender != 'M' && gender != 'F') {
            responseForm.setError("성별은 M이나 F로 입력해주세요");
            return responseForm;
        }

        // 다 통과했을시 Repository save, success 리턴
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Authority authmember = authorityRepository.findByAuth("MEMBER");
        UserAuthority ua = UserAuthority.builder()
                .userId(user.getId())
                .authority(authmember.getId())
                .build();
        userAuthorityRepository.save(ua);
        responseForm.setSuccess(null);

        return responseForm;

    }

    // 유저 로그인
    public ResponseForm login(User user, HttpSession session) {
        ResponseForm responseForm = new ResponseForm();

        String username = user.getUsername();
        String password = user.getPassword();

        User dbUser = userRepository.findByUsername(username);

        // dbUser에 일치하는 유저가 없을시 에러
        if (dbUser == null) {
            return responseForm.setError("일치하는 아이디가 없습니다");
        }

        if (!bCryptPasswordEncoder.matches(password, dbUser.getPassword())) {
            return responseForm.setError("유효하지 않은 아이디와 비밀번호입니다.");
        } else {
            return responseForm.setSuccess(dbUser);
        }

//        if (user != null && bCryptPasswordEncoder.matches(password, dbUser.getPassword())) {
//            return responseForm.setSuccess(dbUser);
//        }
    }

    // user_id를 받아 user객체 리턴하는 함수(mypage나 상대방 정보 볼때 활용)
    public ResponseForm getUser(Long userId) {
        ResponseForm responseForm = new ResponseForm();
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            responseForm.setError("존재하지 않는 회원입니다");
            return responseForm;
        }
        return responseForm.setSuccess(user);
    }

    // 회원정보수정
    public ResponseForm updateUser(Long userId, User user) {
        String name = user.getName();
        String password = user.getPassword();
        String content = user.getContent();

        // 로그인된 userId를 기준으로 update할 유저 검색
        User updateUser = userRepository.findById(userId).orElse(null);
        ResponseForm responseForm = new ResponseForm();

        // 비거나, 한글표기, 2-4자 아닐지 error
        if (name == null || !Pattern.matches("^[가-힣]{2,4}$", name)) {
            responseForm.setError("이름은 한글표기, 2-4자여야 합니다");
            return responseForm;
        }

        // 비거나, 비밀번호 양식 틀릴시 error
        if (password == null || !Pattern.matches("^[a-zA-Z\\d`~!@#$%^&*()-_=+]{8,16}$", password)) {
            responseForm.setError("비밀번호는 8자 이상 16자 이하여야 합니다");
            return  responseForm;
        }

        updateUser.setName(name);
        updateUser.setPassword(password);
        updateUser.setContent(content);

        userRepository.save(updateUser);
        responseForm.setSuccess(null);

        return responseForm;
    }

    // 회원탈퇴
    public ResponseForm deleteUser(Long userId) {
        ResponseForm responseForm = new ResponseForm();
        User deleteUser = userRepository.findById(userId).orElse(null);

        if (deleteUser == null) {
            responseForm.setError("존재하지 않는 회원입니다.");
        }

        userRepository.delete(deleteUser);
        responseForm.setSuccess(null);

        return responseForm;
    }

    // 아이디(0), 별명(1), 전화번호(2) 중복체크
    public ResponseForm duplicate(String str, Integer parameter) {
        ResponseForm responseForm = new ResponseForm();

        // 아이디 중복체크
        if (parameter == 0) {
            User duplicateUsername = userRepository.findByUsername(str);
            if (duplicateUsername != null) {
                responseForm.setError("이미 존재하는 회원입니다");
                return responseForm;
            }
        }

        // 별명 중복체크
        if (parameter == 1) {
            User duplicateName = userRepository.findByName(str);
            if (duplicateName != null) {
                responseForm.setError("이미 존재하는 별명입니다");
                return responseForm;
            }
        }

        // 전화번호 중복체크
        if (parameter == 2) {
            User duplicatePhone = userRepository.findByPhone(str);
            if (duplicatePhone != null) {
                responseForm.setError("이미 존재하는 전화번호입니다");
                return responseForm;
            }
        }

        responseForm.setSuccess(null);
        return responseForm;
    }

    // 친구(0) 초대 리스트, 약속(1) 초대 리스트
    public ResponseForm myList(Long userId, Integer parameter) {
        ResponseForm responseForm = new ResponseForm();

        // userId를 받아 해당 유저가 받은 친구초대 목록 리턴하는 함수(친구 is_accepted=false인것만 표시)
        if (parameter == 0) {
            List<Friend> friends = friendRepository.findByToIdAndIsAccepted(userId, false);
            if (friends.size() == 0 || friends == null)
                return responseForm.setError("친구초대목록이 비어있습니다");
            return responseForm.setSuccess(friends);
        }

        // userId를 받아 해당 유저가 받은 약속초대 목록 리턴하는 함수
        if (parameter == 1) {
            List<Invite> invites = inviteRepository.findByToId(userId);

            if (invites.size() == 0 || invites == null)
                return responseForm.setError("약속초대목록이 비어있습니다");

            List<Appoint> appointList = new ArrayList<>();
            for(Invite i : invites) {
                User u = userRepository.findById(i.getFromId()).orElse(null);
                Appoint a = new Appoint();
                BeanUtils.copyProperties(appointRepository.findById(i.getAppointId()).orElse(null), a);
                a.setMemo(u.getName() + "&" + i.getId());
                a.setCreateAt(i.getCreateAt());
                appointList.add(a);
            }
            return responseForm.setSuccess(appointList);
        }

        return responseForm.setError("실패");
    }

    // 특정 id 의 User 의 Authority (들)을 리턴
    public List<Authority> selectAuthoritiesById(Long userId) {
        // userId로 유저 찾기
        User user = userRepository.findById(userId).orElse(null);

        List<Authority> res = new ArrayList<>();

        // 해당 유저가 없으면 빈 배열 리턴
        if (user == null) return res;

        // userId로 찾은 userAuthority의 auth를 res에 담음
        List<UserAuthority> tmp = userAuthorityRepository.findByUserId(userId);
        for(UserAuthority ua : tmp)
            res.add(authorityRepository.findById(ua.getAuthority()).orElse(null));

        return res;
    }

    // username으로 유저 찾기
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 유저검색
    public ResponseForm searchUser(String str, Long userId) {
        ResponseForm responseForm = new ResponseForm();

        // 입력된 String값을 기준으로 user검색
        List<User> users = userRepository.findByNameContains(str);

        User removeUser = null;
        for(int i = 0; i < users.size(); i++) {
            Long fromId = userId;
            Long toId = users.get(i).getId();
            if (fromId == toId) removeUser = users.get(i);

            Friend f = friendRepository.findByToIdAndFromId(toId, fromId);
            if (f == null) users.get(i).setMemo("X");
            else {
                if (f.getIsAccepted()) users.get(i).setMemo("O");
                else users.get(i).setMemo("-");
            }
        }
        if (removeUser != null) users.remove(removeUser);

        // 없을시 error
        if (users == null || users.size() == 0)
            return responseForm.setError("해당 유저가 없습니다");

        return responseForm.setSuccess(users);
    }

    public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) {
        String api_key = "NCSGGLQGQVSYVL1G";
        String api_secret = "BFPEPU18IRCPMEQXLD4UEQM3FWZMPVUK";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", userPhoneNumber);    // 수신전화번호
        params.put("from", "01055350934");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "sekkison 인증번호는" + "["+randomNumber+"]" + "입니다."); // 문자 내용 입력
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
        } catch (CoolsmsException e) {
        }
    }

    public ResponseForm updateName(Integer param, Long userId, String str) {
        ResponseForm res = new ResponseForm();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return res.setError("해당 유저가 없습니다");

        if (param == 0) {
            User comp = userRepository.findByName(str);
            if (comp != null) return res.setError("이름이 중복됩니다");

            user.setName(str);
        } else if (param == 1) {
            if (str == null) str = "";
            user.setContent(str);
        }
        userRepository.save(user);
        return res.setSuccess(null);
    }

    // 약속에 초대할 유저 검색
    public ResponseForm searchInviteUser(String str, Long userId, Long appointId) {

        // 입력된 String값을 기준으로 user검색
        List<User> users = userRepository.findByNameContains(str);

        List<User> removeUsers = new ArrayList<>();
        for(int i = 0; i < users.size(); i++) {
            Long fromId = userId;
            Long toId = users.get(i).getId();
            if (fromId == toId) removeUsers.add(users.get(i));

            Friend f = friendRepository.findByToIdAndFromId(toId, fromId);
            if (f == null) removeUsers.add(users.get(i));
            else {
                if (f.getIsAccepted()) users.get(i).setMemo("X");
                else removeUsers.add(users.get(i));
            }
        }
        if (removeUsers != null && removeUsers.size() != 0)
            for(User u : removeUsers) users.remove(u);

        // 이미 초대되어 제외할 유저 저장
        List<Invite> invites = inviteRepository.findByAppointId(appointId);
        Set<Long> invited = new HashSet<>();
        for(Invite i : invites) invited.add(i.getToId());

        // 초대중으로 세팅
        for(User u : users)
            if (invited.contains(u.getId()))
                u.setMemo("-");

        // 이미 참가중이라 제외할 유저 저장
        List<MyAppoint> myAppoints = myAppointRepository.findByAppointId(appointId);
        Set<Long> inviting = new HashSet<>();
        for(MyAppoint ma : myAppoints) inviting.add(ma.getUserId());

        // 참가중으로 세팅
        for(User u : users)
            if (inviting.contains(u.getId()))
                u.setMemo("O");

        // 없을시 error
        if (users == null || users.size() == 0)
            return new ResponseForm().setError("해당 유저가 없습니다");

        return new ResponseForm().setSuccess(users);
    }
}
