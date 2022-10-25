package org.serratec.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class ConfigSeguranca extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	UserDetailsService userDetailsService;
	@Override
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder());
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("teste").password("{noop}12345").roles("ADMIN")
//		.and()
//		.withUser("teste2").password("{noop}12345").roles("USER","ADMIN");
//	}
		
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http.csrf().disable().authorizeHttpRequests()
		.antMatchers(HttpMethod.GET,"/api/produto/**","/api/categoria/**").permitAll()
		.antMatchers(HttpMethod.POST,"/api/produto/**","/api/categoria/**","/api/pedido/**","/api/cliente/**","api/usuario/**","/api/endereco/**").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/produto/**","/api/categoria/**","/api/pedido/**","/api/cliente/**","api/usuario/**","/api/endereco/**").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/produto/**","/api/categoria/**","/api/pedido/**","/api/cliente/**","api/usuario/**","/api/endereco/**").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET,"/api/cliente/**","api/usuario/**","/api/endereco/**","/api/pedido/**").hasAnyAuthority("ADMIN","USER")
	//	.antMatchers(HttpMethod.GET,"/api/endereco/**").hasAnyAuthority("ADMIN","USER")
	//	.antMatchers(HttpMethod.GET, "api/usuario/**").hasAnyAuthority("ADMIN","USER")
		//.antMatchers(HttpMethod.POST, "/usuario").hasAuthority("ADMIN")
		//.antMatchers(HttpMethod.GET,"/api/pedido/**").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		//.and()
		//.httpBasic()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.cors()
		.and();
		http.addFilter(new JwtAuthenticationFilter(this.authenticationManager(), jwtUtil));
		http.addFilter(new JwtAuthorizationFilter(this.authenticationManager(), jwtUtil, userDetailsService));
	}
	
	//configuracao do CORS
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
		return source;
		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
}
