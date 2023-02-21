package com.example.sekkison.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	public CustomLoginSuccessHandler(String defaultTargetUrl) {
		// 로그인후 특별히 redirect 할 url 이 없는경우 기본적으로 redirect 할 url
		setDefaultTargetUrl(defaultTargetUrl);
	}

	// 로그인 성공 직후 호출
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
    	
        // 로그인 직전 url 로 redirect 하기
        super.onAuthenticationSuccess(request, response, authentication);
	}

    // request 를 한 client ip 가져오기
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
             ip = request.getHeader("Proxy-Client-IP");
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
             ip = request.getHeader("WL-Proxy-Client-IP");
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
             ip = request.getHeader("HTTP_CLIENT_IP");
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
             ip = request.getHeader("HTTP_X_FORWARDED_FOR");
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
             ip = request.getRemoteAddr();
         return ip;
    }    


	
}











