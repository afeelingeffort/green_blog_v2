package com.tenco.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	// 로그인 페이지 get -> page로 넘어갈 땐 메서드명 page 붙여주기
	@GetMapping("/loginPage")
	public String loginPage() {
		return "/user/login_form";
	}
	// 로그인 처리 post
	
	// 회원가입 페이지 get
	@GetMapping("/joinPage")
	public String joinPage() {
		return "/user/join_form";
	}
	// 회원가입 처리 post
	
	// 로그아웃 get
}
