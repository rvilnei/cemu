package br.jus.treto.cemu.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.treto.cemu.domain.ItemMovimentacao;
import br.jus.treto.cemu.domain.Lancamento;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.domain.Movimentacao;
import br.jus.treto.cemu.domain.Unidade;
import br.jus.treto.cemu.domain.VwUnidades;
import br.jus.treto.cemu.repository.LancamentosRepository;
import br.jus.treto.cemu.repository.MateriaisRepository;
import br.jus.treto.cemu.repository.MovimentacoesRepository;
import br.jus.treto.cemu.repository.UnidadesRepository;
import br.jus.treto.cemu.resources.enumerator.Item_Situacao_Enum;
import br.jus.treto.cemu.resources.enumerator.Movi_Status_Enum;
import br.jus.treto.cemu.services.exceptions.MaterialNaoEncontradoException;

@Service
public class MovimentacoesService {

	@Autowired
	private MovimentacoesRepository movimentacoesRepository ;
	
	@Autowired
	private LancamentosRepository lancamentosRepository;

	@Autowired
	private UnidadesRepository unidadesRepository ;
		
	@Autowired
	private EstoqueService estoqueService ;
		
	public List<Movimentacao> listar(){
		return movimentacoesRepository.findAll();
	}
	
	public Movimentacao find(Long id ){
		Movimentacao movimentacao = movimentacoesRepository.findById(id).orElse(null);
		if(movimentacao == null) {
			throw new MaterialNaoEncontradoException("O material não foi encontrado");
		}
		return movimentacao;
	}

	public  List<Movimentacao> listarEntrada(Long unidadeId) {
		//return movimentacoesRepository.findByUnidadedestinoId( unidadeId );
		return movimentacoesRepository.findByUnidadedestinoIdAndGuiaNotNull( unidadeId );
	}
	
	public  List<Movimentacao> listarSaida(Long unidadeId) {
		return movimentacoesRepository.findByUnidadeorigemId( unidadeId );
	}
	
	public Movimentacao salvar(Movimentacao movimentacao) {
		//movimentacao.setId(null);
		if (movimentacao.getId() == null  ) {
				movimentacao.setDatacriacao( LocalDateTime.now()  ); 
			//	movimentacao.setStatus("MOVI_PENDENTE");
				movimentacao.setStatus(Movi_Status_Enum.MOVI_PENDENTE.name());
		}
		return movimentacoesRepository.save(movimentacao);
	}
  
	public Movimentacao atualizar(Movimentacao movimentacao) {
		verificarExistencia(movimentacao);
		return movimentacoesRepository.save(movimentacao);
	}
	
	private void verificarExistencia(Movimentacao Movimentacao) {
		find(Movimentacao.getId());
	}
	
	public List<Unidade> listarUnidades() {
		return unidadesRepository.findAll();
	}

	public void reduzirEstoque(Movimentacao movimentacao) {
		List<ItemMovimentacao> itens = new ArrayList<>();
		itens.addAll( movimentacao.getItens());
		Unidade unidadeOrigem =  unidadesRepository.findById(movimentacao.getUnidadeorigemId()  ).orElse(null);
		Long unidadeId = unidadeOrigem.getId();
		
		itens.forEach( itemMovimentacao -> {
			ItemMovimentacao item =(ItemMovimentacao) itemMovimentacao ;
			Long materialId = item.getMaterialId();
			Integer quantidade = item.getQuantidadeItem();
			estoqueService.reduzirEstoque(materialId, unidadeId, quantidade);
		});			
		
	}

	public void aumentarEstoqueItem(Movimentacao movimentacao, ItemMovimentacao item) {

		Unidade unidadeOrigem =  unidadesRepository.findById(movimentacao.getUnidadeorigemId()  ).orElse(null);
		Long unidadeOrigemId = unidadeOrigem.getId();
		Long materialId = item.getMaterialId();
		Integer quantidade = item.getQuantidadeItem();
		estoqueService.atualizarEstoque(materialId, unidadeOrigemId, quantidade);
		
	}
	
	public void aumentarEstoqueItemEntrada(Movimentacao movimentacao, ItemMovimentacao item) {
		Unidade unidadeDestino =  unidadesRepository.findById(movimentacao.getUnidadedestinoId()  ).orElse(null);
		Long unidadeDestinoId = unidadeDestino.getId();
		Long materialId = item.getMaterialId();
		Integer quantidade = item.getQuantidadeItem();
		estoqueService.atualizarEstoque(materialId, unidadeDestinoId, quantidade);
		
	}
	
	public void registrarLancamento(Movimentacao movimentacao) {
		Unidade unidadeOrigem =  unidadesRepository.findById(movimentacao.getUnidadeorigemId()  ).orElse(null);
		Long   unidadeOrigemId= unidadeOrigem.getId();
		Unidade unidadeDestino =  unidadesRepository.findById(movimentacao.getUnidadedestinoId()  ).orElse(null);
		Long unidadeDestinoId = unidadeDestino.getId();	 
		
		List<ItemMovimentacao> itens = new ArrayList<>();
		itens.addAll( movimentacao.getItens());
	
		itens.forEach( itemMovimentacao -> {
			Lancamento lancamento = new Lancamento();
			ItemMovimentacao item =(ItemMovimentacao) itemMovimentacao ;	
			lancamento.setData( LocalDate.now()  );
			lancamento.setMaterial( item.getMaterial());
			lancamento.setUnidadeorigemId(unidadeOrigemId);
			lancamento.setUnidadedestinoId(unidadeDestinoId);
			lancamento.setQuantidade(item.getQuantidadeItem());
			//lancamento.setTipo("MOVI_ITEM_ENVIADO");
			lancamento.setTipo(Item_Situacao_Enum.MOVI_ITEM_PENDENTE.name());
			lancamentosRepository.save(lancamento);
		});		
		
	}
	
	public void lancamentoItemMoviRetorno(Movimentacao movimentacao, ItemMovimentacao item) {
		
		Unidade unidadeOrigem =  unidadesRepository.findById(movimentacao.getUnidadeorigemId()  ).orElse(null);
		Long  unidadeOrigemId = unidadeOrigem.getId();
		Unidade unidadeDestino =  unidadesRepository.findById(movimentacao.getUnidadedestinoId()  ).orElse(null);
		Long unidadeDestinoId = unidadeDestino.getId();	 
		Lancamento lancamento = new Lancamento();	
		lancamento.setData( LocalDate.now()  );
		lancamento.setMaterial( item.getMaterial());
		lancamento.setUnidadeorigemId(unidadeDestinoId);
		lancamento.setUnidadedestinoId(unidadeOrigemId);
		lancamento.setQuantidade(item.getQuantidadeItem());
		//lancamento.setTipo("MOVI_ITEM_NAO_RECEBIDO");
		lancamento.setTipo( Item_Situacao_Enum.MOVI_ITEM_NAO_RECEBIDO.name() );
		lancamentosRepository.save(lancamento);

	}
	
	public void lancamentoItemMoviEntrada(Movimentacao movimentacao, ItemMovimentacao item) {	
		Unidade unidadeOrigem =  unidadesRepository.findById(movimentacao.getUnidadeorigemId()  ).orElse(null);
		Long   unidadeOrigemId= unidadeOrigem.getId();
		Unidade unidadeDestino =  unidadesRepository.findById(movimentacao.getUnidadedestinoId()  ).orElse(null);
		Long unidadeDestinoId = unidadeDestino.getId();	 
		List<ItemMovimentacao> itens = new ArrayList<>();
		itens.addAll( movimentacao.getItens());
		Lancamento lancamento = new Lancamento();	
		lancamento.setData( LocalDate.now()  );
		lancamento.setMaterial( item.getMaterial());
		lancamento.setUnidadeorigemId(unidadeDestinoId);
		lancamento.setUnidadedestinoId(unidadeOrigemId);
		lancamento.setQuantidade(item.getQuantidadeItem());
		//lancamento.setTipo("MOVI_ITEM_RECEBIDO");
		lancamento.setTipo( Item_Situacao_Enum.MOVI_ITEM_RECEBIDO.name() );
		lancamentosRepository.save(lancamento);
	}
	
	public Unidade getUnidade( Long id ) {
		return unidadesRepository.findById(id).orElse(null);
	}
	
}
