package br.jus.treto.cemu.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class ItemMovimentacao {

		@Id
		@GeneratedValue( strategy = GenerationType.IDENTITY )
		private Long id;
		private Integer  quantidadeItem;
		//private Long movimentacaoId;
		private String situacao;  // [ pendente, recebiddo , nao recebido ]
		//private Long pendenciaId;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JsonIgnore // para evitar loop na criacao do JSON
		//@JsonProperty(access = Access.AUTO)
		private Movimentacao movimentacao;
		

		@Column(name="material_id", updatable=false, insertable=false)
		private Long materialId;
		@ManyToOne( fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "material_id")
		// @OnDelete(action = OnDeleteAction.CASCADE)
		//@JsonIgnore // para evitar loop na criacao do JSON
		private Material material;	
		
		
		public Movimentacao getMovimentacao() {
			return movimentacao;
		}
		public void setMovimentacao(Movimentacao movimentacao) {
			this.movimentacao = movimentacao;
		}
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public Integer getQuantidadeItem() {
				return quantidadeItem;
			}
			public void setQuantidadeItem(Integer quantidadeItem) {
				this.quantidadeItem = quantidadeItem;
			}
			public Material getMaterial() {
				return material;
			}
			public void setMaterial(Material material) {
				this.material = material;
			}
			public Long getMaterialId() {
				return materialId;
			}
			public void setMaterialId(Long materialId) {
				this.materialId = materialId;
			}
			public String getSituacao() {
				return situacao;
			}
			public void setSituacao(String situacao) {
				this.situacao = situacao;
			}
	
}
