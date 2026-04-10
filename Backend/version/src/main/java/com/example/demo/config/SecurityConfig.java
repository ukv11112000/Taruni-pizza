package com.example.demo.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.service.CustomUserDetailsService;




@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    

    @Autowired
	private JwtRequestFilter jwtRequestFilter;
    
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	http.cors();
//    	 http.csrf().disable();
//        http.userDetailsService(customUserDetailsService);
//         http.authorizeRequests().antMatchers("/user/login","/user/adduser/",
//        		 "/user/addadmin","/coupan/viewAll",
//        		 "/customer/**","/pizza/viewpizzalist/**",
//        		 "/pizza/type/veg","/pizza/type/non-veg","/user/addsuperadmin",
//        		 "/pizza/viewpizza/**,/pizza/**,/user/**").permitAll()
//                 .and().authorizeRequests()
//                 .antMatchers("/pp").authenticated().and().httpBasic().and()
//                 .formLogin().loginPage("/user/login").failureUrl("/login?error").permitAll()
//                 .and()
//                 .logout()
//                 .logoutUrl("/pizza")
//                 .invalidateHttpSession(true)
//                 .deleteCookies("JSESSIONID").logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).logoutSuccessUrl("/pizza");
//    	 
//   
//      }
////    
    @Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.cors();
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/user/all","/user/addadmin/**","/coupan/viewAll","/user/forgotpassword"
						,"/user/addsuperadmin","/user/delete","/coupan/**","/customer/delete"
						,"/order/viewAll","/pizza/add","/pizza/delete/**","/pizza/update","/pizzaOrder","/hello","/customer/all").hasAnyRole("ADMIN","SUPERADMIN")
				.antMatchers("/user/logout").hasAnyRole("USER","ADMIN","SUPERADMIN").
				antMatchers("/authenticate","/customer/add","/pizza/viewpizzalist/**","/","/customer/currentCustomer/**",
						"/customer/update","/order/add","/pizzaOrder/**","/order/viewAll/{id}","/pizza/viewpizza/**","/user/currentUser/**","/user/login").permitAll().
						anyRequest().authenticated().and().
						formLogin().loginPage("http://localhost:4200/login").failureUrl("/login?error").permitAll().and().
						exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

   
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
    	return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
 
  
}