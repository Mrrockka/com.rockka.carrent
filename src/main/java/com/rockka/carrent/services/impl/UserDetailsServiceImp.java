package com.rockka.carrent.services.impl;

import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(nickname);

        if(user != null){
            List<GrantedAuthority> roles = Stream
                    .of(StringUtils.commaDelimitedListToStringArray(user.getRoles()))
                    .map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
        } else {
            return null;
        }

    }

}
