package com.example.sekkison.invite;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InviteService {

    private final InviteRepository inviteRepository;

    // 약속초대거절
    public ResponseForm deny(Long inviteId) {
        ResponseForm responseForm = new ResponseForm();
        Invite deleteInvite = inviteRepository.findById(inviteId).orElse(null);

        inviteRepository.delete(deleteInvite);
        return responseForm.setSuccess(null);
    }

    // 약속 초대
    public ResponseForm invite(Long appointId, Long fromId, Long toId) {
        ResponseForm res = new ResponseForm();
        Invite invite = Invite.builder()
                .appointId(appointId)
                .fromId(fromId)
                .toId(toId).build();
        inviteRepository.save(invite);
        return res.setSuccess(null);
    }

}
