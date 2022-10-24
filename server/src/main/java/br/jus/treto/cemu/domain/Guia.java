package br.jus.treto.cemu.domain;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
 
@Entity
public class Guia {
	 
	@Id
//	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIA_GUIA")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SEQUENCIA_GUIA", sequenceName = "SEQ_GUIA")
	private Long id;

	private String numeroGuia;

	private String placaVeiculo;

	private String nomeCondutor;

	private String rgCondutor;

	private String observacao;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataCriacao;
	 
	private LocalDateTime dataRecebimento;
	
	private String matriculaRecebiemnto;
	 
	private Long transportadoraId;

	@OneToOne
	@NotNull
	@JoinColumn(name="MOVIMENTACAO_ID")
	private Movimentacao movimentacao;

	public Guia() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public String getNomeCondutor() {
		return nomeCondutor;
	}

	public void setNomeCondutor(String nomeCondutor) {
		this.nomeCondutor = nomeCondutor;
	}

	public String getRgCondutor() {
		return rgCondutor;
	}

	public void setRgCondutor(String rgCondutor) {
		this.rgCondutor = rgCondutor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(LocalDateTime dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public String getMatriculaRecebiemnto() {
		return matriculaRecebiemnto;
	}

	public void setMatriculaRecebiemnto(String matriculaRecebiemnto) {
		this.matriculaRecebiemnto = matriculaRecebiemnto;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Long getTransportadoraId() {
		return transportadoraId;
	}

	public void setTransportadoraId(Long transportadoraId) {
		this.transportadoraId = transportadoraId;
	}
	
}
