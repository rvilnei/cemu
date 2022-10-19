package br.jus.treto.cemu.config.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.RestTemplate;

import br.jus.treto.cemu.config.security.autorizador.AutenticacaoService;
import br.jus.treto.cemu.domain.Unidade;
import br.jus.treto.cemu.domain.User;
import br.jus.treto.cemu.domain.UserPerfil;
import br.jus.treto.cemu.repository.UsuarioRepository;
import br.jus.treto.cemu.resources.dto.AutorizadorUnidade;
import br.jus.treto.cemu.resources.dto.AutorizadorUsuario;
//import br.jus.treto.treauth.Autenticacao;
//import br.jus.treto.treauth.model.Perfil;
//import br.jus.treto.treauth.model.Usuario;
//import br.jus.treto.treauth.model.Usuario;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private AutenticacaoService autenticacaoService;	
	private UsuarioRepository usuarioRepository;
	
	public CustomAuthenticationProvider( ) { 	}
	
	public CustomAuthenticationProvider(UsuarioRepository usuarioRepository, AutenticacaoService autenticacaoService) {
			this.usuarioRepository = usuarioRepository;
			this.autenticacaoService = autenticacaoService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String nome = authentication.getName();
        String password = authentication.getCredentials().toString();
        //Autenticar usuario
        AutorizadorUsuario usuario = this.autenticacaoService.autenticar(nome, password); 
	  	if( usuario.getEmail() != null ) {
	  		Optional<User> user = usuarioRepository.findByEmail( usuario.getEmail()  ) ;
	  		User userAuth ;
	  		if( user.isPresent() ) {
	  			 userAuth = user.get();
	  			System.out.println("usuario encontrado ... ");
	  		} else {
	  			System.out.println("usuario nulo ... ");
	  			userAuth = new User();
	  		}
	  		
	  		userAuth = usuarioAutorizadoToUser(userAuth ,usuario);
	        User usr = usuarioRepository.save(userAuth);  
	        usr.getPerfis().forEach( (   p) -> {  
	    										System.out.println("perfil authorities:  "+ p.getNome()   ) ;
	    							});

	        System.out.println("userAuth email : "+usr.getEmail() +"  nome ; "+usr.getNome()  +"  matricula ; "+usr.getMatricula()  );
	        System.out.println("usuario Unidade : "+usr.getUnidadesigla()+" - "+usr.getUnidade()+" ID : "+usr.getId() );
	  
	        Authentication auth =new UsernamePasswordAuthenticationToken( usr, password );
	        return auth;
	  	}	 
	 return null;
	}

	private User usuarioAutorizadoToUser(User userAuth, AutorizadorUsuario usuario) {
		
  		userAuth.setEmail(  usuario.getEmail() );
  		userAuth.setNome( usuario.getNome() );
  		userAuth.setMatricula(usuario.getMatricula() );
  		userAuth.setUnidade( usuario.getLotacao().getNome() ); 
  		userAuth.setUnidadesigla( usuario.getLotacao().getSigla() ); 
  	//	userAuth.setUnidadeId( usuario.getUnidade().getId() ); 
  		Unidade unidade = autenticacaoService.findUnidadeIdBySigla(usuario.getLotacao().getSigla());
  		System.out.println(" unidadeId **** "+unidade.getId());
  		userAuth.setUnidadeId(unidade.getId());
  		userAuth.setPerfis(usuario.getPerfis());
  	
		return userAuth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
