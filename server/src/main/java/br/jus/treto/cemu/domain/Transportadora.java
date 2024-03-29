package br.jus.treto.cemu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity 
public class Transportadora {
	
	@Id
	//@GeneratedValue( strategy = GenerationType.IDENTITY )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIA_TRANSPORTADORA")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SEQUENCIA_TRANSPORTADORA", sequenceName = "SEQ_TRANSPORTADORA")
	private Long id;
	
	private String nome;
	
	private String cgc;
	
	private String telefone;
	
	private String endereco;

	public Transportadora() {
		
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

	public String getCgc() {
		return cgc;
	}

	public void setCgc(String cgc) {
		this.cgc = cgc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
		
}
