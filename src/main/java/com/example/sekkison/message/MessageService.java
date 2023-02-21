package com.example.sekkison.message;

import com.example.sekkison.common.ResponseForm;
import com.example.sekkison.user.User;
import com.example.sekkison.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ResponseForm messageList(Long userId) {
        ResponseForm responseForm = new ResponseForm();
        List<Message> messages = messageRepository.findByToId(userId);

        return responseForm.setSuccess(true, messages);
    }

    public ResponseForm deleteMessage(Long messageId) {
        ResponseForm responseForm = new ResponseForm();
        Message deleteMessage = messageRepository.findById(messageId).orElse(null);
        messageRepository.delete(deleteMessage);

        return responseForm.setSuccess(true, null);
    }

    public ResponseForm createMessage(Message message) {
        ResponseForm responseForm = new ResponseForm();

        if (userRepository.findById(message.getFromId()) == null) {
            responseForm.setError("보내는 사람을 입력해주세요", false);
            return responseForm;
        }

        if (userRepository.findById(message.getToId()) == null) {
            responseForm.setError("받는 사람을 입력해주세요", false);
            return responseForm;
        }

        messageRepository.save(message);
        
        return responseForm.setSuccess(true, null);
    }
}
