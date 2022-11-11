package br.jus.treto.cemu.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.treto.cemu.domain.ItemMovimentacao;
import br.jus.treto.cemu.domain.Pendencia;
import br.jus.treto.cemu.domain.Pendencia.PendenciaType;
import br.jus.treto.cemu.domain.Transportadora;
import br.jus.treto.cemu.services.ItensMovimentacoesService;
import br.jus.treto.cemu.services.PendenciaService;

@CrossOrigin
@RestController
@RequestMapping(value="/pendencias")
public class PendenciaResource {
	
	@Autowired
	private PendenciaService pendenciaService; 
	
	@Autowired
	private ItensMovimentacoesService itensMovimentacoesService ;
	
	@GetMapping()
	public ResponseEntity<List<Pendencia>> listar(){
		List<Pendencia> lista = pendenciaService.listar();
		return ResponseEntity.status(HttpStatus.OK).body( lista );
	}
	
	@PostMapping("/item/{item_id}")
	public ResponseEntity<Pendencia> salvar(@PathVariable(value ="item_id") Long itemPendenteId  ,@RequestBody Pendencia pendencia ){
		ItemMovimentacao item = itensMovimentacoesService.buscar(itemPendenteId);
		pendencia.setItemMovimentacao(item);
		Pendencia pendenciaSalva = pendenciaService.salvar( pendencia );
		return ResponseEntity.status(HttpStatus.CREATED).body( pendenciaSalva );
	}
	
	@GetMapping( "/{id}" )
	public ResponseEntity<Pendencia> buscar( @PathVariable("id" ) Long id  ){
		Pendencia pendencia = pendenciaService.buscar( id );
		return ResponseEntity.status(HttpStatus.OK).body(pendencia);
	}
	
	@PutMapping( "/{id}" )
	public ResponseEntity<Pendencia> atualizar( @RequestBody Pendencia pendencia, @PathVariable("id") Long id ){
		if( !pendenciaService.existsById(id)  ) {
			return ResponseEntity.notFound().build();
		}
		pendencia.setId(id);
		Pendencia pendenciaAlterada = pendenciaService.atualizar( pendencia );
		return ResponseEntity.ok( pendenciaAlterada );
	}
	
	@DeleteMapping( "/{id}" )
	public ResponseEntity<Void> deletar( @PathVariable("id") Long id ){
		if( !pendenciaService.existsById(id)  ) {
			return ResponseEntity.notFound().build();
		}
		pendenciaService.delete(id);
		return ResponseEntity.noContent().build();
	} 

	@GetMapping("/tipos")
	public ResponseEntity< Map<PendenciaType, String> > listaTipos(){
		PendenciaType[]  pendenciaType = PendenciaType.values();
		Map<PendenciaType, String> tiposPendenciasMap = new HashMap<>();
		for (PendenciaType type : pendenciaType) {
			tiposPendenciasMap.put(type, type.geTipo());
		}
		return ResponseEntity.ok(tiposPendenciasMap );
	}
	
}
