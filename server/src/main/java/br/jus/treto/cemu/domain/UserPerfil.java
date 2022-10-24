package br.jus.treto.cemu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class UserPerfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	@Id 
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIA_USERPERFIL")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SEQUENCIA_USERPERFIL", sequenceName = "SEQ_USERPERFIL")
	private Long id;
	private String nome;
	
//	@ManyToOne(fetch=FetchType.EAGER)   
//	public User usuario;
	
	public UserPerfil() {	}
	
	public UserPerfil(String nome ) {
		this.nome = nome;
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

	@Override
	public String getAuthority() {
		return nome;
	}
	
}
