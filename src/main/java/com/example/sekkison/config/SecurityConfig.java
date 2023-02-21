package com.example.sekkison.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 스프링 시큐리티 설정
@Configuration // 스프링컨테이너에 빈으로 생성
@EnableWebSecurity // WebSecurity 를 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    // PasswordEncoder 를 bean 으로 등록
	@Bean public PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF 비활성화
		http.csrf().disable();

		// 인증 설정
		http
			.authorizeRequests()
			// "/test/**" 로 들어오는 요청은 '인증'만 필요.
			.antMatchers("/test**").authenticated()
			// /admin/** 주소로 들어오는 요청은 '인증' + ADMIN 권한 필요
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			// 그 외에는 모두 접근 가능
			.anyRequest().permitAll()

		// 로그인 설정
		.and()
			// form 기반 인증 페이지 활성화.(.loginPage(url) 가 없으면 '디폴트 로그인' form 페이지가 활성화)
			.formLogin()
			// 로그인 필요한 상황 발생시 매개변수의 url (로그인 폼) 로 request 발생
			.loginPage("/user/login")
			// POST "/user/login" 접근시 시큐리티가 낚아채서 대신 로그인 진행
			// 위 요청이 오면 자동으로 loadUserByUsername() 가 실행되어 인증여부 확인진행
			.loginProcessingUrl("/user/login")
			// '직접 /login' → /loginOk 에서 성공하면 "/home" 로 이동시키기
			.defaultSuccessUrl("/home")
			// 로그인 성공직후 수행할 코드
			.successHandler(new CustomLoginSuccessHandler("/home"))
			// 로그인 실패하면 수행할 코드
			.failureHandler(new CustomLoginFailureHandler())

		// 로그아웃 설정
		.and()
			.logout()
			.logoutUrl("/user/logout")
			// CustomLogoutSuccessHandler 에서 꺼낼 정보가 있기에 false로 세팅
			.invalidateHttpSession(false)
            // 로그아웃 성공후 수행
			.logoutSuccessHandler(new CustomLogoutSuccessHandler())

		// 예외처리 설정
		.and()
			.exceptionHandling()
            // 권한(Authorization) 오류 발생시 수행
			.accessDeniedHandler(new CustomAccessDeniedHandler());
	}
}




















