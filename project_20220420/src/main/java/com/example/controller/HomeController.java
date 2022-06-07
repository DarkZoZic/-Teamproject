package com.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// 127.0.0.1:9090/cluver/
	// 127.0.0.1:9090/cluver/home
	// 127.0.0.1:9090/cluver/main
	@GetMapping(value = {"/", "/home", "/main"})
	public String homeGET(
			Model model,
			@AuthenticationPrincipal User user) {
		model.addAttribute("user", user);
		return "/vue/index";
	}
	
	// @GetMapping(value="/page403")
	// public String page403GET() {
	// 	return "page403";
	// }	
}
