package br.jus.treto.cemu.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.jus.treto.cemu.resources.dto.AutorizadorUnidade;

//import br.jus.treto.treauth.model.Perfil;
//import br.jus.treto.treauth.model.Unidade;
//import br.jus.treto.treauth.model.Usuario;

@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String matricula;
	private String nome;
	private String email;
	private String senha;
	private String unidade;
	private String unidadesigla;
	private Long unidadeId;
	private ArrayList<String> roles ;
	
	//@ManyToMany(fetch = FetchType.EAGER)
	@OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<UserPerfil> perfis = new ArrayList<UserPerfil>();

	public User() {	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<UserPerfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Collection<? extends UserPerfil> perfis) {
		//this.perfis = (List<UserPerfil>) perfis;
		this.perfis.addAll(perfis);
	}

	public long getUnidadeId() {
		return unidadeId;
	}

	public void setUnidadeId( Long unidadeId) {
		this.unidadeId = unidadeId;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public List<String> getRoles() {
		if( roles == null ) roles = new ArrayList<String>();
		return roles;
	}
/**
	public void setRoles(List<Perfil> roles) {
		this.getRoles().clear();
		roles.forEach( (p) -> {   
			this.getRoles().add( p.getNome() );
		});
	}
**/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUnidade() {
		return unidade;
	}


	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getUnidadesigla() {
		return unidadesigla;
	}

	public void setUnidadesigla(String unidadesigla) {
		this.unidadesigla = unidadesigla;
	}
	
	
	
/**
	public void setPerfis(List<Perfil> perfis) {
		perfis.forEach( (p) -> {   
			this.getPerfis().add( new UserPerfil( p.getNome())  );
			this.getRoles().add( p.getNome() );
		});
	}
	**/
	

	public void setPerfis(List<String> perfis) {
		perfis.forEach( (p) -> {   
			this.getPerfis().add( new UserPerfil( p)  );
			this.getRoles().add( p );
		});
	}

	
	
	
}
