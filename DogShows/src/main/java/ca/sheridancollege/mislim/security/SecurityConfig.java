package ca.sheridancollege.mislim.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//***************************************
		//Allow us to use the h2 console GUI
		//Must be removes on production level code 
		http.csrf().disable();
		http.headers().frameOptions().disable();
		//***************************************
		http.authorizeRequests()
		//define what url's are restricted to specific roles 
		//we restrict url's not html pages.
		
		.antMatchers("/editDog","/delete/**","/userPage","/addDog","/viewDogs").hasAnyRole("ADMIN","OWNER")//
		.antMatchers("/addBreed").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/register").permitAll()
		.antMatchers(HttpMethod.POST,"/register").permitAll()
		.antMatchers("/","/showList","/css/**").permitAll()  //alow access without authentication
		.antMatchers("/h2/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/userPage")
		.permitAll()
		.and()
		.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
	
	@Autowired 
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Autowired 
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		

		
	}
}
