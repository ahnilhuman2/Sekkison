package com.example.sekkison.my_appoint;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyAppointService {

    private final MyAppointRepository myAppointRepository;

    public ResponseForm isMaster(Long userId, Long appointId) {
        ResponseForm responseForm = new ResponseForm();
        MyAppoint isMaster = myAppointRepository.findByUserIdAndAppointId(userId, appointId);

        return responseForm.setSuccess(true, isMaster);
    }

    public ResponseForm participate(Long userId, Long appointId) {
        ResponseForm responseForm = new ResponseForm();
        MyAppoint participateRoom = MyAppoint.builder()
                .userId(userId).appointId(appointId).isMaster(false)
                .build();
        myAppointRepository.save(participateRoom);

        return responseForm.setSuccess(true, null);
    }

    public ResponseForm deleteMyAppoint(Long userId, Long appointId) {
        ResponseForm res = new ResponseForm();
        MyAppoint myapp = myAppointRepository.findByUserIdAndAppointId(userId, appointId);
        if (myapp == null) return res.setError("방에 참가중이 아닙니다", false);
        myAppointRepository.delete(myapp);
        return res.setSuccess(true, null);
    }
}
