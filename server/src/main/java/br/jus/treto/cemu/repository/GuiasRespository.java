package br.jus.treto.cemu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jus.treto.cemu.domain.Guia;

public interface GuiasRespository extends JpaRepository<Guia, Long> {

    @Query(" SELECT MAX(id) AS id FROM Guia ")
    Long maxIdGuia();
}
