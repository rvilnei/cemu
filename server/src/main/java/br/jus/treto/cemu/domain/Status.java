package br.jus.treto.cemu.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Status {
	
	@Id
	//@GeneratedValue( strategy = GenerationType.IDENTITY )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIA_STATUS")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SEQUENCIA_STATUS", sequenceName = "SEQ_STATUS")
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

}
