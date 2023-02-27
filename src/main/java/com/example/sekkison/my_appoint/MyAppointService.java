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
        MyAppoint participateRoom = myAppointRepository.findByUserIdAndAppointId(userId, appointId);
        participateRoom.setIsMaster(false);
        myAppointRepository.save(participateRoom);

        return responseForm.setSuccess(true, null);
    }
}
