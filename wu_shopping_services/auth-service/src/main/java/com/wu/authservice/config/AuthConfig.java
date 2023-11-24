package com.wu.authservice.config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.wu.authservice.repository.UsersRepository;
import com.wu.authservice.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
//@EnableJpaRepositories(basePackages = "com.wu.authservice.repository")
//@EnableTransactionManagement
public class AuthConfig {
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.csrf().disable()
//		.authorizeHttpRequests().requestMatchers("/auth/register","/auth/token","/auth/validate")
//		.permitAll().and().authorizeHttpRequests().requestMatchers(antMatcher("/h2-console/**")).permitAll()
//		.and()
//		.build();

		//Working
		return http.csrf().disable()
				.authorizeHttpRequests().requestMatchers("/auth/register","/auth/token","/auth/validate","/registration/**")
				.permitAll().and().authorizeHttpRequests().requestMatchers(toH2Console()).permitAll()
				.and().headers().frameOptions().sameOrigin().and()
				.build();
//		return http.csrf().disable()
//				.authorizeHttpRequests().requestMatchers("/auth/logout","/auth/register","/auth/token","/auth/validate","/registration/**")
//				.permitAll().and().authorizeHttpRequests().requestMatchers(toH2Console()).permitAll()
//				.and().headers().frameOptions().sameOrigin().and().sessionManagement(session ->session
//						.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//						.invalidSessionUrl("/auth/logout")
//						.maximumSessions(1)
//						.maxSessionsPreventsLogin(false))
//				.logout(logout->logout.deleteCookies("JSESSIONID").invalidateHttpSession(true))
//				.build();
		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
		
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
		
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
		
	}
	
//	@Bean
//	public UsersRepository userRep() {
//		return new UsersRepository;
//		
//	}
	
	
	
	
}
