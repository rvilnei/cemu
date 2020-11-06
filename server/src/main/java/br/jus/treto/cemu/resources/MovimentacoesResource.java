package br.jus.treto.cemu.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.jus.treto.cemu.domain.Guia;
import br.jus.treto.cemu.domain.ItemMovimentacao;
import br.jus.treto.cemu.domain.Lancamento;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.domain.Movimentacao;
import br.jus.treto.cemu.domain.Tipo;
import br.jus.treto.cemu.domain.Transportadora;
import br.jus.treto.cemu.domain.Unidade;
import br.jus.treto.cemu.domain.VwUnidades;
import br.jus.treto.cemu.resources.dto.EstoqueDto;
import br.jus.treto.cemu.resources.dto.MovimentacaoDto;
import br.jus.treto.cemu.services.EmailService;
import br.jus.treto.cemu.services.ItensMovimentacoesService;
import br.jus.treto.cemu.services.MateriaisService;
import br.jus.treto.cemu.services.MovimentacoesService;

@CrossOrigin
@RestController
@RequestMapping(value = "/movimentacoes")
public class MovimentacoesResource {


	@Autowired
	private MovimentacoesService movimentacoesService ;

	@Autowired
	private ItensMovimentacoesService itensMovimentacoesService ;

	@Autowired
	private MateriaisService materiaisService ;
	
	@Autowired
	private EmailService emailService;
	
	Movimentacao movimentacao ;
	ItemMovimentacao itemMovimentacao;
	Movimentacao movimentacaoItem;	
	
	@RequestMapping( method = RequestMethod.GET, produces = "application/json"  )
	public ResponseEntity<List<MovimentacaoDto>> listar() {
	
		List<MovimentacaoDto> listMovimentacaoaDto =  MovimentacaoDto.converter( movimentacoesService.listar() );
		listMovimentacaoaDto = atribuirUnidades( listMovimentacaoaDto );
		return  ResponseEntity.status(HttpStatus.OK).body(  listMovimentacaoaDto );
	}

	@GetMapping( "/{id}" )
	public ResponseEntity<MovimentacaoDto> buscar( @PathVariable("id" ) Long id  ){
		Movimentacao movimentacao = movimentacoesService.find( id );
		MovimentacaoDto movimentacaoDto = new MovimentacaoDto( movimentacao );
		movimentacaoDto = unidadeDescricao( movimentacaoDto );
		return ResponseEntity.status(HttpStatus.OK).body(movimentacaoDto );
	}
	 		
	@RequestMapping( value = "{unidadeId}/entrada", method = RequestMethod.GET, produces = "application/json"  )
	public ResponseEntity<List<MovimentacaoDto>> listarEntrada(  @PathVariable("unidadeId") Long unidadeId  ) {
	
		List<MovimentacaoDto> listMovimentacaoaDto =  MovimentacaoDto.converter( movimentacoesService.listarEntrada( unidadeId ) );
		listMovimentacaoaDto = atribuirUnidades( listMovimentacaoaDto );
		return  ResponseEntity.status(HttpStatus.OK).body(  listMovimentacaoaDto );
	}

	@RequestMapping( value = "{unidadeId}/saida", method = RequestMethod.GET, produces = "application/json"  )
	public ResponseEntity<List<MovimentacaoDto>> listarSaida(  @PathVariable("unidadeId") Long unidadeId  ) {
	
		List<MovimentacaoDto> listMovimentacaoaDto =  MovimentacaoDto.converter( movimentacoesService.listarSaida( unidadeId ) );
		listMovimentacaoaDto = atribuirUnidades( listMovimentacaoaDto );
		return  ResponseEntity.status(HttpStatus.OK).body(  listMovimentacaoaDto );
	}
	
	private List<MovimentacaoDto> atribuirUnidades( List<MovimentacaoDto> movimentacoes ){
		movimentacoes.forEach( movimentacao ->
										{	
										     unidadeDescricao(movimentacao);
										}
									);
		return  movimentacoes;

	}
	
	private MovimentacaoDto unidadeDescricao( MovimentacaoDto movimentacao ) {
		String unidadeOrigem = movimentacoesService.getUnidade(movimentacao.getUnidadeorigemId() ).getSigla();
		String unidadeDestino = movimentacoesService.getUnidade(movimentacao.getUnidadedestinoId() ).getSigla();
		movimentacao.setUnidadeOrigem(unidadeOrigem);
		movimentacao.setUnidadeDestino(unidadeDestino);
		
		return movimentacao;
	}
	
	@RequestMapping( method = RequestMethod.POST  )
	public ResponseEntity<?> salvar( @RequestBody Movimentacao movimentacaoItem ) {
		movimentacao = movimentacaoItem ;
		List<ItemMovimentacao> itens = new ArrayList<>();
		itens.addAll( movimentacaoItem.getItens());
		
		itens.forEach( itemMovimentacao -> {
			ItemMovimentacao item =(ItemMovimentacao) itemMovimentacao ;
			item.setMaterial(  materiaisService.buscar(item.getMaterialId() )  ); 
			item.setMovimentacao(movimentacao);
			item.setSituacao("ITEM_PENDENTE");
		});			

		movimentacao = movimentacoesService.salvar(movimentacao);	
		movimentacoesService.reduzirEstoque( movimentacao );
		movimentacoesService.registrarLancamento( movimentacao );
		
		/**  serviço de envio de email **/
		//emailService.enviar( "VILNEIze", "rvilnei@gmail.com");
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(movimentacao.getId()).toUri();
		return ResponseEntity.created(uri).body(movimentacao);
	}
		
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar (@RequestBody Material material, @PathVariable("id") Long id ) {
		material.setId(id); // para garantir q o recurso q será atualizado é o do id xxx
		materiaisService.atualizar(material);
		return ResponseEntity.noContent().build();	
	}
		
	@RequestMapping( value = "/updateItem", method = RequestMethod.PUT  )
	public ResponseEntity<?> atualizarItem(  @RequestBody Movimentacao movimentacaoRequest) {

		List<ItemMovimentacao> itens = new ArrayList<>();
		itens.addAll( movimentacao.getItens());
		
		movimentacao = movimentacoesService.find(movimentacaoRequest.getId());
		movimentacao.setStatus("MOVI_CONCLUIDA");
		movimentacao.getItens().forEach( item -> {

			if( item.getSituacao().contentEquals("ITEM_PENDENTE") ) {
				item.setSituacao("ITEM_RECEBIDO");
			}
			 			
		});		
	
		movimentacao = movimentacoesService.salvar(movimentacao);
		movimentacao.getItens().forEach( item -> {
			if( item.getSituacao().contentEquals("ITEM_RECEBIDO") ) {
	 			movimentacoesService.aumentarEstoqueItem( movimentacao, item );
	 			movimentacoesService.lancamentoItemMoviEntrada( movimentacao, item );
			}
		});	
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).body(movimentacaoItem);
	}
	
	
	
	@RequestMapping( value = "/{id}/updateItem", method = RequestMethod.PUT  )
	public ResponseEntity<?> atualizarItem(  @RequestBody ItemMovimentacao item, @PathVariable("id") Long id ) {
		movimentacao =movimentacoesService.find(id);
		List<ItemMovimentacao> itens = new ArrayList<>();
		itens.addAll( movimentacao.getItens());
		itens.forEach( it -> {
			if ( it.getId() == item.getId() )
				if ( it.getSituacao().contentEquals("ITEM_PENDENTE" ) ) {
					it.setSituacao(item.getSituacao());
					it.setMovimentacao(movimentacao);
					movimentacao.setStatus("MOVI_EM_ANDAMENTO");
					movimentacao = movimentacoesService.salvar(movimentacao);
		 			//itemMovimentacao = itensMovimentacoesService.atualizar(it);
		 			movimentacoesService.aumentarEstoqueItem( movimentacao, it );
		 			movimentacoesService.lancamentoItemMoviRetorno( movimentacao, it );
				}
 			
		});		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).body(movimentacaoItem);
	}
		
	@RequestMapping( value = "/unidades" )
	public ResponseEntity<List <Unidade>> unidades(){
		List<Unidade> unidades = movimentacoesService.listarUnidades();
		return ResponseEntity.status(HttpStatus.OK).body(unidades);
	}
	
}
