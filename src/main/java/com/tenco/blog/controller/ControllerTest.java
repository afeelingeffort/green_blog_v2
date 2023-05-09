package com.tenco.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerTest {
	
	/*
	  prefix: /WEB-INF/view
      suffix: .jsp
      */
	@GetMapping("/temp")
	public String tempPage() {
		return "/temp";
	}
}
