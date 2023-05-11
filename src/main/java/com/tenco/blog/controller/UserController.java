package com.tenco.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tenco.blog.model.User;
import com.tenco.blog.service.UserSevice;

@Controller
public class UserController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserSevice userSevice;
	
	// 로그인 페이지 get -> page로 넘어갈 땐 메서드명 page 붙여주기
	@GetMapping("/auth/loginPage")
	public String loginPage() {
		return "/user/login_form";
	}
	
	// 로그인 처리 post -> RestApiController
	
	// 회원가입 페이지 get
	@GetMapping("/auth/joinPage")
	public String joinPage() {
		return "/user/join_form";
	}
	
	// 회원가입 처리 post -> RestApiController
	@PostMapping("/auth/userProc")
	public String saveUser(User user) {
		int result = userSevice.createUser(user);
		return "redirect:/user/loginPage";
	}
	
	// 로그아웃 get
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}
