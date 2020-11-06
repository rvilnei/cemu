package br.jus.treto.cemu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.treto.cemu.domain.Material;

public interface MateriaisRepository extends JpaRepository< Material, Long> {

}
