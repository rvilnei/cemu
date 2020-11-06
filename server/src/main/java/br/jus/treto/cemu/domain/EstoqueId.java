package br.jus.treto.cemu.domain;

import java.io.Serializable;
import java.util.Objects;


// chave composta
public class EstoqueId  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4476928008227826051L;
	
	private Long materialId;
	private Long unidadeId;
	
    public EstoqueId() {
    }
	
    public EstoqueId(  Long materialId, Long unidadeId ) {
    	this.materialId = materialId;
    	this.unidadeId = unidadeId;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstoqueId estoqueId = (EstoqueId) o;
        return materialId.equals(estoqueId.materialId) &&
        		unidadeId.equals(estoqueId.unidadeId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(materialId, unidadeId);
    }

}
