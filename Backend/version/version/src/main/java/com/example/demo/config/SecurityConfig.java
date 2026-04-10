package com.example.demo.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.csrf().disable();
         
         http.authorizeRequests().antMatchers("/user/login","/user/adduser/",
        		 "/user/addadmin","/coupan/viewAll",
        		 "/customer/**","/pizza/viewpizzalist/**",
        		 "/pizza/type/veg","/pizza/type/non-veg","/user/addsuperadmin",
        		 "/pizza/viewpizza/**,/pizza/**").permitAll()
         .and().authorizeRequests()
                 .antMatchers("/user/**").authenticated().and().httpBasic().and()
                 .formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
                 .and()
                 .logout()
                 .logoutUrl("/perform_logout")
                 .invalidateHttpSession(true)
                 .deleteCookies("JSESSIONID").logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).logoutSuccessUrl("/");
     
      }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}