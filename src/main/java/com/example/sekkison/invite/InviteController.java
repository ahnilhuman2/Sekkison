package com.example.sekkison.invite;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("invites")
@RequiredArgsConstructor
public class InviteController {

    private final InviteService inviteService;

    @ResponseBody
    @DeleteMapping("/{inviteId}")
    // 약속초대거절
    public ResponseForm deny(@PathVariable("inviteId") Long inviteId) {
        return inviteService.deny(inviteId);
    }
}
