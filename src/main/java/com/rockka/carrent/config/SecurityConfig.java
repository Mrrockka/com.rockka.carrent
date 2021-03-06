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
    /*
    ** Adding user roles to security bean
    */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    /*
    ** Adding address permits for user roles, configuring login and logout pages
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/*","/car/*").permitAll()
                .antMatchers("/admin/**","/car/**").hasRole("ADMIN")
                .antMatchers("/user/**","/invoice/**", "/payment/**").hasAnyRole("USER")
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/account").failureUrl("/login")
                .and().logout().logoutSuccessUrl("/login")
                .and().csrf().disable()
                .exceptionHandling().accessDeniedPage("/access_denied")
        ;
    }
    /*
    ** Adding sharable addresses
    */
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers(
                "/thumbs/**"
                ,"/images/**"
                ,"/css/**"
                ,"/js/**"
        );
    }
}
