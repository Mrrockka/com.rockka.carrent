package com.rockka.carrent.controllers;

import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class RegistrationController {

	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@PostMapping("/register")
	@ResponseBody
	public String registerNewUser(@RequestBody User user){
		String answer = "";
		if(userService.isExists(user)){
			answer = "User with this name already exist.";
			logger.debug("user is exists, answer is : " + answer);
		} else{
			user.setRoles("ROLE_USER");
			userService.save(user);
			answer = "welcome";
		}
		return answer;
	}
}
