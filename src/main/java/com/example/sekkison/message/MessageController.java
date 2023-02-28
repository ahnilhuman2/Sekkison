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
    public ResponseForm list(@PathVariable("userId") Long userId) {
        return messageService.messageList(userId);
    }

    @ResponseBody
    @DeleteMapping("/{messageId}")
    public ResponseForm delete(@PathVariable("messageId") Long messageId) {
        return messageService.deleteMessage(messageId);
    }

    @ResponseBody
    @PostMapping("")
    public ResponseForm create(Message message) {
        return messageService.createMessage(message);
    }

}
