package br.jus.treto.cemu.resources.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import br.jus.treto.cemu.domain.Estoque;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.services.EstoqueService;
import br.jus.treto.cemu.services.MateriaisService;

public class EstoqueReportDto {

	private static EstoqueService estoquesService;;
	private static MateriaisService materiaisService;
	
	private Long materialId;
	private String materialNome;
	private String materialCodigobarras;
	private Long unidadeId;
	private String unidadeNome;
	private String unidadeSigla;
	private Integer quantidade;
	private LocalDateTime data;
	private LocalDateTime dataAlteracao;
	
	public EstoqueReportDto() {}
	
	public EstoqueReportDto( Estoque estoque) {
		this.materialId = estoque.getMaterialId();
		if( materialId != null ) {
			this.materialNome =  materiaisService.buscar( estoque.getMaterialId()  ).getNome();
			this.materialCodigobarras =  materiaisService.buscar( estoque.getMaterialId()  ).getCodigobarras();
		}
		this.unidadeId = estoque.getUnidadeId();
		if( unidadeId != null )
			this.unidadeNome =  materiaisService.getUnidade(unidadeId).getDescricao();
			this.unidadeSigla =  materiaisService.getUnidade(unidadeId).getSigla();
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
	
	public static List<EstoqueReportDto> converter(List<Estoque> estoques,MateriaisService service ) {
		materiaisService = service;
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

	public String getMaterialCodigobarras() {
		return materialCodigobarras;
	}

	public Long getUnidadeId() {
		return unidadeId;
	}

	public String getUnidadeNome() {
		return unidadeNome;
	}

	public String getUnidadeSigla() {
		return unidadeSigla;
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
