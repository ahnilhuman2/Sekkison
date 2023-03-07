package com.example.sekkison.user_file;

import com.example.sekkison.common.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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

    @GetMapping(
            value = "/{userId}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable("userId") Long userId) throws IOException {
        String imageName = userFileService.getFile(userId);
//        InputStream in = getClass().getResourceAsStream(uploadDir + "/" + imageName);
        InputStream in = new FileInputStream(uploadDir + "/" + imageName);

        return IOUtils.toByteArray(in);
    }
}
