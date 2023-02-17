package com.example.sekkison.appoint;

import com.example.sekkison.common.ResponseForm;
import com.example.sekkison.my_appoint.MyAppoint;
import com.example.sekkison.my_appoint.MyAppointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointService {
    private final AppointRepository appointRepository;
    private final MyAppointRepository myAppointRepository;

    // 약속 만들기 (input : user_id, appoint 정보)
    public ResponseForm createAppoint(MyAppoint myAppoint, Appoint appoint) {
        ResponseForm res = new ResponseForm();

        String title = appoint.getTitle();
        String content = appoint.getContent();
        Integer max_cnt = appoint.getMax_cnt();
        LocalDateTime d_day = appoint.getD_day();
        Boolean is_public = appoint.getIs_public();

        // title이 비어있다면 에러
        if (title == null || title.trim().equals("")) {
            res.setError("제목이 비어있습니다", false);
            return res;
        }

        // content가 null 이면 content를 빈 문자열로 초기화
        if (content == null) appoint.setContent("");

        // head_cnt를 1로 세팅 (최초 생성이기 때문)
        appoint.setHead_cnt(1);

        // max_cnt가 비어있다면(나만의 약속) 1로 세팅
        if (max_cnt == null) appoint.setMax_cnt(1);




        Long appoint_id = appoint.getId();




        return res;
    }
}
