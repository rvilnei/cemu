package br.jus.treto.cemu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.treto.cemu.domain.Estoque;
import br.jus.treto.cemu.domain.EstoqueId;
import br.jus.treto.cemu.domain.Unidade;

public interface EstoqueRepository extends JpaRepository< Estoque, EstoqueId > {
	 Estoque findByMaterialIdAndUnidadeId(Long materialId, Long unidadeId);
	 Void removeByMaterialIdAndUnidadeId(Long materialId, Long unidadeId);
	 List<Estoque> findByMaterialId( Long materialId );
	 List<Estoque> findByUnidadeId( Long unidadeId );
	// Void remove(Long materialId, String unidade);
}
