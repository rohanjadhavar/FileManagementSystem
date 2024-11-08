package com.fileManageent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fileManageent.entities.User;
import com.fileManageent.entities.UserRepo;

@Controller
public class HomeCotroller {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
	@GetMapping("/register")
	public String register(Model m) {
		m.addAttribute("user", new User());
		return "register";
	}
	
	
	@PostMapping("/process")
	public String process(@ModelAttribute("user") User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("USER");
		
		this.userRepo.save(user);
		
		return "redirect:/";
	}
}
