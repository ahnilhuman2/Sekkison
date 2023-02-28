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
    @GetMapping("/is_master/{userId}/{appointId}")
    public ResponseForm isMaster(
            @PathVariable("userId") Long userId,
            @PathVariable("appointId") Long appointId) {
        return myAppointService.isMaster(userId, appointId);
    }

    @ResponseBody
    @PostMapping("/{userId}/{appointId}")
    public ResponseForm participate(
            @PathVariable("userId") Long userId,
            @PathVariable("appointId") Long appointId) {
        return myAppointService.participate(userId, appointId);
    }
}
