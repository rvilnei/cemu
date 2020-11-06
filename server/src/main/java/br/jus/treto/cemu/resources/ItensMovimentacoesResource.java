package br.jus.treto.cemu.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.jus.treto.cemu.domain.ItemMovimentacao;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.domain.Movimentacao;
import br.jus.treto.cemu.services.ItensMovimentacoesService;
import br.jus.treto.cemu.services.MovimentacoesService;
import br.jus.treto.cemu.services.exceptions.MaterialNaoEncontradoException;

@CrossOrigin
@RestController
@RequestMapping(value = "/itens")
public class ItensMovimentacoesResource {

	@Autowired
	private ItensMovimentacoesService itensMovimentacoesService ;
	
	@RequestMapping( method = RequestMethod.GET, produces = "application/json"  )
	public ResponseEntity<List<ItemMovimentacao>> listar() {
		 return ResponseEntity.status(HttpStatus.OK).body( itensMovimentacoesService.listar());
	}
	
	@RequestMapping( value = "/{idMovimentacao}", method = RequestMethod.GET, produces = "application/json"  )
	public ResponseEntity<List<ItemMovimentacao>> itensPorMovimentacaoId(   Long movimentacaoId ) {
		 return ResponseEntity.status(HttpStatus.OK).body( itensMovimentacoesService.itensByMovimentacaoId( movimentacaoId  ) );
	}
	
	@RequestMapping( method = RequestMethod.POST  )
	public ResponseEntity<?> salvar( @RequestBody ItemMovimentacao itemMovimentacao ) {
		itemMovimentacao = itensMovimentacoesService.salvar(itemMovimentacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(itemMovimentacao.getId()).toUri();
		return ResponseEntity.created(uri).body(itemMovimentacao);
	}
	
	public ItemMovimentacao buscar(Long id) {
		ItemMovimentacao item =  itensMovimentacoesService.buscar(id);
		if(item == null) {
			throw new MaterialNaoEncontradoException("O item não foi encontrado");
		}
		return item;
	}
	
	
}
