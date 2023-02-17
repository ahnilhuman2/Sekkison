package com.example.sekkison.appoint;

import com.example.sekkison.common.ResponseForm;
import com.example.sekkison.my_appoint.MyAppointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appoints")
@RequiredArgsConstructor
public class AppointController {
    private final AppointService appointService;
    private final MyAppointService myAppointService;

    @ResponseBody
    @PostMapping("/{user_id}")
    // 약속 만들기
    public ResponseForm create(
            @PathVariable("user_id") Long user_id, Appoint appoint) {
        return appointService.createAppoint(user_id, appoint);
    }
    @ResponseBody
    @GetMapping("/{appoint_id}")
    // 약속 가져오기
    public ResponseForm read(
            @PathVariable("appoint_id") Long appoint_id) {
        return appointService.readAppoint(appoint_id);
    }
    @ResponseBody
    @PutMapping("/{user_id}/{appoint_id}")
    // 약속 수정
    public ResponseForm update(
            @PathVariable("user_id") Long user_id,
            @PathVariable("appoint_id") Long appoint_id,
            Appoint appoint) {
        return appointService.updateAppoint(user_id, appoint_id, appoint);
    }

}
