package br.jus.treto.cemu.domain;


import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lancamento {
	
	@Id
	//@GeneratedValue( strategy = GenerationType.IDENTITY )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIA_LANCAMENTO")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SEQUENCIA_LANCAMENTO", sequenceName = "SEQ_LANCAMENTO")
	private Long id;  
	private LocalDate data;  
	private Integer quantidade ;   
	private String matricula ;  
	private Long unidadeorigemId;
	private Long unidadedestinoId;
	private String tipo;

	//private Long idMovimentacao;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID")
	@JsonIgnore // para evitar loop na criacao do JSON
	private Material material;
	
public Long getId() {
	return id;
}
   
public void setId(Long id) {
	this.id = id;
}
public LocalDate getData() {
	return data;
}
public void setData(LocalDate data) {
	this.data = data;
}
public Integer getQuantidade() {
	return quantidade;
}
public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
}
public String getMatricula() {
	return matricula;
}
public void setMatricula(String matricula) {
	this.matricula = matricula;
}


public Material getMaterial() {
	return material;
}

public void setMaterial(Material material) {
	this.material = material;
}

public Long getUnidadeorigemId() {
	return unidadeorigemId;
}

public void setUnidadeorigemId(Long unidadeorigemId) {
	this.unidadeorigemId = unidadeorigemId;
}

public Long getUnidadedestinoId() {
	return unidadedestinoId;
}

public void setUnidadedestinoId(Long unidadedestinoId) {
	this.unidadedestinoId = unidadedestinoId;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

}