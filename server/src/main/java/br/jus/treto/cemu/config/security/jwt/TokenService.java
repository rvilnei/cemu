package br.jus.treto.cemu.config.security.jwt;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.jus.treto.cemu.domain.LoginForm;
import br.jus.treto.cemu.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class TokenService {

	@Value("${session.expeiration}")
	private String expiration ;
	
	@Value("${session.signature.secret}")
	private String secret ;
	
	public String gerarToken(Authentication authentication) {
		User userLogado = (User)  authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration)); 
		return Jwts.builder()
				.setIssuer("API de estoque de materiais - TRE-TO")
				.setSubject( userLogado.getId().toString() )
				.claim("name", userLogado.getNome())
				.claim("email", userLogado.getUsername())
				.claim("unidade", userLogado.getUnidade())
				.claim("unidadeId", userLogado.getUnidadeId())
				.claim("matricula", userLogado.getMatricula())
				//.claim("perfis", userLogado.getPerfis() )
				.claim("roles", userLogado.getRoles() )
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean istokenValido( String token ) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch(Exception e) {
			return false;
		}
		
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject()) ;
	}
	
}
