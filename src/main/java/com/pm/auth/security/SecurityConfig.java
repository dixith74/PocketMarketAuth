package com.pm.auth.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pm.common.security.JwtAuthenticationConfig;
import com.pm.common.security.JwtUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationConfig jwtConfig;

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}pass").roles("USER");
	}*/
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// @formatter:off
		web
			.ignoring()
			.antMatchers(HttpMethod.OPTIONS)
			.antMatchers("/user/register",
						"/user/login",
						"/user/update/**",
						"/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security/**",
                        "/swagger-ui.html",
                        "/webjars/**");
		// @formatter:on
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
               .exceptionHandling().authenticationEntryPoint(
                        (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
			.and()
				.addFilterAfter(new JwtUsernamePasswordAuthenticationFilter(jwtConfig, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, jwtConfig.getUrl()).permitAll()
				.anyRequest().authenticated();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public JwtAuthenticationConfig jwtConfig() {
        	return new JwtAuthenticationConfig();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
