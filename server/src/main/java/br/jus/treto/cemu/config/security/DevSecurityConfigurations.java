package br.jus.treto.cemu.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@Profile( "prod" )
public class DevSecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception  {
		return super.authenticationManager();
	}
	
	//Configuracoes de autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**").permitAll() 
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/console/**").permitAll()
				
			.and().csrf().disable();
			//habilita h2-console
		//	.headers().frameOptions().disable();
	}
	
}

