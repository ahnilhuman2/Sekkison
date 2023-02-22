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

    public ResponseForm deny(Long inviteId) {
        ResponseForm responseForm = new ResponseForm();
        Invite deleteInvite = inviteRepository.findById(inviteId).orElse(null);

        inviteRepository.delete(deleteInvite);
        return responseForm.setSuccess(true, null);
    }
}
