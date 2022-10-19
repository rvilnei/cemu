package br.jus.treto.cemu.config.security.jwt;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

//import br.jus.treto.treauth.Autenticacao;
//import br.jus.treto.treauth.model.Unidade;
//import br.jus.treto.treauth.model.Usuario;
import br.jus.treto.cemu.config.security.jwt.TokenService;
import br.jus.treto.cemu.domain.User;
import br.jus.treto.cemu.repository.UsuarioRepository;
import br.jus.treto.cemu.resources.form.LoginForm;

/** Lógica para qdo chegar uma requisiçao com um TOKEN
 recuperar,  validar e autenticar o usuário **/
public  class AutenticacaoViaTokenFilter extends OncePerRequestFilter  {
	
	private TokenService tokenService;
	private UsuarioRepository repository;
	
	
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarRequestToken(request);
		boolean valido = tokenService.istokenValido(token);
		if(valido) {
			autenticaarCliente(token);
		}else { 
			System.out.println("Token invalido ... ");
		}
		filterChain.doFilter(request, response);
	}

	private void autenticaarCliente(String token) {
		    Long idUsuario = tokenService.getIdUsuario(token);
		    User userAuth =  repository.findById(idUsuario).get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userAuth, null, userAuth.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarRequestToken(HttpServletRequest request) {
	String token = request.getHeader("Authorization") ;		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}		
	return token.substring(7, token.length());
	}

}
