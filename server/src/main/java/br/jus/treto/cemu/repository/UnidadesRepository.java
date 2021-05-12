package br.jus.treto.cemu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import br.jus.treto.cemu.domain.Unidade;
import br.jus.treto.cemu.domain.VwUnidades;

public interface UnidadesRepository extends JpaRepository< Unidade, Long > {

}
