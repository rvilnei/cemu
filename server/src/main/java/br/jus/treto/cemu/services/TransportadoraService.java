package br.jus.treto.cemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.jus.treto.cemu.domain.Transportadora;
import br.jus.treto.cemu.repository.TransportadoraRepository;
import br.jus.treto.cemu.services.exceptions.MaterialNaoEncontradoException;


@Service
public class TransportadoraService {

	@Autowired()
	private TransportadoraRepository transportadoraRepository;
	
	public List<Transportadora> listar() {
		return transportadoraRepository.findAll();
	}

	public Transportadora salvar(Transportadora transportadora) {
		return transportadoraRepository.save(transportadora);
	}

	public Transportadora buscar(Long id) {
		Transportadora transportadora = transportadoraRepository.findById(id).orElse(null);
		if(transportadora == null) {
			throw new MaterialNaoEncontradoException("A transportadora não foi encontrado");
		}
		return transportadora;
	}

	public Transportadora atualizar(Transportadora transportadora) {
		return transportadoraRepository.save(transportadora);
	}

	public void delete(Long id) {
		try {
			transportadoraRepository.deleteById(id);
		}catch( EmptyResultDataAccessException e ){
			throw new MaterialNaoEncontradoException("A Transportadora não foi encontrada!");
		}
		
	}

	public boolean existsById(Long id) {
		return transportadoraRepository.existsById(id)? true: false;
	}
	
}
