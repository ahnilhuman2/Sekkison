package com.example.sekkison.user_file;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/userFiles")
@RequiredArgsConstructor
public class UserFileController {

    private final UserFileService userFileService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @ResponseBody
    @PostMapping("/upload")
    public ResponseForm uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("userId") Long userId) {
        try {
            return userFileService.uploadFile(userId, file);
        } catch (IOException e) {
            return null;
        }
    }

}
