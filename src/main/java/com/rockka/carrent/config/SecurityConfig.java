package com.rockka.carrent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
@EnableWebSecurity
public class SecurityConfig
        extends WebSecurityConfigurerAdapter
{
    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/*","/car/*").permitAll()
                .antMatchers("/admin/**","/car/**").hasRole("ADMIN")
                .antMatchers("/user/**","/invoice/**").hasAnyRole("ADMIN","USER")
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/account").failureUrl("/login")
                .and().logout().logoutSuccessUrl("/login")
                .and().csrf().disable()
        ;
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers(
                "/resources/**"
                ,"/thumbs/**"
                ,"/images/**"
                ,"/css/**"
                ,"/js/**"
        );
//        web.debug(true);
    }
}
