package com.example.sekkison.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{

    // 로그아웃 성공 직후 호출
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

        // invalidate
        request.getSession().invalidate();
        
        // ret_url  이 있는 경우 logout 하고 해당  url 로 redirect
        String redirectUrl = "/user/login?logoutHandler";
        if(request.getParameter("ret_url") != null) redirectUrl = request.getParameter("ret_url");
        
        response.sendRedirect(redirectUrl);
	}
}