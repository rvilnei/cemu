package br.jus.treto.cemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.treto.cemu.domain.ItemMovimentacao;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.domain.Movimentacao;
import br.jus.treto.cemu.repository.ItensMovimentacoesRepository;
import br.jus.treto.cemu.repository.MovimentacoesRepository;
import br.jus.treto.cemu.services.exceptions.MaterialNaoEncontradoException;

@Service
public class ItensMovimentacoesService {

	@Autowired
	private ItensMovimentacoesRepository itensMovimentacoesRepository ;
	
	@Autowired
	private MovimentacoesService movimentacoesService;
	
	public List<ItemMovimentacao> listar(){
		return itensMovimentacoesRepository.findAll();
	}
	
//	public List<ItemMovimentacao> listarPorMovimentacao( Movimentacao movimentacao ){
//		return itensMovimentacoesRepository.findByMovimentacao( movimentacao );
//	}
	
	public List<ItemMovimentacao> itensByMovimentacaoId( Movimentacao movimentacao ){
		return itensMovimentacoesRepository.findByMovimentacao( movimentacao );
	}
	
	public ItemMovimentacao salvar(ItemMovimentacao itemMovimentacao) {
		itemMovimentacao.setId(null);
		return itensMovimentacoesRepository.save(itemMovimentacao);
	}

	public ItemMovimentacao atualizar(ItemMovimentacao item) {
		verificarExistencia(item);
		return itensMovimentacoesRepository.save(item);
	}
	
	private void verificarExistencia(ItemMovimentacao item) {
		buscar(item.getId());
	}
	
	public List<ItemMovimentacao> itensByMovimentacaoId( Long idMovimentacao ){
		Movimentacao movimentacao = movimentacoesService.find( idMovimentacao );
		return itensMovimentacoesRepository.findByMovimentacao( movimentacao );
	}
	
	public ItemMovimentacao buscar(Long id) {
		ItemMovimentacao item = itensMovimentacoesRepository.findById(id).orElse(null);
		if(item == null) {
			throw new MaterialNaoEncontradoException("O item não foi encontrado");
		}
		return item;
	}
	
}
