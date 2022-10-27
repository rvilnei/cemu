package br.jus.treto.cemu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.treto.cemu.domain.Pendencia;

public interface PendenciasRepository extends JpaRepository< Pendencia, Long > {

}
