package br.jus.treto.cemu.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movimentacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	//@GeneratedValue( strategy = GenerationType.IDENTITY )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIA_MOVIMENTACAO")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SEQUENCIA_MOVIMENTACAO", sequenceName = "SEQ_MOVIMENTACAO")
	private Long id;
//	private Long guiaId;
	private Long unidadeorigemId;
	private Long unidadedestinoId;
	private LocalDateTime datacriacao;
	private LocalDateTime datarecebimento;
	private String status;
	private String observacao ;
	@OneToMany( mappedBy= "movimentacao", cascade=CascadeType.ALL )
	private List<ItemMovimentacao> itens = new ArrayList<>();
 	
    @OneToOne(fetch = FetchType.EAGER,
    cascade =  CascadeType.ALL,
    mappedBy = "movimentacao")
    @JsonIgnore
    private Guia guia;

//    @OneToOne(mappedBy = "movimentacao", cascade = CascadeType.ALL,
//    fetch = FetchType.EAGER, optional = false)
//    @JsonIgnore
//    private Guia guia;

    
	public void addItem(ItemMovimentacao item) {
    	 itens.add(item);
    	 item.setMovimentacao(this);
    }
    public void removeItem(ItemMovimentacao item) {
    	itens.remove(item);
    	item.setMovimentacao(null);
    }
	public Long getId() {
		return id;
	}
	public List<ItemMovimentacao> getItens() {
		return itens;
	}
	public void setItens(List<ItemMovimentacao> itens) {
		this.itens = itens;
	}
	public void setId(Long id) {
		this.id = id;
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
	public LocalDateTime getDatacriacao() {
		return datacriacao;
	}
	public void setDatacriacao(LocalDateTime datacriacao) {
		this.datacriacao = datacriacao;
	}
	public LocalDateTime getDatarecebimento() {
		return datarecebimento;
	}
	public void setDatarecebimento(LocalDateTime datarecebimento) {
		this.datarecebimento = datarecebimento;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
