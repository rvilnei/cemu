package br.jus.treto.cemu.resources.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import br.jus.treto.cemu.domain.Estoque;
import br.jus.treto.cemu.services.EstoqueService;

public class EstoqueReportDto {

	private static EstoqueService estoquesService;;

	private Long materialId;
	private String materialNome;
	private Long unidadeId;
	private String unidadeNome;
	private Integer quantidade;
	private LocalDateTime data;
	private LocalDateTime dataAlteracao;
	
	public EstoqueReportDto() {}
	
	public EstoqueReportDto( Estoque estoque) {
		this.materialId = estoque.getMaterialId();
		this.materialNome = "" ;
		this.unidadeId = estoque.getUnidadeId();
		this.unidadeNome = "";
		this.quantidade = estoque.getQuantidade();
		this.data = estoque.getData();
		this.dataAlteracao = estoque.getDataAlteracao();
		
	}
	
	
	public static EstoqueReportDto  transformaEmDTO( Estoque estoque ) {
		return new EstoqueReportDto( estoque );
	}

	public static List<EstoqueReportDto> converter(List<Estoque> estoque) {
		return estoque.stream().map(EstoqueReportDto::new).collect( Collectors.toList() ) ;
	}
	
	public static List<EstoqueReportDto> converter(List<Estoque> estoques, EstoqueService service ) {
		estoquesService = service;
		return estoques.stream().map(EstoqueReportDto::new).collect( Collectors.toList() ) ;
	}

	public static EstoqueService getEstoquesService() {
		return estoquesService;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public String getMaterialNome() {
		return materialNome;
	}

	public Long getUnidadeId() {
		return unidadeId;
	}

	public String getUnidadeNome() {
		return unidadeNome;
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

}
