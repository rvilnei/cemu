package br.jus.treto.cemu.config.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.jus.treto.cemu.domain.User;
import br.jus.treto.cemu.domain.UserPerfil;
import br.jus.treto.cemu.repository.UsuarioRepository;
import br.jus.treto.treauth.Autenticacao;
import br.jus.treto.treauth.model.Perfil;
import br.jus.treto.treauth.model.Usuario;
//import br.jus.treto.treauth.model.Usuario;

public class CustomAuthenticationProvider implements AuthenticationProvider {


	private UsuarioRepository usuarioRepository;

	public CustomAuthenticationProvider( ) { 	}
	
	public CustomAuthenticationProvider(UsuarioRepository usuarioRepository) {
			this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String nome = authentication.getName();
        String password = authentication.getCredentials().toString();
           
        System.out.println("* authenticate *name**  "+nome+"  **password**  "+password);
      	Usuario usuario = Autenticacao.autenticaERetornaDados( nome ,  password , "SDU", true );
    	List<Perfil> perfis = usuario.getPerfis();
    	
	  	if( usuario.getEmail() != null ) {
	       // User userAuth = new User(usuario);
	  		//Optional<User> user = usuarioRepository.findByEmail( usuario.getEmail()  ) ;
	  		//User userAuth = user.get();
	  		
	  	//	User userAuth = usuarioRepository.findByEmail( usuario.getEmail()  ) ;
	  		Optional<User> user = usuarioRepository.findByEmail( usuario.getEmail()  ) ;
	  		User userAuth ;
	  		if( user.isPresent() ) {
	  			 userAuth = user.get();
	  			System.out.println("usuario encontrado ... ");
	  		} else {
	  			System.out.println("usuario nulo ... ");
	  			userAuth = new User();
	  		}
	  		
	  		userAuth.setEmail(  usuario.getEmail() );
	  		userAuth.setNome( usuario.getNome() );
	  		userAuth.setMatricula(usuario.getId() .toString() );
	  		userAuth.setUnidade( usuario.getUnidade().getSigla() ); 
	  		userAuth.setUnidadeId( usuario.getUnidade().getId() ); 
	  		userAuth.setPerfis(usuario.getPerfis());
	  		userAuth.setRoles(usuario.getPerfis());
	        User usr = usuarioRepository.save(userAuth);  
	        usr.getPerfis().forEach( (   p) -> {  
	    										System.out.println("perfil authorities:  "+ p.getNome()   ) ;
	    							});
	        
	        System.out.println("userAuth Unidade : "+usr.getEmail() +"  nome ; "+usr.getNome()  +"  matricula ; "+usr.getMatricula()  );
	        System.out.println("usuario Unidade : "+usr.getUnidade()+" ID : "+usr.getId() );
	        
	         Authentication auth =new UsernamePasswordAuthenticationToken( usr, password );
	         return auth;
	  	}	
         
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
