package br.jus.treto.cemu.resources;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.treto.cemu.domain.Transportadora;
import br.jus.treto.cemu.services.TransportadoraService;

@CrossOrigin
@RestController
@RequestMapping(value="transportadoras")
public class TransportadoraResource {
	
	@Autowired()
	TransportadoraService transportadoraService;
	
	@RequestMapping( method= RequestMethod.GET )
	@GetMapping()
	public ResponseEntity<List<Transportadora>> listar(){
		List<Transportadora> lista = transportadoraService.listar();
		return ResponseEntity.status(HttpStatus.OK).body( lista );
	}
	 
	//@RequestMapping( method= RequestMethod.POST )
	@PostMapping()
	public ResponseEntity<Transportadora> salvar( @RequestBody Transportadora transportadora ){
		Transportadora transportadoraSalva = transportadoraService.salvar( transportadora );
		return ResponseEntity.status(HttpStatus.CREATED).body( transportadoraSalva );
	}
	
	//@RequestMapping( value="/{id}", method= RequestMethod.GET )
	@GetMapping( "/{id}" )
	public ResponseEntity<Transportadora> buscar( @PathVariable("id" ) Long id  ){
		Transportadora transportadora = transportadoraService.buscar( id );
		return ResponseEntity.status(HttpStatus.OK).body(transportadora);
	}
	
	//@RequestMapping( value="/{id}", method= RequestMethod.PUT )
	@PutMapping( "/{id}" )
	public ResponseEntity<Transportadora> atualizar( @RequestBody Transportadora transportadora, @PathVariable("id") Long id ){
		if( !transportadoraService.existsById(id)  ) {
			return ResponseEntity.notFound().build();
		}
		transportadora.setId(id);
		Transportadora transportadoraAlterada = transportadoraService.atualizar( transportadora );
		return ResponseEntity.ok( transportadoraAlterada );
	}
	
	//@RequestMapping( value="/{id}", method= RequestMethod.DELETE )
	@DeleteMapping( "/{id}" )
	public ResponseEntity<Void> deletar( @PathVariable("id") Long id ){
		if( !transportadoraService.existsById(id)  ) {
			return ResponseEntity.notFound().build();
		}
		transportadoraService.delete(id);
		return ResponseEntity.noContent().build();
	} 
}
