package com.springboot.rest_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.rest_api.service.MyUserService;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	MyUserService myUserService;

//SecurityConfig(AuthenticationProvider getAuth) {
//}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
						.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/api/public/hello").permitAll()
						.requestMatchers("/api/auth/token/generate").permitAll()
						.requestMatchers("/api/private/hello").authenticated().requestMatchers("/api/auth/signup")
						.permitAll().anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

//@Bean
//AuthenticationProvider getAuth()
//{
//	DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//	dao.setPasswordEncoder(passwordEncoder());
//	dao.setUserDetailsService(myUserService);
//	return dao;
//}

	@Bean
	UserDetailsService userDetailsService() {

		User user1 = (User) User.withUsername("Bhavesh").password("{noop}12345").roles("Default_User").build();

		User user2 = (User) User.withUsername("Ram").password("{noop}12345").roles("Default_User").build();

		return new InMemoryUserDetailsManager(user1, user2);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception
	{
		return auth.getAuthenticationManager();
	}

}