package br.jus.treto.cemu.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.SequenceGenerator;

import org.hibernate.engine.profile.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.OneToOne;

@Entity
public class Pendencia implements Serializable {
	private final long serialVersionUID = 1L;
	
	public enum PendenciaType {
		//material foi perdido/desapareceu no local
		EXTRAVIO("Extravio"),
		//material foi perdido/desapareceu durante o transporte
		EXTRAVIO_TRANSPORTE("Extravio durante Transporte"),
		//material foi separado para consumo/utilizacao local
		CONSUMO_LOCAL("Consumo Local"),
		RECUPERADO("Recuperado"),
		DESCARTADO("Descartado"),
		REAPARECIMENTO("Reaparecimento");
		
		private String tipo;

		private PendenciaType(String tipo) {
			this.tipo = tipo;
		}

		public String geTipo() {
			return tipo;
		}

	}

	/** **/
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQQUENCIA_PENDENCIA")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SEQQUENCIA_PENDENCIA", sequenceName = "SEQ_PENDENCIA")
	private Long id;
	private String observacao;
	
//	@OneToOne(mappedBy="pendencia")	
//	@JsonIgnore // para evitar loop na criacao do JSON
	@OneToOne( fetch = FetchType.EAGER )
	@MapsId
	@JoinColumn( name = "item_movimentacao_id" )
	private ItemMovimentacao itemMovimentacao;

	@Enumerated(EnumType.STRING)
	private PendenciaType pendenciaTipo;

	public Pendencia() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemMovimentacao getItemMovimentacao() {
		return itemMovimentacao;
	}

	public void setItemMovimentacao(ItemMovimentacao itemMovimentacao) {
		this.itemMovimentacao = itemMovimentacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public PendenciaType getPendenciaTipo() {
		return pendenciaTipo;
	}	
	
	//public String getPendenciaTipo() {
	//	return pendenciaTipo.geTipo();
	//}

	public void setPendenciaTipo(PendenciaType pendenciaTipo) {
		this.pendenciaTipo = pendenciaTipo ;
	}
	
//	public void setPendenciaTipo(String pendenciaTipo_descricao) {
//		this.pendenciaTipo = this.pendenciaTipo.valueOf(pendenciaTipo_descricao);
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemMovimentacao == null) ? 0 : itemMovimentacao.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((pendenciaTipo == null) ? 0 : pendenciaTipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pendencia other = (Pendencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemMovimentacao == null) {
			if (other.itemMovimentacao != null)
				return false;
		} else if (!itemMovimentacao.equals(other.itemMovimentacao))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (pendenciaTipo != other.pendenciaTipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pendencia [id=" + id + ", observacao=" + observacao + ", itemMovimentacao=" + itemMovimentacao
				+ ", pendenciaType=" + pendenciaTipo + "]";
	}
	
}
