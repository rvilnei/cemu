package br.jus.treto.cemu.resources.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import br.jus.treto.cemu.domain.Guia;
import br.jus.treto.cemu.domain.ItemMovimentacao;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.domain.Movimentacao;
import br.jus.treto.cemu.services.GuiasService;
import br.jus.treto.cemu.services.MateriaisService;
import br.jus.treto.cemu.services.ReportsService;

public class GuiaReportDto {

	private static GuiasService guiasService;;
	
	private Long id;
	private String numeroGuia;
	private String placaVeiculo;
	private String nomeCondutor;
	private String rgCondutor;
	private String observacao;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataRecebimento;
	private String matriculaRecebiemnto;
	private Long transportadoraId;
	private Movimentacao movimentacao;
	private List<ItemMovimentacao> itensMovimentacao;
		

	private Long movimentacaoId;
	private Long unidadeorigemId;
	private Long unidadedestinoId;
	private String unidadeorigem;
	private String dsUnidadeorigem;
	private String unidadedestino;
	private String dsUnidadedestino;
	private LocalDateTime datacriacaoMovimentacao;
	private String statusMovimentacao;
	
 //	private  List<ItemMovimentacao> itensMovimentacao = new ArrayList<ItemMovimentacao>()	;
	
	public GuiaReportDto() {}
	
	public GuiaReportDto( Guia guia ) {
		
		this. id = guia.getId();
		this. numeroGuia = guia.getNumeroGuia() ;
		this. placaVeiculo = guia.getPlacaVeiculo() ;
		this.nomeCondutor = guia.getNomeCondutor() ;
		this. rgCondutor = guia.getRgCondutor() ;
		this.observacao = guia.getObservacao() ;
		this.dataCriacao = guia.getDataCriacao() ;
		this.dataRecebimento = guia.getDataRecebimento() ;
		this.matriculaRecebiemnto = guia.getMatriculaRecebiemnto() ;
		this.transportadoraId = guia.getTransportadoraId() ;
		this. movimentacao =  guia.getMovimentacao() ;
		
		this.movimentacaoId = guia.getMovimentacao().getId() ;
		this.unidadeorigemId = guia.getMovimentacao().getUnidadedestinoId() ;
		this.unidadedestinoId = guia.getMovimentacao().getUnidadeorigemId();
		if ( this.unidadeorigemId  != null ) {
			this.unidadeorigem  = guiasService.getUnidade(unidadeorigemId).getSigla();
			this.dsUnidadeorigem  = guiasService.getUnidade(unidadeorigemId).getDescricao() ;
		}
		if ( this.unidadedestinoId  != null ) {
			this.unidadedestino  = guiasService.getUnidade(unidadedestinoId).getSigla();
		   this.dsUnidadedestino  = guiasService.getUnidade(unidadeorigemId).getDescricao();
		}
		this.statusMovimentacao = guia.getMovimentacao().getStatus();
		this.datacriacaoMovimentacao = guia.getMovimentacao().getDatacriacao();
		this.itensMovimentacao = guia.getMovimentacao().getItens();
		
	}
	
	public LocalDateTime getDatacriacaoMovimentacao() {
		return datacriacaoMovimentacao;
	}

	public String getStatusMovimentacao() {
		return statusMovimentacao;
	}

	public List<ItemMovimentacao> getItensMovimentacao() {
		return this.itensMovimentacao;
	}

	
	public Long getMovimentacaoId() {
		return movimentacaoId;
	}

	public Long getUnidadeorigemId() {
		return unidadeorigemId;
	}

	public Long getUnidadedestinoId() {
		return unidadedestinoId;
	}
	
	public String getUnidadeorigem() {
		return this.unidadeorigem;
	}
	
	public void setUnidadeorigem( String origem ) {
		this.unidadeorigem = origem;
	}
	
	public String getDsUnidadeorigem() {
		return dsUnidadeorigem;
	}

	public String getDsUnidadedestino() {
		return dsUnidadedestino;
	}

	public String getUnidadedestino() {
		return this.unidadedestino;
	}
	
	public void setUnidadedestino( String destino ) {
		this.unidadedestino = destino;
	}
	
	public static GuiaReportDto  transformaEmDTO( Guia guia ) {
		return new GuiaReportDto( guia );
	}

	public static List<GuiaReportDto> converter(List<Guia> guias) {
		return guias.stream().map(GuiaReportDto::new).collect( Collectors.toList() ) ;
	}
	
	public static List<GuiaReportDto> converter(List<Guia> guias, GuiasService service ) {
		guiasService = service;
		return guias.stream().map(GuiaReportDto::new).collect( Collectors.toList() ) ;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroGuia() {
		return numeroGuia;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public String getNomeCondutor() {
		return nomeCondutor;
	}

	public String getRgCondutor() {
		return rgCondutor;
	}

	public String getObservacao() {
		return observacao;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public LocalDateTime getDataRecebimento() {
		return dataRecebimento;
	}

	public String getMatriculaRecebiemnto() {
		return matriculaRecebiemnto;
	}

	public Long getTransportadoraId() {
		return transportadoraId;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}
	 
}