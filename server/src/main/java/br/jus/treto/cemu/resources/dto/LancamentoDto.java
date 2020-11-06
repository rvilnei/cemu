package br.jus.treto.cemu.resources.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import br.jus.treto.cemu.domain.Lancamento;
import br.jus.treto.cemu.domain.Movimentacao;
import br.jus.treto.cemu.services.MateriaisService;

public class LancamentoDto {
	
	@Autowired
	private MateriaisService materiaisService;
	
	private Long id;  
	private LocalDate data;  
	private Integer quantidade ;   
	private String matricula ;  
	private Long unidadeorigemId;
	private Long unidadedestinoId;
	private String tipo;

	private String unidadeOrigem;
	private String unidadeDestino;
	
	public LancamentoDto() {}

	public LancamentoDto(Lancamento lancamento){		
		 this.id = lancamento.getId() ;
		 
		 this.data= lancamento.getData();  
		 this.quantidade = lancamento.getQuantidade();   
		 this.matricula = lancamento.getMatricula();  
		 this.unidadeorigemId= lancamento.getUnidadeorigemId();
		 this.unidadedestinoId= lancamento.getUnidadedestinoId();
		 this.tipo= lancamento.getTipo();
		 this.unidadeorigemId = lancamento.getUnidadeorigemId() ;
		 this.unidadedestinoId = lancamento.getUnidadedestinoId() ;
		// this.unidadeOrigem = materiaisService.getUnidade(this.unidadeorigemId ).getSigla() ;
		//  this.unidadeDestino = materiaisService.getUnidade( this.unidadedestinoId  ).getSigla();
		 this.tipo = lancamento.getTipo();
		 
	}
	
	public static List<LancamentoDto> converter(List<Lancamento> lancamentos) {
		// usando a api de stream do java 8
		//lancamentos.forEach(l ->  System.out.println(" **Lancamento converter:  "+ l.getQuantidade()  ) );
		return lancamentos.stream().map(LancamentoDto::new).collect( Collectors.toList() ) ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Long getUnidadeorigemId() {
		return unidadeorigemId;
	}

	public void setUnidadeorigemId(Long unidadeorigemId) {
		this.unidadeorigemId = unidadeorigemId;
	}

	public Long getUnidadedestinoId() {
		return unidadedestinoId;
	}

	public void setUnidadedestinoId(Long unidadedestinoId) {
		this.unidadedestinoId = unidadedestinoId;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	
	
	

}
