package com.example.sekkison.config;

import com.example.sekkison.authority.Authority;
import com.example.sekkison.user.User;
import com.example.sekkison.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PrincipalDetails implements UserDetails{
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private User user;
	public User getUser() { // 로그인한 사용자의 정보 (Entity) 추출
		return user;
	}
	
	public PrincipalDetails(User user) { this.user = user; }

	// 해당 User 의 '권한(들)'을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();

		// userService에서 권한 목록 받아오기
		List<Authority> list = userService.selectAuthoritiesById(user.getId());
		for(Authority auth: list) {
			collect.add(new GrantedAuthority() {
				@Override public String getAuthority() { return auth.getAuth(); }
				@Override public String toString() { return auth.getAuth(); }
			});
		}
		return collect;
	}

	// 로그인 한 사용자의 password 는 ?
	@Override public String getPassword() {
		return user.getPassword();
	}

	// 로그인 한 사용자의 username 은 ?
	@Override public String getUsername() {
		return user.getUsername();
	}
	
	// 로그인 한 사용자의 name 은 ?
	public String getName() { return user.getName(); }

	// 계정이 만료된건 아닌지?
	@Override public boolean isAccountNonExpired() {
		return true;
	}
	// 계정이 잠긴건 아닌지?
	@Override public boolean isAccountNonLocked() {
		return true;
	}
	// 계정 credential 이 만료된건 아닌지? (계정정보 보안가능한 저장영역 ? 기한?)
	@Override public boolean isCredentialsNonExpired() {
		return true;
	}
	// 활성화 되었나? (휴면계정 같은거)
	@Override public boolean isEnabled() {
		return true;
	}
	
}
