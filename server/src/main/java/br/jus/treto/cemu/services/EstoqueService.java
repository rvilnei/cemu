package br.jus.treto.cemu.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.jus.treto.cemu.domain.Estoque;
import br.jus.treto.cemu.domain.Unidade;
import br.jus.treto.cemu.repository.EstoqueRepository;
import br.jus.treto.cemu.repository.UnidadesRepository;
import br.jus.treto.cemu.services.exceptions.GenericsException;
import br.jus.treto.cemu.services.exceptions.MaterialNaoEncontradoException;

@Service
public class EstoqueService {
	
	@Autowired
	private UnidadesRepository unidadesRepository;
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	public List<Estoque> listar(){
		return estoqueRepository.findAll();
	}
	
	public Estoque buscar(Long materialId, Long unidadeId) {
		Estoque estoque = estoqueRepository.findByMaterialIdAndUnidadeId(materialId, unidadeId) ;
		return estoque;		
	}
	
	public List<Estoque> buscaEstoqueUnidade( Long unidadeId) {
		List<Estoque> estoqueUnidade = estoqueRepository.findByUnidadeId(unidadeId) ;
		return estoqueUnidade;		
	}
	
	
	public Estoque salvar(Estoque estoque) {
		return estoqueRepository.save(estoque);
	}
	
	public void deletar( Long materialId, Long unidadeId ) {
		try {
			Estoque estoque = buscar(materialId, unidadeId);
			estoqueRepository.delete(estoque);
		} catch(EmptyResultDataAccessException e) { 
			// criar exeception exception para estoque
			throw new MaterialNaoEncontradoException("O Material na unidade não foi encontrado");
		}
	}
	
	public void atualizar(Estoque estoque) {
		Estoque estoqueAtual = buscar(estoque.getMaterialId() ,estoque.getUnidadeId() );
		if(estoqueAtual == null) {
			throw new GenericsException("Não há Material cadastrado no estoque da unidade");
		}
		estoqueRepository.save(estoque);
	}
	
	public void verificarExistencia( Estoque estoque) {
		Long materialId = estoque.getMaterialId();
		Long unidadeId = estoque.getUnidadeId() ;
		Estoque estoqueAtual = buscar(materialId , unidadeId );
		if(estoqueAtual != null) {
			// criar exception especifica para estoque
			throw new GenericsException("Materia já cadastrado no estoque da unidade");
		}
	}

	public void atualizarEstoque(Long materialId, Long unidadeId, Integer qtd) {
		// TODO Auto-generated method stub
		Estoque estoqueAtual = buscar(materialId, unidadeId) ;		
		if(estoqueAtual != null) {
			Integer quantidadeAtual = estoqueAtual.getQuantidade();
			estoqueAtual.setQuantidade(quantidadeAtual+qtd);
			estoqueAtual.setDataAlteracao( LocalDateTime.now()  );   
			atualizar(estoqueAtual);
		} else {
			estoqueAtual = new Estoque();
			estoqueAtual.setMaterialId(materialId);
			estoqueAtual.setUnidadeId(unidadeId);
			estoqueAtual.setQuantidade(qtd);
			estoqueAtual.setDataAlteracao( LocalDateTime.now() );  
			salvar(estoqueAtual);
		}
		
	}
	

	public void reduzirEstoque(Long materialId, Long unidadeId, Integer qtd) {

		Estoque estoqueAtual = buscar(materialId, unidadeId) ;		
		System.out.println( "*****  "+ estoqueAtual.getUnidadeId()) ;
		Integer quantidadeAtual = estoqueAtual.getQuantidade();
		estoqueAtual.setQuantidade(quantidadeAtual-qtd);
		estoqueAtual.setDataAlteracao( LocalDateTime.now()  );   
		atualizar(estoqueAtual);
		
	}
	
	public List<Unidade> listarUnidades() {
		return unidadesRepository.findAll();
	}

	
	public Unidade getUnidade( Long id ) {
		return unidadesRepository.findById(id).orElse(null);
	}
	
	
}
