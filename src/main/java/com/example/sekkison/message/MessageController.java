package com.example.sekkison.message;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @ResponseBody
    @GetMapping("/list/{userId}")
    // 쪽지 목록
    public ResponseForm list(@PathVariable("userId") Long userId) {
        return messageService.messageList(userId);
    }

    @ResponseBody
    @DeleteMapping("/{messageId}")
    // 쪽지 삭제
    public ResponseForm delete(@PathVariable("messageId") Long messageId) {
        return messageService.deleteMessage(messageId);
    }

    @ResponseBody
    @PostMapping("")
    // 쪽지 보내기
    public ResponseForm create(Message message) {
        return messageService.createMessage(message);
    }

}
