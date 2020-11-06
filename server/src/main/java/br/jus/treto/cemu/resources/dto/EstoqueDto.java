package br.jus.treto.cemu.resources.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.jus.treto.cemu.domain.Estoque;


public class EstoqueDto {
	
	private Long materialId;
	private String materialNome;
	private String unidadeSigla;
	private Long unidadeId;
	private Integer quantidade;
	private LocalDateTime data;
	private LocalDateTime dataAlteracao;
	private String codigoBarras;

	public EstoqueDto( ){}
	
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras( String codigoBarras ) {
		this.codigoBarras = codigoBarras;
	}

	
	public EstoqueDto(Estoque estoque){		
		this.materialId = estoque.getMaterialId();
		this.unidadeId = estoque.getUnidadeId();
		this.quantidade = estoque.getQuantidade();
		this.data = estoque.getData();
		this.dataAlteracao = estoque.getDataAlteracao();
	}
	
	public Long getMaterialId() {
		return materialId;
	}
	public Long getUnidadeId() {
		return unidadeId;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public LocalDateTime getData() {
		return data;
	}
	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public static List<EstoqueDto> converter(List<Estoque> estoques) {
		// usando a api de stream do java 8
		return estoques.stream().map(EstoqueDto::new).collect( Collectors.toList() ) ;
	}

	public String getUnidadeSigla() {
		return unidadeSigla;
	}

	public void setUnidadeSigla(String unidadeSigla) {
		this.unidadeSigla = unidadeSigla;
	}

	public String getMaterialNome() {
		return materialNome;
	}

	public void setMaterialNome( String nome ) {
		this.materialNome = nome;
	}
	
}
