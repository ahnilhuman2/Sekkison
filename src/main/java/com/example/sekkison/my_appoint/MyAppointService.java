package com.example.sekkison.my_appoint;

import com.example.sekkison.common.ResponseForm;
import com.example.sekkison.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyAppointService {

    private final MyAppointRepository myAppointRepository;
    private final UserRepository userRepository;

    public ResponseForm isMaster(Long userId, Long appointId) {
        ResponseForm responseForm = new ResponseForm();
        MyAppoint isMaster = myAppointRepository.findByUserIdAndAppointId(userId, appointId);

        return responseForm.setSuccess(isMaster);
    }

    public ResponseForm participate(Long userId, Long appointId) {
        ResponseForm responseForm = new ResponseForm();
        MyAppoint participateRoom = MyAppoint.builder()
                .userId(userId).appointId(appointId).isMaster(false)
                .build();
        myAppointRepository.save(participateRoom);

        return responseForm.setSuccess(null);
    }

    public ResponseForm deleteMyAppoint(Long userId, Long appointId) {
        ResponseForm res = new ResponseForm();
        MyAppoint myapp = myAppointRepository.findByUserIdAndAppointId(userId, appointId);
        if (myapp == null) return res.setError("방에 참가중이 아닙니다");
        myAppointRepository.delete(myapp);
        return res.setSuccess(null);
    }
}
