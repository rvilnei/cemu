package br.jus.treto.cemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.jus.treto.cemu.domain.Pendencia;
import br.jus.treto.cemu.domain.Transportadora;
import br.jus.treto.cemu.repository.PendenciasRepository;
import br.jus.treto.cemu.services.exceptions.MaterialNaoEncontradoException;

@Service
public class PendenciaService {

	@Autowired
	private PendenciasRepository pendenciasRepository; 
	
	public List<Pendencia> listar() {
		return pendenciasRepository.findAll();
	}

	public Pendencia salvar(Pendencia pendencia) {
		return pendenciasRepository.save(pendencia);
	}

	public Pendencia buscar(Long id) {
		Pendencia pendencia = pendenciasRepository.findById(id).orElse(null);
		if(pendencia == null) {
			//throw new MaterialNaoEncontradoException("A transportadora não foi encontrado");
		}
		return pendencia;
	}

	public Pendencia atualizar(Pendencia pendencia) {
		return pendenciasRepository.save(pendencia);
	}

	public void delete(Long id) {
		try {
			pendenciasRepository.deleteById(id);
		}catch( EmptyResultDataAccessException e ){
			//throw new MaterialNaoEncontradoException("A Transportadora não foi encontrada!");
		}		
	}

	public boolean existsById(Long id) {
		return pendenciasRepository.existsById(id)? true: false;
	}
	
}
