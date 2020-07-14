package hasan.kara.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import hasan.kara.domain.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EntityScan(basePackages = {"hasan.kara"})
@ComponentScan(basePackages = {"hasan.kara"})
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userService)
		.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		        http.
	        		csrf().disable()
	                .authorizeRequests()
	                //.antMatchers("/").permitAll()
	                .antMatchers("/login/**").permitAll()
	                .antMatchers("/registration/**").permitAll()
	                .antMatchers("/events/view/**").permitAll()
	                .antMatchers("/guest/**").permitAll()
	                .antMatchers("/admin/**").hasAuthority("ADMIN")
	                .anyRequest()
	                .authenticated().and().csrf().disable().formLogin()
	                .loginPage("/login").failureUrl("/login?error=true")
	                .usernameParameter("username")
	                .passwordParameter("password")
	                .and().logout()
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                .logoutSuccessUrl("/").and().exceptionHandling()
	                .accessDeniedPage("/error/accessdenied");

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/resources/**",
					"/static/**",
					"/css/**",
					"/js/**",
					"/images/**",
					"/dist/**",
					"/favicon.co",
					"/plugins/**");
	}

}