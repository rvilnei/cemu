package br.jus.treto.cemu.resources.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.jus.treto.cemu.domain.Guia;
import br.jus.treto.cemu.domain.ItemMovimentacao;
import br.jus.treto.cemu.domain.Movimentacao;

public class MovimentacaoDto {

	private Long id;
//	private Long guiaId;
	private Long unidadeorigemId;
	private Long unidadedestinoId;
	private LocalDateTime datacriacao;
	private LocalDateTime datarecebimento;
	private String status;
	private String observacao ;
	private List<ItemMovimentacao> itens = new ArrayList<>();
	private Guia guia;
	private String unidadeOrigem;
	private String unidadeDestino;
	
	public MovimentacaoDto() {}

	public MovimentacaoDto(Movimentacao movimentacao){		
		 this.id = movimentacao.getId() ;
//		 this.guiaId = movimentacao.getGuiaId() ;
		 this.unidadeorigemId = movimentacao.getUnidadeorigemId() ;
		 this.unidadedestinoId = movimentacao.getUnidadedestinoId() ;
		 this.datacriacao = movimentacao.getDatacriacao() ;
		 this.datarecebimento = movimentacao.getDatarecebimento() ;;
		 this.status = movimentacao.getStatus();
		 this.observacao = movimentacao.getObservacao() ;
		 this.itens = movimentacao.getItens() ;
		 this.guia = movimentacao.getGuia();
		 
	}
	
	public static List<MovimentacaoDto> converter(List<Movimentacao> movimentacoes) {
		// usando a api de stream do java 8
		return movimentacoes.stream().map(MovimentacaoDto::new).collect( Collectors.toList() ) ;
	}
	
	
	
	public String getUnidadeOrigem() {
		return unidadeOrigem;
	}

	public void setUnidadeOrigem(String unidadeOrigem) {
		this.unidadeOrigem = unidadeOrigem;
	}

	public String getUnidadeDestino() {
		return unidadeDestino;
	}

	public void setUnidadeDestino(String unidadeDestino) {
		this.unidadeDestino = unidadeDestino;
	}

	public Long getId() {
		return id;
	}
	public Long getUnidadeorigemId() {
		return unidadeorigemId;
	}
	public Long getUnidadedestinoId() {
		return unidadedestinoId;
	}
	public LocalDateTime getDatacriacao() {
		return datacriacao;
	}
	public LocalDateTime getDatarecebimento() {
		return datarecebimento;
	}
	public String getObservacao() {
		return observacao;
	}
	public List<ItemMovimentacao> getItens() {
		return itens;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Guia getGuia() {
		return guia;
	}

	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	
}
