package com.example.board.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.board.entity.User;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired UserRepository userRepository;
	@Autowired BoardRepository boardRepository;

	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}
	
	@PostMapping("/signin")
	public String signinPost(@ModelAttribute User user,HttpSession session) {
		String email = user.getEmail();
		String pwd = user.getPwd();

		List<User> info = userRepository.findByEmailAndPwd(email, pwd);
		// System.out.println(info.size());
		//info.isPresent()
		if(info.size() == 1){
			session.setAttribute("user", info.get(0));
		}
		return "redirect:/";
	}

	@GetMapping("/signout")
	public String signout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// 회원가입 
	@GetMapping("/signup") 
	public String signup() {
		
		return "signup";
	}

	@PostMapping("/signup")
	public String signupPost(@ModelAttribute User user) {
		Date date = new Date();

		user.setEbtbDate(date);
		// User result = userRepository.save(user);
		userRepository.save(user);
		return "redirect:/";
	}
}