package com.example.sekkison.user_file;

import com.example.sekkison.common.ResponseForm;
import com.example.sekkison.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserFileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private static final long MAX_IMAGE_SIZE = 5242880;

    private final UserFileRepository userFileRepository;
    private final UserRepository userRepository;

    public ResponseForm uploadFile(Long userId, MultipartFile file) throws IOException {
        ResponseForm responseForm = new ResponseForm();
        if (file.isEmpty() || "".equals(file.getName())) {
            UserFile userFile = userFileRepository.findByUserId(userId).orElse(null);
            if (userFile != null && !userFile.getFile().equals("default.jpg"))
                new File(uploadDir + "/" + userFile.getFile()).delete();
            userFile.setFile("default.jpg");
            userFileRepository.save(userFile);
            return responseForm.setSuccess(null);
        }

        // 이미지 크기 제한 체크
        if (file.getSize() > MAX_IMAGE_SIZE) {
            throw new RuntimeException("이미지 크기가 너무 큽니다.");
        }

        String fileName = writeFile(file);

        UserFile userFile = userFileRepository.findByUserId(userId).orElse(null);
        if (userFile != null && !userFile.getFile().equals("default.jpg"))
            new File(uploadDir + "/" + userFile.getFile()).delete();

        userFile.setFile(fileName);
        userFileRepository.save(userFile);

        return responseForm.setSuccess(null);
    }

    private String writeFile(MultipartFile file) throws IOException {
        String extension = com.google.common.io.Files.getFileExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID().toString() + "." + extension;
        byte[] fileContent = file.getBytes();

        String filePath = uploadDir + "/" + fileName;

        Path path = Paths.get(filePath);
        Files.write(path, fileContent);
        return fileName;
    }

    public String getFile(Long userId) {
        UserFile userFile = userFileRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("UserFile not found with userId: " + userId));
        return userFile.getFile();
    }
}
