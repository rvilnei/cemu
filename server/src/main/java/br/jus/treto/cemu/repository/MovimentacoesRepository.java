package br.jus.treto.cemu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.domain.Movimentacao;

public interface MovimentacoesRepository  extends JpaRepository< Movimentacao, Long> {
	
	List<Movimentacao> findByUnidadeorigemId(Long unidadeId);

	List<Movimentacao> findByUnidadedestinoId(Long unidadeId);

	List<Movimentacao> findByUnidadedestinoIdAndGuiaNotNull(Long unidadeId);
}
