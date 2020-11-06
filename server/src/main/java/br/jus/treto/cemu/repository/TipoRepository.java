package br.jus.treto.cemu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.treto.cemu.domain.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Long> {

}
