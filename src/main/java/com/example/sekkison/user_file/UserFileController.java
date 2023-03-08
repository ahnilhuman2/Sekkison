package com.example.sekkison.user_file;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/userFiles")
@RequiredArgsConstructor
public class UserFileController {

    private final UserFileService userFileService;

    // 파일저장 경로
    @Value("${file.upload-dir}")
    private String uploadDir;

    // 프로필 업로드
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

    // 프로필 불러오기
    @GetMapping( value = "/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable("userId") Long userId) throws IOException {
        String imageName = userFileService.getFile(userId);
        InputStream in = new FileInputStream(uploadDir + "/" + imageName);

        return IOUtils.toByteArray(in);
    }
}
