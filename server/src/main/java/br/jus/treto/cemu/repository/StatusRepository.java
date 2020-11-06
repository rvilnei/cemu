package br.jus.treto.cemu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.treto.cemu.domain.Status;

public interface StatusRepository extends JpaRepository< Status, Long> {

}
