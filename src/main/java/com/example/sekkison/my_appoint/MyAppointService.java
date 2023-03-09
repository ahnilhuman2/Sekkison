package com.example.sekkison.my_appoint;

import com.example.sekkison.appoint.AppointService;
import com.example.sekkison.common.ResponseForm;
import com.example.sekkison.invite.Invite;
import com.example.sekkison.invite.InviteRepository;
import com.example.sekkison.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyAppointService {

    private final MyAppointRepository myAppointRepository;
    private final AppointService appointService;
    private final InviteRepository inviteRepository;
    private final UserRepository userRepository;

    public ResponseForm isMaster(Long userId, Long appointId) {
        ResponseForm responseForm = new ResponseForm();
        MyAppoint isMaster = myAppointRepository.findByUserIdAndAppointId(userId, appointId);

        return responseForm.setSuccess(isMaster);
    }
    // 참가
    public ResponseForm participate(Long userId, Long appointId) {
        ResponseForm responseForm = new ResponseForm();
        MyAppoint participateRoom = MyAppoint.builder()
                .userId(userId).appointId(appointId).isMaster(false)
                .build();

        // 초대장 삭제
        List<Invite> invites = inviteRepository.findByToIdAndAppointId(userId, appointId);
        if (invites != null && invites.size() != 0)
            for(Invite i : invites) 
                inviteRepository.delete(i);

        myAppointRepository.save(participateRoom);

        appointService.setHeadCnt(appointId);
        return responseForm.setSuccess(null);
    }
    // 나가기
    public ResponseForm deleteMyAppoint(Long userId, Long appointId) {
        ResponseForm res = new ResponseForm();
        MyAppoint myapp = myAppointRepository.findByUserIdAndAppointId(userId, appointId);
        if (myapp == null) return res.setError("방에 참가중이 아닙니다");
        myAppointRepository.delete(myapp);

        appointService.setHeadCnt(appointId);
        return res.setSuccess(null);
    }
}
