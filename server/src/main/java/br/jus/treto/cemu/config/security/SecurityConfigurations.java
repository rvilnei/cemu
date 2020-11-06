
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
import br.jus.treto.cemu.domain.LoginForm;
import br.jus.treto.cemu.domain.User;
import br.jus.treto.cemu.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
//@Profile("prod")
//@Profile("dev")
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception  {
		return super.authenticationManager();
	}
	
	//Configuracoes de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
		 auth.authenticationProvider(new CustomAuthenticationProvider(usuarioRepository));
	}
	/****
	//Configuracoes de autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/css/**", "/js/**").permitAll()
			.antMatchers("/*").permitAll()
	        .antMatchers("*.json", "/*.css", "/*.js", "/*.ico").permitAll()
			.antMatchers("/").permitAll().and()
	        .authorizeRequests().antMatchers("/console/**").permitAll()
			.antMatchers( HttpMethod.GET, "/user/login" ).permitAll()
			.antMatchers( HttpMethod.POST, "/user/login" ).permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Habilitar crossOrigin
			.antMatchers("/h2-console/**").permitAll()
			
			// A ROLE CADASTRADA NA TABELA PERFIS SEGUE O PADRÃO ROLE_XXXX EX: ROLE_USUARIO, ROLE_TESTE //
//			.antMatchers(HttpMethod.GET, "/materiais").hasRole("USUARIO")	
//			.antMatchers(HttpMethod.GET, "/materiais/*").hasRole("USUARIO")	
//			.antMatchers(HttpMethod.PUT, "/materiais/*").hasRole("ADMINISTRADOR")	
//			
			.antMatchers("/console/**").permitAll()
			
			.anyRequest().authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy( SessionCreationPolicy .STATELESS )
			.and().addFilterBefore(new AutenticacaoViaTokenFilter( tokenService, usuarioRepository ), UsernamePasswordAuthenticationFilter.class)
			
			//habilita h2-console
			.headers().frameOptions().disable();
	}
	
	****/
	//Configuracoes de autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()	
		.antMatchers("/user/login").permitAll()
		.antMatchers("/console/**").permitAll()
		
		// A ROLE CADASTRADA NA TABELA PERFIS SEGUE O PADRÃO ROLE_XXXX EX: ROLE_USUARIO, ROLE_TESTE //
		//.antMatchers(HttpMethod.GET, "/materiais").hasRole("USUARIO")	
		
		.antMatchers("/h2-console/**").permitAll()
	    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Habilitar crossOrigin
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy( SessionCreationPolicy .STATELESS )
		.and().addFilterBefore(new AutenticacaoViaTokenFilter( tokenService, usuarioRepository ), UsernamePasswordAuthenticationFilter.class)
		
		//habilita h2-console
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
	
//	//  '$2a$10$B0x28ME9hPO0W/nfDPoKBeJp540RssMw5ciyWMsie4W/JHIKFfreu'
//	// somente para gerar uma chave para senha de teste
//	public static void main(String[] arqs) {
//		System.out.println( new BCryptPasswordEncoder().encode( "123456" ) );
//	}
	
}

