package br.jus.treto.cemu.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;


@Entity
@IdClass(EstoqueId.class)
public class Estoque implements Serializable  {

	private static final long serialVersionUID = 59378570590427538L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIA_ESTOQUE")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SEQUENCIA_ESTOQUE", sequenceName = "SEQ_ESTOQUE")
	private Long materialId;
	@Id
	private Long unidadeId;
	
	private Integer quantidade;
	private LocalDateTime data;
	private LocalDateTime dataAlteracao;
/**	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumns({
		@JoinColumn( name = "MATERIAL_ID" ),
		@JoinColumn( name = "UNIDADE" )
	})
	@JsonIgnore // para evitar loop na criacao do JSON
	private Material material;
**/
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	public Long getUnidadeId() {
		return unidadeId;
	}
	public void setUnidadeId(Long unidadeId) {
		this.unidadeId = unidadeId;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
