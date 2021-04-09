package br.jus.treto.cemu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.domain.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Long> {

	Optional<Tipo> findByNome(String nome);

}
