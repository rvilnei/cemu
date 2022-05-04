package br.jus.treto.cemu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Material {
	
	@JsonInclude(Include.NON_NULL)  // Só retrono no json se não for nulo
	@Id
	//@GeneratedValue( strategy = GenerationType.IDENTITY )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIA_MATERIAL")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SEQUENCIA_MATERIAL", sequenceName = "SEQ_MATERIAL")
	private Long id;		
	@NotNull @NotEmpty  @Length( min = 2 )
	private String nome;
	private String descricao;
	private Long tipoId;
	private String codigobarras;
	private Long categoria;
	private String modelo; 
	//@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
	@ManyToOne(optional = true,  cascade=CascadeType.DETACH)
	@JoinColumn(name="STATUS_ID", referencedColumnName="ID", nullable=true)
	private Status status;
	private Boolean temDevolucao;
	@Transient
	private Boolean temCodigobarras;

	@OneToMany( mappedBy= "material", cascade=CascadeType.DETACH)
	private List<Lancamento> lancamentos;
	
	@OneToMany( mappedBy = "material" , cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
	@JsonIgnore  
  	private  List<ItemMovimentacao> itens = new ArrayList<>()	;
	
	 public Material() {}
	
	public List<ItemMovimentacao> getItens() {
		return itens;
	}

	public void setItens(List<ItemMovimentacao> itens) {
		this.itens = itens;
	}

	public Long getTipoId() {
		return tipoId;
	}

	public void setTipo(Long tipo) {
		this.tipoId = tipo;
	}

	public String getCodigobarras() {
		return codigobarras;
	}

	public void setCodbarra(String codigobarras) {
		this.codigobarras = codigobarras;
	}

	public Long getCategoria() {
		return categoria;
	}

	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatus(Long status) {
		this.statusId = status;
	}

	public Boolean getTemDevolucao() {
		return temDevolucao;
	}

	public void setTemDevolucao(Boolean temDevolucao) {
		this.temDevolucao = temDevolucao;
	}

	public Boolean getTemCodigobarras() {
		if( this.codigobarras == null || this.codigobarras == "" ) {
			this.temCodigobarras = false;
		}else { 
			this.temCodigobarras = true;
		}
		return this.temCodigobarras;
	}

	public void setTem_rfid(Boolean temCodigobarras) {
		this.temCodigobarras = temCodigobarras;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	public Material(String nome, String descricao ) {
		this.nome = nome;
		this.descricao  = descricao;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
