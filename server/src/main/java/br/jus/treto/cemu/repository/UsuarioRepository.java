package br.jus.treto.cemu.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.treto.cemu.domain.User;

//import br.com.alura.forum.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<User, Long> {
	

	Optional<User> findByEmail(String email);

}

