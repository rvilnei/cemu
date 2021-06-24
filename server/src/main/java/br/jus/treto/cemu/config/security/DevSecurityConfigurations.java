
package br.jus.treto.cemu.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.jus.treto.cemu.config.security.jwt.AutenticacaoViaTokenFilter;
import br.jus.treto.cemu.config.security.jwt.TokenService;
import br.jus.treto.cemu.domain.User;
import br.jus.treto.cemu.repository.UsuarioRepository;
import br.jus.treto.cemu.resources.form.LoginForm;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevSecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception  {
		return super.authenticationManager();
	//	return super.authenticationManagerBean();
	}
	
	//Configuracoes de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(new CustomAuthenticationProvider(usuarioRepository));
	}

	//Configuracoes de autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()	
		.antMatchers("/user/login").permitAll()
		.antMatchers("/console/**").permitAll()
		 .antMatchers("/reports/**").permitAll()
		 .antMatchers("/reports/*/*").permitAll()
		// A ROLE CADASTRADA NA TABELA PERFIS SEGUE O PADRÃO ROLE_XXXX EX: ROLE_USUARIO, ROLE_TESTE //
		//.antMatchers(HttpMethod.GET, "/materiais").hasRole("USUARIO")	
		//.antMatchers(HttpMethod.DELETE, "/materiais/*").hasRole("ADMINITRADOR")	
		.antMatchers("/h2-console/**").permitAll()
	    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Habilitar crossOrigin
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy( SessionCreationPolicy .STATELESS )
		.and().addFilterBefore(new AutenticacaoViaTokenFilter( tokenService, usuarioRepository ), UsernamePasswordAuthenticationFilter.class)

		.csrf().disable()
		.headers().frameOptions().disable();
	}

	//Configuracoes de recursos estaticos(js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
	  web
      .ignoring()
      .antMatchers("/css/**", "/js/**")
	  .antMatchers("/*")
      .antMatchers("*.json", "/*.css", "/*.js", "/*.ico")
	  .antMatchers("/")
      .antMatchers("/console/**");
	}
	
}

