package br.jus.treto.cemu.resources.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.jus.treto.cemu.domain.ItemMovimentacao;
import br.jus.treto.cemu.domain.Lancamento;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.domain.Movimentacao;
import br.jus.treto.cemu.domain.Status;
import br.jus.treto.cemu.domain.Tipo;
import br.jus.treto.cemu.services.MateriaisService;


public class MaterialDto {
	
	private Long id;				
	private String nome;
	private String descricao;
	private Long tipoId;
	private String tipoDescricao;
	private String codigobarras;
	private Long categoria;
	private String modelo;
	private Long statusId;
	private String statusDescricao;
	private Boolean temDevolucao;
	private Boolean temCodigobarras;

	private List<LancamentoDto> lancamentos;
	
  	private  List<ItemMovimentacao> itens = new ArrayList<>()	;
	private static MateriaisService materiaisService;
	
	public MaterialDto() {}

//	public MaterialDto(Material material){		
//		 	this.id = material.getId() ;
//			this.nome = material.getNome() ;
//			this.descricao = material.getDescricao() ;
//			this.tipo  = material.getTipo();
//			this.codigobarras  = material.getCodigobarras();
//			this.categoria  = material.getCategoria();
//			this.modelo  = material.getModelo();
//			this.status = material.getStatus();
//			this.temDevolucao = material.getTemDevolucao();
//			this.temCodigobarras = material.getTemCodigobarras();
//			this.lancamentos = LancamentoDto.converter( material.getLancamentos() );	
//			//this.itens = material.getItens();
//		 
//	}
	
	public MaterialDto(Material material){		
	 	this.id = material.getId() ;
		this.nome = material.getNome() ;
		this.descricao = material.getDescricao() ;
		this.tipoId  = material.getTipoId();
		//this.tipoDescricao  = material.getTipo().getNome();
		if ( material.getTipoId() != null )
			this.tipoDescricao  = materiaisService.buscarTipo(  material.getTipoId() ).getNome();
		this.codigobarras  = material.getCodigobarras();
		this.categoria  = material.getCategoria();
		this.modelo  = material.getModelo();
		this.statusId= material.getStatusId();
		if ( material.getStatusId() != null )
		this.statusDescricao  = materiaisService.buscarStatus(  material.getStatusId() ).getNome();
		this.temDevolucao = material.getTemDevolucao();
		this.temCodigobarras = material.getTemCodigobarras();
		this.lancamentos = LancamentoDto.converter( material.getLancamentos() );	
		//this.itens = material.getItens();	 
}
	
	public static MaterialDto transformaEmDTO(Material material) {
	    return new MaterialDto( material );
	}
	public static List<MaterialDto> converter(List<Material> materiaais) {
		return materiaais.stream().map(MaterialDto::new).collect( Collectors.toList() ) ;
	}
	
	public static List<MaterialDto> converter(List<Material> materiaais, MateriaisService service ) {
		materiaisService = service;
		return materiaais.stream().map(MaterialDto::new).collect( Collectors.toList() ) ;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getTipoId() {
		return tipoId;
	}

	public String getCodigobarras() {
		return codigobarras;
	}

	public Long getCategoria() {
		return categoria;
	}

	public String getModelo() {
		return modelo;
	}
	
	public String getTipoDescricao() {
		return tipoDescricao;
	}
	public Long getStatusId() {
		return statusId;
	}
	public String getStatusDescricao() {
		return statusDescricao;
	}
	public Boolean getTemDevolucao() {
		return temDevolucao;
	}

	public Boolean getTemCodigobarras() {
		return temCodigobarras;
	}

	public List<LancamentoDto> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<LancamentoDto> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	
	public List<ItemMovimentacao> getItens() {
		return itens;
	}

	public void setItens(List<ItemMovimentacao> itens) {
		this.itens = itens;
	}

	public void setStatusId(Long status) {
		this.statusId = status;
	}

}
