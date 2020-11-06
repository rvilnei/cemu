package br.jus.treto.cemu.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import br.jus.treto.cemu.domain.Guia;
import br.jus.treto.cemu.services.GuiasService;

@CrossOrigin
@RestController
@RequestMapping(value="/guias" )
public class GuiasResource {
	
	@Autowired
	private GuiasService guiasService;
	
	@RequestMapping( method = RequestMethod.GET )
	public ResponseEntity<List<Guia>> listar() {
		List<Guia> lista = guiasService.listar();
		return ResponseEntity.status(HttpStatus.OK).body( lista  );
	}
	
	@RequestMapping( value="/{id}", method = RequestMethod.GET )
	public ResponseEntity<Guia> buscar(@PathVariable("id") Long id  ) {
		Guia guia = guiasService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body( guia  );
	}
	
	@RequestMapping( value="numeroNovaGuia", method = RequestMethod.GET )
	public ResponseEntity<String> getNumeroGuia( ) {
		String numeroGerado = guiasService.getNumeroGuia();
		return ResponseEntity.status(HttpStatus.OK).body( numeroGerado  );
	}
	
	@RequestMapping( method = RequestMethod.POST )
	public ResponseEntity< Guia > salvar( @RequestBody Guia guia ){
		Guia guiaSalva = guiasService.salvar( guia );
		return ResponseEntity.status(HttpStatus.OK).body(guiaSalva);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar (@RequestBody Guia guia, @PathVariable("id") Long id ) {
		guia.setId(id); // para garantir q o recurso q será atualizado é o do id xxx
		guiasService.atualizar(guia);
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public  ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		/** exception está sendo tratada no inteceptor ResourceExceptionHandler **/
		guiasService.delete(id);
		return ResponseEntity.noContent().build();	
	}
	
}
