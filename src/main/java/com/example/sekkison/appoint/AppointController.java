package com.example.sekkison.appoint;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appoints")
@RequiredArgsConstructor
public class AppointController {
    private final AppointService appointService;

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
    @PutMapping("/{appoint_id}/{user_id}")
    // 약속 수정
    public ResponseForm update(
            @PathVariable("user_id") Long user_id,
            @PathVariable("appoint_id") Long appoint_id,
            Appoint appoint) {
        return appointService.updateAppoint(user_id, appoint_id, appoint);
    }
    @ResponseBody
    @DeleteMapping("/{user_id}/{appoint_id}")
    // 약속 삭제
    public ResponseForm delete(
            @PathVariable("user_id") Long user_id,
            @PathVariable("appoint_id") Long appoint_id) {
        return appointService.deleteAppoint(user_id, appoint_id);
    }
    @ResponseBody
    @GetMapping("/members/{appoint_id}")
    // 멤버 별명 가져오기
    public ResponseForm getMembers(@PathVariable("appoint_id") Long appoint_id) {
        return appointService.getAppointMembers(appoint_id);
    }
    @ResponseBody
    @DeleteMapping("/members/{appoint_id}/{from_id}/{to_id}")
    // 약속 멤버 한명 강퇴
    public ResponseForm getMembers(
            @PathVariable("appoint_id") Long appoint_id,
            @PathVariable("from_id") Long from_id,
            @PathVariable("to_id") Long to_id) {
        return appointService.deleteAppointMembers(appoint_id, from_id, to_id);
    }
    @ResponseBody
    @GetMapping("/search/{search}/{is_public}/{is_recruit}/{page}")
    // 약속 검색
    public ResponseForm searchAppointList(
            @PathVariable("search") String search,
            @PathVariable("is_public") Integer is_public,
            @PathVariable("is_recruit") Integer is_recruit,
            @PathVariable("page") Integer page) {
        return appointService.getSearchAppointList(search, is_public, is_recruit, page);
    }
    @ResponseBody
    @PutMapping("/setCount/{appoint_id}/{user_id}/{count}")
    public ResponseForm updateMaxCnt(
            @PathVariable("appoint_id") Long appoint_id,
            @PathVariable("user_id") Long user_id,
            @PathVariable("count") Integer count) {
        return appointService.updateMaxCnt(appoint_id, user_id, count);
    }
}
