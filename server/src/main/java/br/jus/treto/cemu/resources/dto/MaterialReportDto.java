package br.jus.treto.cemu.resources.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.services.MateriaisService;

public class MaterialReportDto {
	
	private static MateriaisService materiaisService;
	
	private Long id;
	private Long statusId;
	private String statusNome;
	private Long tipoId;
	private String  tipoNome;
	private String categoria;
	private String codigobarras;
	private String descricao;
	private String modelo;
	private String nome;
	private String temDevolucao;
	
	public MaterialReportDto() {}
	
	public MaterialReportDto(Material material) {
		this.id =material.getId();
		this.nome = material.getNome();
		this.statusId = material.getStatus().getId();
		if( this.statusId  != null ) 
			this.statusNome =  materiaisService.buscarStatus(this.statusId).getNome();
		this.tipoId = material.getTipo().getId();
		if(	this.tipoId  != null ) 
			this.tipoNome =  materiaisService.buscarTipo(this.tipoId).getNome();
	//	this.categoria = material.getCategoria();
		this.codigobarras = material.getCodigobarras();
		this.descricao = material.getDescricao();
		this.modelo = material.getModelo();
		this.temDevolucao = ( material.getTemDevolucao() == true ? "SIM" :"NÃO" );
	}

	public static MaterialReportDto  transformaEmDTO( Material material ) {
		return new MaterialReportDto( material );
	}

	public static List<MaterialReportDto> converter(List<Material> material) {
		return material.stream().map(MaterialReportDto::new).collect( Collectors.toList() ) ;
	}
	
	public static List<MaterialReportDto> converter(List<Material> materiais,MateriaisService service ) {
		materiaisService = service;
		return materiais.stream().map(MaterialReportDto::new).collect( Collectors.toList() ) ;
	}

	public static MateriaisService getMateriaisService() {
		return materiaisService;
	}

	public Long getId() {
		return id;
	}

	public Long getStatusId() {
		return statusId;
	}

	public String getStatusNome() {
		return statusNome;
	}

	public Long getTipoId() {
		return tipoId;
	}

	public String getTipoNome() {
		return tipoNome;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getCodigobarras() {
		return codigobarras;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getModelo() {
		return modelo;
	}

	public String getNome() {
		return nome;
	}

	public String getTemDevolucao() {
		return temDevolucao;
	}
	
}
