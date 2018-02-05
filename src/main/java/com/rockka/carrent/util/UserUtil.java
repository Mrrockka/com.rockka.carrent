package com.rockka.carrent.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
/*
** For for convenience
*/
public class UserUtil {
	/*
	** Returns principal for children classes
	*/
	protected UserDetails getPrincipal(){
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails ?
				(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;
	}

}
