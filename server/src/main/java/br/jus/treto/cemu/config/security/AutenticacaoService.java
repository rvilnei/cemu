package br.jus.treto.cemu.config.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.jus.treto.cemu.domain.User;
import br.jus.treto.cemu.repository.UsuarioRepository;
import br.jus.treto.treauth.Autenticacao;
import br.jus.treto.treauth.model.Perfil;
import br.jus.treto.treauth.model.Usuario;

@Service
public class AutenticacaoService implements UserDetailsService       {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<User> user = usuarioRepository.findByEmail(username);
//		System.out.println( "****AutenticacaoService* usuario.isPresent **  "+user.isPresent()  );
//		if (user.isPresent()) {
//			return user.get();
//		}
				
//		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	//	throw new UsernameNotFoundException("Dados inválidos!");//
	//}

}
