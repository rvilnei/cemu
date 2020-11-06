package br.jus.treto.cemu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Unidade {
	
	@Id
	@Column(name="CD")
	private Long id;
	private String sigla;
	private String descricao;
	
//    table name: 'UNIDADE_TSE', schema:  'SRH2', insertable: false, updateable: false
//    id column:'CD'
//    sigla column: 'SIGLA_UNID_TSE'
//    descricao column: 'DS'
//    situacao column: 'SIT_UNID'
//    fimVigencia column: 'DT_FIM_VIGENCIA'
	
	
	public  Unidade(){}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
