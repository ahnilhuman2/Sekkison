package com.example.sekkison.appoint;

import com.example.sekkison.common.C;
import com.example.sekkison.common.ResponseForm;
import com.example.sekkison.my_appoint.MyAppoint;
import com.example.sekkison.my_appoint.MyAppointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointService {
    private final AppointRepository appointRepository;
    private final MyAppointRepository myAppointRepository;

    // 약속 만들기 (input : user_id, appoint 정보)
    public ResponseForm createAppoint(Long user_id, Appoint appoint) {
        ResponseForm res = new ResponseForm();

        // title이 비어있다면 에러
        if (appoint.getTitle() == null || appoint.getTitle().trim().equals(""))
            res.setError("제목이 비어있습니다", false);

        // d-day가 비어있다면 에러
        if (appoint.getD_day() == null) return res.setError("날짜와 시간이 비어있습니다", false);

        // content가 null 이면 content를 빈 문자열로 초기화
        if (appoint.getContent() == null) appoint.setContent("");

        // head_cnt를 1로 세팅 (최초 생성이기 때문)
        appoint.setHead_cnt(1);

        // type 세팅
        if (appoint.getType() == null) {
            if (appoint.getMax_cnt() == null)
                appoint.setType(C.appointType.SOLO);
            else if (appoint.getPos_x() == null && appoint.getPos_y() == null)
                appoint.setType(C.appointType.NFTF);
            else
                appoint.setType(C.appointType.FTF);
        }

        // max_cnt가 비어있다면(나만의 약속) 1로 세팅
        if (appoint.getMax_cnt() == null) appoint.setMax_cnt(1);

        // appoint 저장
        appoint = appointRepository.save(appoint);

        // myAppoint 세팅
        MyAppoint myAppoint = MyAppoint.builder()
                .user_id(user_id)
                .appoint_id(appoint.getId())
                .is_master(true).build();

        // myAppoint 저장
        myAppointRepository.save(myAppoint);

        res.setSuccess(true, null);
        return res;
    }
    // 약속 정보 가져오기 (input : appoint_id)
    public ResponseForm readAppoint(Long appointId) {
        ResponseForm res = new ResponseForm();

        Appoint appoint = appointRepository.findById(appointId).orElse(null);

        // id로 찾을 수 없으면
        if (appoint == null) return res.setError("약속이 존재하지 않습니다", false);

        return res.setSuccess(true, appoint);
    }
    // 약속 수정하기 (input : user_id, appoint_id, appoint)
    public ResponseForm updateAppoint(Long userId, Long appointId, Appoint appoint) {
        ResponseForm res = new ResponseForm();

        // userId, appointId로 myAppoint 가져오기
        MyAppoint myAppoint = myAppointRepository.findByUser_idAndAppoiny_id(userId, appointId);

        // 방장이 아니라면 에러
        if (!myAppoint.getIs_master()) return res.setError("주최자가 아닙니다", false);

        // 방장이라면 appoint 수정
        appoint.setId(appointId);
        appointRepository.save(appoint);

        res.setSuccess(true, null);
        return res;
    }
}
