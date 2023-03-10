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

    @ResponseBody
    @PostMapping("/{appointId}/{fromId}/{toId}")
    // 초대보내기
    public ResponseForm invite(
            @PathVariable("appointId") Long appointId,
            @PathVariable("fromId") Long fromId,
            @PathVariable("toId") Long toId
            ) {
        return inviteService.invite(appointId, fromId, toId);
    }
}
