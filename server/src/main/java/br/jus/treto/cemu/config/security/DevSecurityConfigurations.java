package br.jus.treto.cemu.config.security;
//
//package br.jus.treto.sceu.config.security;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import br.jus.treto.sceu.config.security.jwt.AutenticacaoViaTokenFilter;
//import br.jus.treto.sceu.config.security.jwt.TokenService;
//import br.jus.treto.sceu.domain.LoginForm;
//import br.jus.treto.sceu.domain.User;
//import br.jus.treto.sceu.repository.UsuarioRepository;
//
//@EnableWebSecurity
//@Configuration
//@Profile( "dev" )
//public class DevSecurityConfigurations extends WebSecurityConfigurerAdapter {
//	//Configuracoes de autorizacao
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/**").permitAll() 
//			.antMatchers("/h2-console/**").permitAll()
//			.antMatchers("/console/**").permitAll()
//				
//			.and().csrf().disable()
//			//habilita h2-console
//			.headers().frameOptions().disable();
//	}
//	
//}
//
