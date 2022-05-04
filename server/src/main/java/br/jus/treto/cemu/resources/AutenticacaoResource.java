package br.jus.treto.cemu.resources;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.treto.cemu.config.security.jwt.TokenService;
import br.jus.treto.cemu.resources.dto.TokenDto;
import br.jus.treto.cemu.resources.form.LoginForm;

@CrossOrigin
//@CrossOrigin(origins = "http://10.27.104.82/:8080")
@RestController
@RequestMapping( "user/login" )
//@Profile("prod")
public class AutenticacaoResource {
	
	@Autowired
	private AuthenticationManager autManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody  @Valid LoginForm	 form  ){
			System.out.println(  " ** "   +form.getEmail() );
			UsernamePasswordAuthenticationToken dadosLogin = form.converter();

			try {
					// chama CustomAuthenticationProvider.authenticate(Authentication authentication) () //
					Authentication authentication = autManager.authenticate(dadosLogin);
					String token = tokenService.gerarToken( authentication );
					return ResponseEntity.ok( new TokenDto( token, "Bearer" ));
				} catch(AuthenticationException e) {
						System.out.println("** AuthenticationException ... nao autenticado " );
						return ResponseEntity.badRequest().build();
				}
	}

}
