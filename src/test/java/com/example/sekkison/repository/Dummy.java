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
        User user4 = User.builder()
                .username("USER4").password("1234").name("배").phone("01043545424")
                .gender('M').content("안녕하세요 배입니다").build();
        User user5 = User.builder()
                .username("USER5").password("1234").name("귤").phone("01094837744")
                .gender('M').content("안녕하세요 귤입니다").build();
        User user6 = User.builder()
                .username("USER6").password("1234").name("체리").phone("01012324343")
                .gender('M').content("안녕하세요 체리입니다").build();
        User user7 = User.builder()
                .username("USER7").password("1234").name("두리안").phone("01098379393")
                .gender('F').content("안녕하세요 두리안입니다").build();
        User user8 = User.builder()
                .username("USER8").password("1234").name("망고").phone("01044433209")
                .gender('F').content("안녕하세요 망고입니다").build();
        User user9 = User.builder()
                .username("USER9").password("1234").name("오렌지").phone("01090873828")
                .gender('M').content("안녕하세요 오렌지입니다").build();
        User user10 = User.builder()
                .username("USER10").password("1234").name("포도").phone("01088887722")
                .gender('F').content("안녕하세요 포도입니다").build();
        User user11 = User.builder()
                .username("USER11").password("1234").name("레몬").phone("01088543622")
                .gender('F').content("안녕하세요 레몬입니다").build();
        User user12 = User.builder()
                .username("USER12").password("1234").name("딸기").phone("01090352632")
                .gender('F').content("안녕하세요 딸기입니다").build();
        User user13 = User.builder()
                .username("USER13").password("1234").name("배추").phone("01075839273")
                .gender('F').content("안녕하세요 배추입니다").build();
        User user14 = User.builder()
                .username("USER14").password("1234").name("당근").phone("01067659922")
                .gender('F').content("안녕하세요 당근입니다").build();
        User user15 = User.builder()
                .username("USER15").password("1234").name("시금치").phone("01054675653")
                .gender('F').content("안녕하세요 시금치입니다").build();

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user3 = userRepository.save(user3);
        user4 = userRepository.save(user4);
        user5 = userRepository.save(user5);
        user6 = userRepository.save(user6);
        user7 = userRepository.save(user7);
        user8 = userRepository.save(user8);
        user9 = userRepository.save(user9);
        user10 = userRepository.save(user10);
        user11 = userRepository.save(user11);
        user12 = userRepository.save(user12);
        user13 = userRepository.save(user13);
        user14 = userRepository.save(user14);
        user15 = userRepository.save(user15);

        // 유저에 권한 부여
        UserAuthority userAuth1 = UserAuthority.builder()
                .userId(user1.getId()).authority(auth_member.getId()).build();
        UserAuthority userAuth2 = UserAuthority.builder()
                .userId(user2.getId()).authority(auth_member.getId()).build();
        UserAuthority userAuth3 = UserAuthority.builder()
                .userId(user3.getId()).authority(auth_member.getId()).build();
        UserAuthority userAuth4 = UserAuthority.builder()
                .userId(user4.getId()).authority(auth_member.getId()).build();
        UserAuthority userAuth5 = UserAuthority.builder()
                .userId(user5.getId()).authority(auth_member.getId()).build();
        UserAuthority userAuth6 = UserAuthority.builder()
                .userId(user6.getId()).authority(auth_member.getId()).build();
        UserAuthority userAuth7 = UserAuthority.builder()
                .userId(user7.getId()).authority(auth_member.getId()).build();
        UserAuthority userAuth8 = UserAuthority.builder()
                .userId(user8.getId()).authority(auth_member.getId()).build();
        UserAuthority userAuth9 = UserAuthority.builder()
                .userId(user9.getId()).authority(auth_member.getId()).build();
        UserAuthority userAuth10 = UserAuthority.builder()
                .userId(user10.getId()).authority(auth_member.getId()).build();
        userAuth1 = userAuthorityRepository.save(userAuth1);
        userAuth2 = userAuthorityRepository.save(userAuth2);
        userAuth3 = userAuthorityRepository.save(userAuth3);
        userAuth4 = userAuthorityRepository.save(userAuth4);
        userAuth5 = userAuthorityRepository.save(userAuth5);
        userAuth6 = userAuthorityRepository.save(userAuth6);
        userAuth7 = userAuthorityRepository.save(userAuth7);
        userAuth8 = userAuthorityRepository.save(userAuth8);
        userAuth9 = userAuthorityRepository.save(userAuth9);
        userAuth10 = userAuthorityRepository.save(userAuth10);

        // 유저 프로필사진 설정
        UserFile userFile1 = UserFile.builder().userId(user1.getId()).file("default.jpg").build();
        UserFile userFile2 = UserFile.builder().userId(user2.getId()).file("default.jpg").build();
        UserFile userFile3 = UserFile.builder().userId(user3.getId()).file("default.jpg").build();
        UserFile userFile4 = UserFile.builder().userId(user4.getId()).file("default.jpg").build();
        UserFile userFile5 = UserFile.builder().userId(user5.getId()).file("default.jpg").build();
        UserFile userFile6 = UserFile.builder().userId(user6.getId()).file("default.jpg").build();
        UserFile userFile7 = UserFile.builder().userId(user7.getId()).file("default.jpg").build();
        UserFile userFile8 = UserFile.builder().userId(user8.getId()).file("default.jpg").build();
        UserFile userFile9 = UserFile.builder().userId(user9.getId()).file("default.jpg").build();
        UserFile userFile10 = UserFile.builder().userId(user10.getId()).file("default.jpg").build();
        userFile1 = userFileRepository.save(userFile1);
        userFile2 = userFileRepository.save(userFile2);
        userFile3 = userFileRepository.save(userFile3);
        userFile4 = userFileRepository.save(userFile4);
        userFile5 = userFileRepository.save(userFile5);
        userFile6 = userFileRepository.save(userFile6);
        userFile7 = userFileRepository.save(userFile7);
        userFile8 = userFileRepository.save(userFile8);
        userFile9 = userFileRepository.save(userFile9);
        userFile10 = userFileRepository.save(userFile10);

        // 친구 생성
        createFriend(user2, user1, "안일찬", "김대진");
        createFriend(user2, user3, "김뚜뚜", "철수");
        createFriend(user2, user4, "김땡땡", "영희");
        createFriend(user2, user5, "박똘똘", "동생");
        createFriend(user2, user6, "이둘삼", "모르는사람");
        createFriend(user2, user7, "박구사", "마동석");
        createFriend(user2, user8, "한장땡", "서강준");
        createFriend(user2, user9, "룰루", "페이커");
        createFriend(user2, user10, "랄라", "마리오");

        // 약속 생성
        Appoint appoint1 = Appoint.builder()
                .title("카페 갈사람").content("봉천동 카페에서 스터디할 사람 구함")
                .posX(37.483071074372766).posY(126.93869664756576)
                .addressDetail("TABLE1629 2층 대형 테이블").isRecruit(true).type(C.appointType.FTF)
                .headCnt(2).maxCnt(5).dDay(LocalDateTime.of(2023, 4, 25, 14, 30, 00))
                .isPublic(true).build();
        Appoint appoint2 = Appoint.builder()
                .title("밥먹을 사람").content("대치동에서 밥먹을 사람")
                .posX(37.49336487501768).posY(127.06171319748971)
                .addressDetail("대치역 버거킹").isRecruit(true).type(C.appointType.FTF)
                .headCnt(3).maxCnt(5).dDay(LocalDateTime.of(2023, 4, 13, 18, 30, 00))
                .isPublic(true).build();
        Appoint appoint3 = Appoint.builder()
                .title("신림에서 게임할사람").content("롤하자")
                .posX(37.526333688315134).posY(127.03781631686098)
                .addressDetail("SNS 피시방").isRecruit(true).type(C.appointType.FTF)
                .headCnt(3).maxCnt(5).dDay(LocalDateTime.of(2023, 4, 17, 12, 00, 00))
                .isPublic(true).build();
        Appoint appoint4 = Appoint.builder()
                .title("압구정에서 술먹을사람").content("많이 먹을거임")
                .posX(37.48424055695671).posY(126.92751065352222)
                .addressDetail("마법갈비요술꼬치").isRecruit(true).type(C.appointType.FTF)
                .headCnt(2).maxCnt(4).dDay(LocalDateTime.of(2023, 4, 17, 18, 00, 00))
                .isPublic(true).build();
        Appoint appoint5 = Appoint.builder()
                .title("양재동에서 스크린칠사람").content("100타 이하만")
                .posX(37.48459851803084).posY(127.03966934653603)
                .addressDetail("핀하이스크린").isRecruit(true).type(C.appointType.FTF)
                .headCnt(2).maxCnt(4).dDay(LocalDateTime.of(2023, 4, 28, 18, 00, 00))
                .isPublic(true).build();
        Appoint appoint6 = Appoint.builder()
                .title("커피 먹을사람").content("방장이 산다")
                .posX(37.49456269567025).posY(127.06269799130555)
                .addressDetail("대치역 스타벅스").isRecruit(true).type(C.appointType.FTF)
                .headCnt(2).maxCnt(4).dDay(LocalDateTime.of(2023, 5, 1, 13, 00, 00))
                .isPublic(true).build();
        Appoint appoint7 = Appoint.builder()
                .title("아파트보러 가실분").content("부동산 스터디해요")
                .posX(37.49489702315638).posY(127.0608719979609)
                .addressDetail("래미안 대치 팰리스").isRecruit(true).type(C.appointType.FTF)
                .headCnt(3).maxCnt(8).dDay(LocalDateTime.of(2023, 5, 13, 13, 00, 00))
                .isPublic(true).build();
        Appoint appoint8 = Appoint.builder()
                .title("싸울분").content("다덤벼")
                .posX(37.49088686917802).posY(127.06216346216982)
                .addressDetail("서울 대치초등학교").isRecruit(true).type(C.appointType.FTF)
                .headCnt(2).maxCnt(4).dDay(LocalDateTime.of(2023, 5, 18, 18, 00, 00))
                .isPublic(true).build();
        Appoint appoint9 = Appoint.builder()
                .title("유모차 무료나눔해요").content("상태좋습니다. 대치역으로 오세요")
                .posX(37.49368909234321).posY(127.0619905104726)
                .addressDetail("대치역").isRecruit(true).type(C.appointType.FTF)
                .headCnt(2).maxCnt(3).dDay(LocalDateTime.of(2023, 5, 30, 12, 00, 00))
                .isPublic(true).build();
        Appoint appoint10 = Appoint.builder()
                .title("호텔 같이 가실분").content("돈은 반반입니다. 여자만")
                .posX(37.508500483292536).posY(127.0641347373916)
                .addressDetail("파크하얏트 서울").isRecruit(true).type(C.appointType.FTF)
                .headCnt(2).maxCnt(3).dDay(LocalDateTime.of(2023, 5, 30, 12, 00, 00))
                .isPublic(true).build();
        Appoint appoint11 = Appoint.builder()
                .title("테스트용 약속").content("테스트")
                .posX(37.508500483292536).posY(127.0641347373916)
                .addressDetail("파크하얏트 서울").isRecruit(true).type(C.appointType.FTF)
                .headCnt(2).maxCnt(3).dDay(LocalDateTime.of(2023, 5, 30, 12, 00, 00))
                .isPublic(true).build();

        appoint1 = appointRepository.save(appoint1);
        appoint2 = appointRepository.save(appoint2);
        appoint3 = appointRepository.save(appoint3);
        appoint4 = appointRepository.save(appoint4);
        appoint5 = appointRepository.save(appoint5);
        appoint6 = appointRepository.save(appoint6);
        appoint7 = appointRepository.save(appoint7);
        appoint8 = appointRepository.save(appoint8);
        appoint9 = appointRepository.save(appoint9);
        appoint10 = appointRepository.save(appoint10);
        appoint11 = appointRepository.save(appoint11);



        // 약속 멤버 세팅
        MyAppoint myAppoint1 = MyAppoint.builder()
                .userId(user1.getId()).appointId(appoint1.getId()).isMaster(true).build();
        MyAppoint myAppoint2 = MyAppoint.builder()
                .userId(user2.getId()).appointId(appoint1.getId()).isMaster(false).build();
        MyAppoint myAppoint3 = MyAppoint.builder()
                .userId(user3.getId()).appointId(appoint1.getId()).isMaster(false).build();
        MyAppoint myAppoint4 = MyAppoint.builder()
                .userId(user4.getId()).appointId(appoint4.getId()).isMaster(true).build();
        MyAppoint myAppoint5 = MyAppoint.builder()
                .userId(user5.getId()).appointId(appoint4.getId()).isMaster(false).build();
        MyAppoint myAppoint6 = MyAppoint.builder()
                .userId(user6.getId()).appointId(appoint4.getId()).isMaster(false).build();
        MyAppoint myAppoint7 = MyAppoint.builder()
                .userId(user7.getId()).appointId(appoint7.getId()).isMaster(true).build();
        MyAppoint myAppoint8 = MyAppoint.builder()
                .userId(user8.getId()).appointId(appoint7.getId()).isMaster(false).build();
        MyAppoint myAppoint9 = MyAppoint.builder()
                .userId(user9.getId()).appointId(appoint10.getId()).isMaster(true).build();
        MyAppoint myAppoint10 = MyAppoint.builder()
                .userId(user10.getId()).appointId(appoint10.getId()).isMaster(false).build();
        MyAppoint myAppoint11 = MyAppoint.builder()
                .userId(user2.getId()).appointId(appoint11.getId()).isMaster(true).build();
        MyAppoint myAppoint12 = MyAppoint.builder()
                .userId(user3.getId()).appointId(appoint11.getId()).isMaster(false).build();

        myAppoint1 = myAppointRepository.save(myAppoint1);
        myAppoint2 = myAppointRepository.save(myAppoint2);
        myAppoint3 = myAppointRepository.save(myAppoint3);
        myAppoint4 = myAppointRepository.save(myAppoint4);
        myAppoint5 = myAppointRepository.save(myAppoint5);
        myAppoint6 = myAppointRepository.save(myAppoint6);
        myAppoint7 = myAppointRepository.save(myAppoint7);
        myAppoint8 = myAppointRepository.save(myAppoint8);
        myAppoint9 = myAppointRepository.save(myAppoint9);
        myAppoint10 = myAppointRepository.save(myAppoint10);
        myAppoint11 = myAppointRepository.save(myAppoint11);
        myAppoint12 = myAppointRepository.save(myAppoint12);

        // 쪽지 생성
        Message message1 = Message.builder()
                .fromId(user1.getId()).toId(user2.getId()).content("내일 시간 됨?").build();
        Message message2 = Message.builder()
                .fromId(user2.getId()).toId(user1.getId()).content("지금 뭐함?").build();
        Message message3 = Message.builder()
                .fromId(user2.getId()).toId(user1.getId()).content("답안함?").build();
        Message message4 = Message.builder()
                .fromId(user1.getId()).toId(user2.getId()).content("ㅈㅅ 못봄").build();
        Message message5 = Message.builder()
                .fromId(user3.getId()).toId(user4.getId()).content("ㅎㅇ").build();
        Message message6 = Message.builder()
                .fromId(user4.getId()).toId(user3.getId()).content("ㅇㅇ").build();
        Message message7 = Message.builder()
                .fromId(user5.getId()).toId(user6.getId()).content("님 남자임?").build();
        Message message8 = Message.builder()
                .fromId(user6.getId()).toId(user5.getId()).content("ㄴㄴ").build();
        Message message9 = Message.builder()
                .fromId(user5.getId()).toId(user6.getId()).content("술드실? 제가삼").build();
        Message message10 = Message.builder()
                .fromId(user6.getId()).toId(user5.getId()).content("ㄲㅈ").build();

        message1 = messageRepository.save(message1);
        message2 = messageRepository.save(message2);
        message3 = messageRepository.save(message3);
        message4 = messageRepository.save(message4);
        message5 = messageRepository.save(message5);
        message6 = messageRepository.save(message6);
        message7 = messageRepository.save(message7);
        message8 = messageRepository.save(message8);
        message9 = messageRepository.save(message9);
        message10 = messageRepository.save(message10);

        // 약속 초대 생성
        Invite invite1 = Invite.builder()
                .fromId(user1.getId()).toId(user3.getId()).appointId(appoint1.getId()).build();
        Invite invite2 = Invite.builder()
                .fromId(user9.getId()).toId(user1.getId()).appointId(appoint1.getId()).build();
        Invite invite3 = Invite.builder()
                .fromId(user9.getId()).toId(user3.getId()).appointId(appoint1.getId()).build();
        Invite invite4 = Invite.builder()
                .fromId(user9.getId()).toId(user4.getId()).appointId(appoint1.getId()).build();
        Invite invite5 = Invite.builder()
                .fromId(user9.getId()).toId(user5.getId()).appointId(appoint1.getId()).build();
        Invite invite6 = Invite.builder()
                .fromId(user9.getId()).toId(user6.getId()).appointId(appoint1.getId()).build();
        Invite invite7 = Invite.builder()
                .fromId(user9.getId()).toId(user7.getId()).appointId(appoint1.getId()).build();
        Invite invite8 = Invite.builder()
                .fromId(user9.getId()).toId(user8.getId()).appointId(appoint1.getId()).build();

        invite1 = inviteRepository.save(invite1);
        invite2 = inviteRepository.save(invite2);
        invite3 = inviteRepository.save(invite3);
        invite4 = inviteRepository.save(invite4);
        invite5 = inviteRepository.save(invite5);
        invite6 = inviteRepository.save(invite6);
        invite7 = inviteRepository.save(invite7);
        invite8 = inviteRepository.save(invite8);

        // 친구 초대 생성
        Friend addFriend1 = Friend.builder()
                .fromId(user1.getId()).toId(user3.getId()).isAccepted(false).build();
        Friend addFriend2 = Friend.builder()
                .fromId(user1.getId()).toId(user11.getId()).isAccepted(false).build();
        Friend addFriend3 = Friend.builder()
                .fromId(user1.getId()).toId(user12.getId()).isAccepted(false).build();
        Friend addFriend4 = Friend.builder()
                .fromId(user1.getId()).toId(user13.getId()).isAccepted(false).build();
        Friend addFriend5 = Friend.builder()
                .fromId(user1.getId()).toId(user14.getId()).isAccepted(false).build();
        Friend addFriend6 = Friend.builder()
                .fromId(user1.getId()).toId(user15.getId()).isAccepted(false).build();

        addFriend1 = friendRepository.save(addFriend1);
        addFriend2 = friendRepository.save(addFriend2);
        addFriend3 = friendRepository.save(addFriend3);
        addFriend4 = friendRepository.save(addFriend4);
        addFriend5 = friendRepository.save(addFriend5);
        addFriend6 = friendRepository.save(addFriend6);
    }
    private void createFriend(User user1, User user2, String memo1, String memo2) {
        Friend f1 = Friend.builder()
                .toId(user1.getId()).fromId(user2.getId()).isAccepted(true).memo(memo1).build();
        Friend f2 = Friend.builder()
                .toId(user2.getId()).fromId(user1.getId()).isAccepted(true).memo(memo2).build();
        friendRepository.save(f1);
        friendRepository.save(f2);
    }
}
