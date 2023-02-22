package com.example.sekkison.common;

import com.example.sekkison.config.PrincipalDetails;
import com.example.sekkison.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class U {
    // 현재 request 구하기
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attrs.getRequest();
    }

    // 현재 session 구하기
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    // 현재 로그인한 사용자 UserDetail 구하기
    public static User getLoggedUser() {
        // 현재 로그인한 작성자 정보
        PrincipalDetails userDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    public static void printFileInfo(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();

        if(originalFileName == null || originalFileName.length() == 0) {
            System.out.println("\t파일이 없습니다");
            return;
        }

        // 이미지 파일 여부
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file.getInputStream());

            // try catch를 위해 작성
            if (bufferedImage != null) {
                bufferedImage.getWidth();
                bufferedImage.getHeight();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
