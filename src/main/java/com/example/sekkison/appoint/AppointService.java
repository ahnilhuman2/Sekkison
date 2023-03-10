package com.example.sekkison.appoint;

import com.example.sekkison.common.C;
import com.example.sekkison.common.ResponseForm;
import com.example.sekkison.invite.Invite;
import com.example.sekkison.invite.InviteRepository;
import com.example.sekkison.my_appoint.MyAppoint;
import com.example.sekkison.my_appoint.MyAppointRepository;
import com.example.sekkison.user.User;
import com.example.sekkison.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointService {
    private final AppointRepository appointRepository;
    private final MyAppointRepository myAppointRepository;
    private final UserRepository userRepository;
    private final InviteRepository inviteRepository;

    // 약속 만들기 (input : user_id, appoint 정보)
    public ResponseForm createAppoint(Long user_id, Appoint appoint, Integer typeInteger,
                                      String date, String time) {
        ResponseForm res = new ResponseForm();

        // title이 비어있다면 에러
        if (appoint.getTitle() == null || appoint.getTitle().trim().equals(""))
            return res.setError("제목이 비어있습니다");
        // d-day가 비어있다면 에러
        if (date == null || "".equals(date) || time == null || "".equals(time))
            return res.setError("날짜와 시간이 비어있습니다");

        // content가 null 이면 content를 빈 문자열로 초기화
        if (appoint.getContent() == null) appoint.setContent("");
        // head_cnt를 1로 세팅 (최초 생성이기 때문)
        appoint.setHeadCnt(1);
        // 나만의 약속이라면 maxCnt 1, isPublic null로 세팅
        if (typeInteger == 2) {
            appoint.setMaxCnt(1);
            appoint.setIsPublic(null);
        }

        // dday 세팅
        appoint.setDday(makeDateTime(date, time));

        // type 세팅
        C.appointType type;
        if (typeInteger == 0) type = C.appointType.FTF;
        else if (typeInteger == 1) type = C.appointType.NFTF;
        else type = C.appointType.SOLO;
        appoint.setType(type);

        // appoint 저장
        appoint = appointRepository.save(appoint);

        // myAppoint 세팅
        MyAppoint myAppoint = MyAppoint.builder()
                .userId(user_id)
                .appointId(appoint.getId())
                .isMaster(true).build();

        // myAppoint 저장
        myAppointRepository.save(myAppoint);
        setHeadCnt(appoint.getId());
        return res.setSuccess(appoint.getId());
    }
    private LocalDateTime makeDateTime(String date, String time) {
        String[] dates = date.split("\\.");
        String[] times = time.split(":");
        Integer y = Integer.parseInt(dates[0]);
        Integer m = Integer.parseInt(dates[1]);
        Integer d = Integer.parseInt(dates[2]);
        Integer hh = Integer.parseInt(times[0]);
        Integer mm = Integer.parseInt(times[1]);
        return LocalDateTime.of(y, m, d, hh, mm, 00);
    }
    // 약속 정보 가져오기 (input : appoint_id)
    public ResponseForm readAppoint(Long userId, Long appointId) {
        ResponseForm res = new ResponseForm();

        Appoint appoint = appointRepository.findById(appointId).orElse(null);

        // id로 찾을 수 없으면
        if (appoint == null) return res.setError("약속이 존재하지 않습니다");

        MyAppoint me_ma = myAppointRepository.findByUserIdAndAppointId(userId, appointId);
        List<Invite> me_i = inviteRepository.findByToIdAndAppointId(userId, appointId);
        if ((appoint.getIsPublic() == null || !appoint.getIsPublic()) &&
                me_ma == null && (me_i == null || me_i.size() == 0))
            return res.setError("권한이 없습니다");

        // memo에 방장 이름 세팅
        List<MyAppoint> ma = myAppointRepository.findByAppointIdAndIsMaster(appoint.getId(), true);
        if (ma == null || ma.size() == 0) appoint.setMemo("unknown");
        else {
            User u = userRepository.findById(ma.get(0).getUserId()).orElse(null);
            if (u == null) appoint.setMemo("unknown");
            else appoint.setMemo(u.getName());
        }
        return res.setSuccess(appoint);
    }
    // 약속 수정하기 (input : user_id, appoint_id, appoint)
    public ResponseForm updateAppoint(Long userId, Long appointId, Appoint appoint) {
        ResponseForm res = new ResponseForm();

        // 방장이 아니라면 에러
        if (!isMaster(appointId, userId)) return res.setError("주최자가 아닙니다");

        // 방장이라면 appoint 수정
        appoint.setId(appointId);
        appointRepository.save(appoint);

        res.setSuccess(null);
        return res;
    }
    // 유저가 해당 약속의 방장인지 아닌지 판단하는 함수
    private Boolean isMaster(Long appointId, Long userId) {
        MyAppoint myAppoint = myAppointRepository.findByUserIdAndAppointId(userId, appointId);
        return myAppoint.getIsMaster();
    }
    // 약속에 참가한 유저 이름 가져오기
    public ResponseForm getAppointMembers(Long appointId) {
        ResponseForm res = new ResponseForm();

        // appoint_id로 myAppoint 가져오기
        List<MyAppoint> myAppoints = myAppointRepository.findByAppointId(appointId);

        // myAppoints가 비어있다면 에러
        if (myAppoints.size() == 0 || myAppoints == null) return res.setError("해당하는 약속이 없습니다");

        // members에 유저 이름 넣기
        List<User> members = new ArrayList<>();
        for(MyAppoint ma : myAppoints) {
            User user = userRepository.findById(ma.getUserId()).orElse(null);
            members.add(user);
        }
        return res.setSuccess(members);
    }
    // 약속 멤버 한명 강퇴
    public ResponseForm deleteAppointMembers(Long appointId, Long fromId, Long toId) {
        ResponseForm res = new ResponseForm();

        // 방장이 아니면 강퇴불가
        if (!isMaster(appointId, fromId)) return res.setError("강퇴는 방장만 가능합니다");

        // appoint_id, to_id(user)로 제거할 my_appoint 찾기
        MyAppoint myAppoint = myAppointRepository.findByUserIdAndAppointId(toId, appointId);
        
        // 없으면 에러
        if (myAppoint == null) return res.setError("약속에 참가한 해당 유저가 없습니다");

        // myAppoint 삭제
        myAppointRepository.delete(myAppoint);
        setHeadCnt(appointId);
        return res.setSuccess(null);
    }
    // 검색어, 공개여부, 모집여부로 Appoint리스트 가져오기
    // 공개여부 1:공개, 0:비공개
    // 모집여부 1:모집중, 0:모집완료
    // 만남여부 1:만남, 0:온라인
    public ResponseForm getSearchAppointList(String search, Integer isPublic, Integer isRecruit, Integer isFtf, Integer page) {
        ResponseForm res = new ResponseForm();

        Boolean is_public = isPublic == 1 ? true : false;
        Boolean is_recruit = isRecruit == 1 ? true : false;
        C.appointType is_ftf = isFtf == 1 ? C.appointType.FTF : C.appointType.NFTF;
        
        // 페이징 설정
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());

        // 조건에 맞는 appoint리스트 생성
        Page<Appoint> appointList =
                appointRepository.findByIsPublicAndIsRecruitAndTypeAndTitleContainsAndDdayAfter(
                        is_public, is_recruit, is_ftf, search, LocalDateTime.now() ,pageable);

        // 불러올 약속이 없으면 에러
        if (appointList.getContent().size() == 0) return res.setError("더이상 약속이 없습니다");

        // 방장 이름 세팅
        for(Appoint a : appointList.getContent()) {
            List<MyAppoint> ma = myAppointRepository.findByAppointIdAndIsMaster(a.getId(), true);
            if (ma == null || ma.size() == 0) {
                a.setMemo("0&unknown");
                continue;
            }
            User u = userRepository.findById(ma.get(0).getUserId()).orElse(null);
            if (u == null) {
                a.setMemo("0&unknown");
                continue;
            }
            a.setMemo(u.getId() + "&" + u.getName());
        }

        return res.setSuccess(appointList.getContent());
    }
    // 약속에 참가한 멤버 인원수가 변할 때마다(myAppoint 삭제/추가) 갱신할것
    public void setHeadCnt(Long appoint_id) {
        // appoint, myappointList 가져오기
        Appoint appoint = appointRepository.findById(appoint_id).orElse(null);
        List<MyAppoint> myAppoints = myAppointRepository.findByAppointId(appoint.getId());

        // headCnt 세팅
        appoint.setHeadCnt(myAppoints.size());

        // headCnt, maxCnt 같다면 isRecruit false, 다르다면 isRecruit true
        appoint.setIsRecruit(appoint.getHeadCnt() >= appoint.getMaxCnt() ? false : true);
        appointRepository.save(appoint);
    }
    // 약속 삭제
    public ResponseForm deleteAppoint(Long userId, Long appointId) {
        ResponseForm res = new ResponseForm();

        // 방장이 아니라면 에러
        if (!isMaster(appointId, userId)) return res.setError("약속 삭제는 방장만 가능합니다");

        // myAppoints 삭제
        List<MyAppoint> myAppoints = myAppointRepository.findByAppointId(appointId);
        for(MyAppoint ma : myAppoints) myAppointRepository.delete(ma);

        // invites 삭제
        List<Invite> invites = inviteRepository.findByAppointId(appointId);
        for(Invite i : invites) inviteRepository.delete(i);

        // appoint 삭제
        Appoint appoint = appointRepository.findById(appointId).orElse(null);
        appointRepository.delete(appoint);

        return res.setSuccess(null);
    }
    // 약속 최대인원 수정
    public ResponseForm updateMaxCnt(Long appointId, Long userId, Integer count) {
        ResponseForm res = new ResponseForm();

        // 방장이 아니면 에러
        if (!isMaster(appointId, userId)) return res.setError("수정은 방장만 가능합니다");

        // maxCnt 설정 및 저장
        Appoint appoint = appointRepository.findById(appointId).orElse(null);
        appoint.setMaxCnt(count);
        appointRepository.save(appoint);
        return res.setSuccess(null);
    }
    // 내 약속 목록 가져오기(페이징)
    public ResponseForm getMyAppointList(Long userId, Integer page) {
        ResponseForm res = new ResponseForm();

        // 페이징 설정
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());

        // 조건에 맞는 appoint리스트 생성
        Page<MyAppoint> myAppointList =
                myAppointRepository.findByUserId(userId, pageable);

        // 불러올 약속이 없으면 에러
        if (myAppointList.getContent().size() == 0) return res.setError("더이상 약속이 없습니다");

        List<Appoint> appointList = new ArrayList<>();
        for(MyAppoint ma : myAppointList.getContent()) {
            Long appointId = ma.getAppointId();
            Appoint a = appointRepository.findById(appointId).orElse(null);
            List<MyAppoint> maList = myAppointRepository.findByAppointIdAndIsMaster(appointId, true);
            if (maList == null || maList.size() == 0) a.setMemo("unknown");
            else {
                User u = userRepository.findById(maList.get(0).getUserId()).orElse(null);
                if (u == null) a.setMemo("unknown");
                else a.setMemo(u.getName());
            }
            appointList.add(a);
        }
        return res.setSuccess(appointList);
    }
    // 해당 날짜의 내 약속 가져오기
    public ResponseForm getCalenderAppoint(Long userId, Integer year, Integer month) {
        List<List<Appoint>> data = new ArrayList<>();
        for(int i = 0; i < 32; i++) data.add(new ArrayList<>());

        // 내 약속 가져오기
        List<MyAppoint> mas = myAppointRepository.findByUserId(userId);
        for(MyAppoint ma : mas) {
            Appoint a = appointRepository.findById(ma.getAppointId()).orElse(null);
            LocalDateTime t = a.getDday();

            // 해당 날짜가 아니면 패스
            if (t.getYear() != year) continue;
            if (t.getMonthValue() != month) continue;
            data.get(t.getDayOfMonth()).add(a);
        }
        return new ResponseForm().setSuccess(data);
    }
}
