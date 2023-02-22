package com.example.sekkison.invite;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invites")
@RequiredArgsConstructor
public class InviteController {

    private final InviteService inviteService;

    @ResponseBody
    @DeleteMapping("")
    public ResponseForm deny(Long inviteId) {
        return inviteService.deny(inviteId);
    }
}
