package com.tenco.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.blog.dto.ResponseDto;
import com.tenco.blog.model.User;
import com.tenco.blog.service.UserSevice;

@RestController
public class UserApiController {

	@Autowired
	private UserSevice userSevice;

	@Autowired
	private HttpSession session;

	// 회원가입 처리
	@PostMapping("/api/user")
	public ResponseDto<Integer> saveUser(@RequestBody User user) {
		int result = userSevice.createUser(user);

		return new ResponseDto<>(HttpStatus.OK, result);
	}

	// 전통적인 로그인 방식으로 사용하지 않음
	// 시큐리티가 알아서 로그인 처리해줌
	// /auth/loginProc 주소를 감지하고 있다가 요청이 들어오면
	// 시큐리티 로그인 처리 동작을 진행한다.
	
//	@PostMapping("/api/user/login")
//	public ResponseDto<?> loginUser(@RequestBody User user) {
//		// 유효성 검사
//		// 서비스 호출해서 결과값 받기
//		// principal : 접근 주체
//		User principal = userSevice.readUser(user);
//		if (principal != null) {
//			// !! 세션 !!
//			session.setAttribute("principal", principal);
//		}
//
//		return new ResponseDto<Integer>(HttpStatus.OK, 1);
//	}
//
//	@GetMapping("/api/user/logout")
//	public void logoutUser(){
//		
//	}
}
