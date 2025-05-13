package com.example.board.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.entity.User;
import com.example.board.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired UserRepository userRepository;

	@GetMapping("/signin")
	public String signin(
		@RequestParam(required = false) 
				String redirect,
		Model model
	) {
		model.addAttribute("redirect", redirect);
		return "signin";
	}
	
	@PostMapping("/signin")
	public String signinPost(@ModelAttribute User user, HttpSession session) {
		Optional<User> opt = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
		
		if(opt.isPresent()) {
			session.setAttribute("user_info", opt.get());
		}

		return "redirect:/";
	}

	@GetMapping("/signout")
	public String signout(HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}
	
	@GetMapping("/signup") 
	public String signup() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signupPost(
		@ModelAttribute User user) {
		userRepository.save(user);
		return "redirect:/";
	}
}