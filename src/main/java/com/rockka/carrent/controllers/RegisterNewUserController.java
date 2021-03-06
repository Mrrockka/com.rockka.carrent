package com.rockka.carrent.controllers;

import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/*
** For user registration view(public/registration), script(js/register_new_user)
*/
@Controller
public class RegisterNewUserController {

	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(RegisterNewUserController.class);
	/*
	** Registering new user
	*/
	@RequestMapping("/register_new_user")
	@ResponseBody
	public String registerNewUser(@RequestBody User user){
		String answer = "";
		if(userService.isExists(user)){
			answer = "User with this name already exist.";
			logger.error(answer);
		} else{
			user.setRoles("ROLE_USER");
			userService.save(user);
			answer = "success";
		}
		return answer;
	}
}
