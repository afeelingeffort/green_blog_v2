package com.tenco.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tenco.blog.model.User;

public class PrincipalDetail implements UserDetails {

	// User
	private User user;

	public PrincipalDetail(User user) {
		this.user = user;
	}

	/**
	 * 계정의 권한을 반환한다.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// "ROLE_" 스프링 시큐리티 규칙 (꼭 넣어야 함)
		// "ROLE_" + user.getRole();
		Collection<GrantedAuthority> collections = new ArrayList<GrantedAuthority>();
		
//		collections.add(new GrantedAuthority() {
//
//			@Override
//			public String getAuthority() {
//				return "ROLE_" + user.getRole();
//			}
//		});

		collections.add(() -> {
			return "ROLE" + user.getRole();
		});
		
		return collections;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	/**
	 * 계정 만료되지 않았는지 여부를 리턴한다. true : 계정 만료 안 됨 false : 계정 만료됨
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 계정 잠김 여부 확인 true : 사용 가능 false : 사용 불가능
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 비밀번호 만료 여부를 알려준다. true : 사용 가능 false : 사용 불가능
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 계정 활성화 여부 true : 사용 가능 false : 로그인 불가능
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
