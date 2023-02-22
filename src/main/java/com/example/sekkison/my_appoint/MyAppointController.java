package com.example.sekkison.my_appoint;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
