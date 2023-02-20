package com.example.sekkison.appoint;

import com.example.sekkison.common.ResponseForm;
import com.example.sekkison.my_appoint.MyAppoint;
import com.example.sekkison.my_appoint.MyAppointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appoints")
@RequiredArgsConstructor
public class AppointController {
    private final AppointService appointService;
    private final MyAppointService myAppointService;

    @ResponseBody
    @PostMapping("")
    public ResponseForm create(MyAppoint myAppoint, Appoint appoint) {
        ResponseForm res = appointService.createAppoint(myAppoint, appoint);
        return res;
    }
}
