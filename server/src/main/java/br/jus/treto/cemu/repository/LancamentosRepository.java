package br.jus.treto.cemu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.treto.cemu.domain.Lancamento;

public interface LancamentosRepository extends JpaRepository< Lancamento, Long > {

}
