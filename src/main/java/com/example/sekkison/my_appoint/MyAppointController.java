package com.example.sekkison.my_appoint;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myAppoints")
@RequiredArgsConstructor
public class MyAppointController {

    private final MyAppointService myAppointService;

    @ResponseBody
    @GetMapping("/is_master")
    public ResponseForm isMaster(Long userId, Long appointId) {
        return myAppointService.isMaster(userId, appointId);
    }

    @ResponseBody
    @PostMapping("")
    public ResponseForm participate(Long userId, Long appointId) {
        return myAppointService.participate(userId, appointId);
    }
}
