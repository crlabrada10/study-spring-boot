package com.home.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Carlos Rafael Labrada Arce
 *
 */

@Configuration
@EnableWebSecurity
// para seguridad a nivel de metodo en los servicio con la anotación de spring @Secured({"Nombre del rol"})
//@EnableGlobalMethodSecurity(securedEnabled = true)
// Si se considera que se va a migrar a otra tecnología por ejemplo Dropwizard
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
	
	//Para crear usuario y contraseña en memoria
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password(encoder().encode("password")).roles("ADMIN").and()
			.withUser("user").password(encoder().encode("password")).roles("USER");
	}*/
	
	/**CSRF  Cross Site Request Forgery*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests()
			.antMatchers("/users/roles/**").permitAll()	
				.antMatchers("/users/**").hasRole("ADMIN")
				.antMatchers("/roles/**").authenticated()
				
				//.antMatchers("/roles/**").permitAll().anyRequest().authenticated()
				
				.and().httpBasic();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
