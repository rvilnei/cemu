package br.jus.treto.cemu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.treto.cemu.domain.ItemMovimentacao;
import br.jus.treto.cemu.domain.Movimentacao;

public interface ItensMovimentacoesRepository   extends JpaRepository< ItemMovimentacao, Long> {
	List<ItemMovimentacao> findByMovimentacao(Movimentacao movimentacao);

}
