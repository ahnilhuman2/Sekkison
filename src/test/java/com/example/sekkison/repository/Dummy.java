package com.example.sekkison.repository;

import com.example.sekkison.appoint.Appoint;
import com.example.sekkison.appoint.AppointRepository;
import com.example.sekkison.authority.Authority;
import com.example.sekkison.authority.AuthorityRepository;
import com.example.sekkison.comment.CommentRepository;
import com.example.sekkison.common.C;
import com.example.sekkison.friend.Friend;
import com.example.sekkison.friend.FriendRepository;
import com.example.sekkison.invite.Invite;
import com.example.sekkison.invite.InviteRepository;
import com.example.sekkison.message.Message;
import com.example.sekkison.message.MessageRepository;
import com.example.sekkison.my_appoint.MyAppoint;
import com.example.sekkison.my_appoint.MyAppointRepository;
import com.example.sekkison.user.User;
import com.example.sekkison.user.UserRepository;
import com.example.sekkison.user_authority.UserAuthority;
import com.example.sekkison.user_authority.UserAuthorityRepository;
import com.example.sekkison.user_file.UserFile;
import com.example.sekkison.user_file.UserFileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class Dummy {
    @Autowired UserRepository userRepository;
    @Autowired AppointRepository appointRepository;
    @Autowired AuthorityRepository authorityRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired FriendRepository friendRepository;
    @Autowired InviteRepository inviteRepository;
    @Autowired MessageRepository messageRepository;
    @Autowired MyAppointRepository myAppointRepository;
    @Autowired UserAuthorityRepository userAuthorityRepository;
    @Autowired UserFileRepository userFileRepository;

    @Test
    void init() {
        // 권한 생성
        Authority auth_member = Authority.builder().auth("MEMBER").build();
        Authority auth_admin = Authority.builder().auth("ADMIN").build();
        auth_member = authorityRepository.saveAndFlush(auth_member);
        auth_admin = authorityRepository.saveAndFlush(auth_admin);

        // 유저 생성
        User user1 = User.builder()
                .username("USER1").password("1234").name("사과").phone("01012345678")
                .gender('M').content("안녕하세요 사과입니다").build();
        User user2 = User.builder()
                .username("USER2").password("1234").name("수박").phone("01011112222")
                .gender('F').content("안녕하세요 수박입니다").build();
        User user3 = User.builder()
                .username("USER3").password("1234").name("바나나").phone("01033332222")
                .gender('F').content("안녕하세요 바나나입니다").build();
        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user3 = userRepository.save(user3);

        // 유저에 권한 부여
        UserAuthority userAuth1 = UserAuthority.builder()
                .userId(user1.getId()).authority(auth_member.getId()).build();
        UserAuthority userAuth2 = UserAuthority.builder()
                .userId(user2.getId()).authority(auth_member.getId()).build();
        UserAuthority userAuth3 = UserAuthority.builder()
                .userId(user3.getId()).authority(auth_member.getId()).build();
        userAuth1 = userAuthorityRepository.save(userAuth1);
        userAuth2 = userAuthorityRepository.save(userAuth2);
        userAuth3 = userAuthorityRepository.save(userAuth3);

        // 유저 프로필사진 설정
        UserFile userFile1 = UserFile.builder().userId(user1.getId()).file("default.jpg").build();
        UserFile userFile2 = UserFile.builder().userId(user2.getId()).file("default.jpg").build();
        UserFile userFile3 = UserFile.builder().userId(user3.getId()).file("default.jpg").build();
        userFile1 = userFileRepository.save(userFile1);
        userFile2 = userFileRepository.save(userFile2);
        userFile2 = userFileRepository.save(userFile3);

        // 친구 생성
        Friend friend1 = Friend.builder()
                .toId(user1.getId()).fromId(user2.getId()).isAccepted(true).memo("김대진").build();
        Friend friend2 = Friend.builder()
                .toId(user2.getId()).fromId(user1.getId()).isAccepted(true).memo("안일찬").build();
        friend1 = friendRepository.save(friend1);
        friend2 = friendRepository.save(friend2);

        // 약속 생성
        Appoint appoint1 = Appoint.builder()
                .title("카페 갈사람").content("봉천동 카페에서 스터디할 사람 구함")
                .posX(37.483071074372766).posY(126.93869664756576)
                .addressDetail("TABLE1629 2층 대형 테이블").isRecruit(true).type(C.appointType.FTF)
                .headCnt(2).maxCnt(5).dDay(LocalDateTime.of(2023, 3, 25, 14, 30, 00))
                .isPublic(true).build();
        appoint1 = appointRepository.save(appoint1);

        // 약속 멤버 세팅
        MyAppoint myAppoint1 = MyAppoint.builder()
                .userId(user1.getId()).appointId(appoint1.getId()).isMaster(true).build();
        MyAppoint myAppoint2 = MyAppoint.builder()
                .userId(user2.getId()).appointId(appoint1.getId()).isMaster(false).build();
        myAppoint1 = myAppointRepository.save(myAppoint1);
        myAppoint2 = myAppointRepository.save(myAppoint2);

        // 쪽지 생성
        Message message1 = Message.builder()
                .fromId(user1.getId()).toId(user2.getId()).content("내일 시간 됨?").build();
        Message message2 = Message.builder()
                .fromId(user2.getId()).toId(user1.getId()).content("지금 뭐함?").build();
        message1 = messageRepository.save(message1);
        message2 = messageRepository.save(message2);

        // 약속 초대 생성
        Invite invite1 = Invite.builder()
                .fromId(user1.getId()).toId(user3.getId()).appointId(appoint1.getId()).build();
        invite1 = inviteRepository.save(invite1);

        // 친구 초대 생성
        Friend addFriend1 = Friend.builder()
                .fromId(user1.getId()).toId(user3.getId()).isAccepted(false).build();
        addFriend1 = friendRepository.save(addFriend1);
    }
}
