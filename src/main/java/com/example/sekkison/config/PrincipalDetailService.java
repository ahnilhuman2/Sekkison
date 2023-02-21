package com.example.sekkison.config;

import com.example.sekkison.user.User;
import com.example.sekkison.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//시큐리티 설정에서 loginProcessingUrl(url) 을 설정해 놓았기에
//로그인시 위 url 로 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는
//loadUserByUsername() 가 실행되고
//인증성공하면 결과를 UserDetails 로 리턴

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService{
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username으로 DB에서 유저 찾기
		User user = userService.findByUsername(username);

		// 유저가 없으면 UsernameNotFoundException을 throw 해주도록 한다
		if (user == null) throw new UsernameNotFoundException(username);

		// 유저가 있다면 UserDetails 를 생성하여 리턴
		else {
			PrincipalDetails userDetails = new PrincipalDetails(user);
			userDetails.setUserService(userService);
			return userDetails;
		}
	}
	
	@Transactional
	public boolean checkMemberPassword(String inputPassword, String username) {
	    return passwordEncoder.matches(inputPassword, loadUserByUsername(username).getPassword());
	}
}