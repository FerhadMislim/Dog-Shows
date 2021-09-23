package ca.sheridancollege.mislim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.mislim.beans.User;
import ca.sheridancollege.mislim.repositories.SecurityRepository;

@Controller
public class SecurityController {
	@Autowired
	@Lazy
	private SecurityRepository secRepo;
	
	@GetMapping("/login")
	public String goLogin() {
		return "login";
	}
	
	@GetMapping("/access-denied")
	public String goToAccessDenied() {
		return "access-denied";
	}
	
	@GetMapping("/register")
	public String goToRegistration() {
		return "registration";
	}
	
	@PostMapping("/register")
	public String registerNewUser(@RequestParam String username, @RequestParam String password) {
		
		if (secRepo.findUserAccount(username) != null) {
			//the username already exists.
			
			return"redirect:/register";
		}else {
			secRepo.addUser(username, password);
			User user = secRepo.findUserAccount(username);
				secRepo.addRole(user.getUserId(),2);//OWNER
			
			return"redirect:/";
		}
		
	}

}
