package br.jus.treto.cemu.resources;

import java.net.URI;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import br.jus.treto.cemu.domain.Lancamento;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.domain.Status;
import br.jus.treto.cemu.domain.Tipo;
import br.jus.treto.cemu.resources.dto.LancamentoDto;
import br.jus.treto.cemu.resources.dto.MaterialDto;
import br.jus.treto.cemu.resources.dto.MovimentacaoDto;
import br.jus.treto.cemu.services.MateriaisService;

@CrossOrigin
@RestController
@RequestMapping(value = "/materiais")
public class MateriaisResource {
	
	@Autowired
	private MateriaisService materiaisService ;

	@GetMapping
	public ResponseEntity<List<MaterialDto>> listar() {
		List<Material> materiais = materiaisService.listar();
		List<MaterialDto> listMaterialDto =  MaterialDto.converter( materiais, materiaisService );
		 return ResponseEntity.status(HttpStatus.OK).body( listMaterialDto );
	}
	  
	@PostMapping 
	public ResponseEntity<?> salvar( @RequestBody Material material, UriComponentsBuilder uriBuilder ) {
		material = materiaisService.salvar(material);
		URI uri = uriBuilder.path("/materiais/{id}").buildAndExpand(material.getId()).toUri();
		return ResponseEntity.created(uri).body(material);
	}
 
	@DeleteMapping("/{id}") 
	public  ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		/** exception está sendo tratada no inteceptor ResourceExceptionHandler **/
		materiaisService.delete(id);
		return ResponseEntity.noContent().build();	
	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<Void> atualizar (@RequestBody Material material, @PathVariable("id") Long id ) {
		material.setId(id); // para garantir q o recurso q será atualizado é o do id xxx
		materiaisService.atualizar(material);
		return ResponseEntity.noContent().build();	
	}
	
	//poderia criar outro resource e service para Lancamento 
	@PostMapping ("/{id}/lancamentos")
	public ResponseEntity<Void> adicionarLancamento( @PathVariable("id") Long materialId, @RequestBody Lancamento lancamento){
		materiaisService.salvaLancamento(materialId, lancamento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build(); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		/** exception está sendo tratada no inteceptor ResourceExceptionHandler **/
		Material material =  materiaisService.buscar(id);
		MaterialDto materialDto = MaterialDto.transformaEmDTO(material);
		List<Lancamento> lancamentos = materiaisService.listarLancamentos(material.getId());
		atribuirUnidades( materialDto.getLancamentos() );
		return  ResponseEntity.status(HttpStatus.OK).body( materialDto );
	}
	 
	@RequestMapping("/{id}/lancamentos")
	public ResponseEntity< List<LancamentoDto>> listarLancamentos( @PathVariable("id") long materialId ){
		List<Lancamento> lancamentos = materiaisService.listarLancamentos(materialId);
	
		List<LancamentoDto> listLancamentoDto =  LancamentoDto.converter( lancamentos );
		listLancamentoDto = atribuirUnidades( listLancamentoDto );
		
		return ResponseEntity.status(HttpStatus.OK).body(listLancamentoDto);
	}
	
	private List<LancamentoDto> atribuirUnidades( List<LancamentoDto> lancamentos ){
		lancamentos.forEach( lancamento -> {		String unidadeOrigem = materiaisService.getUnidade(lancamento.getUnidadeorigemId() ).getSigla();
													String unidadeDestino = materiaisService.getUnidade(lancamento.getUnidadedestinoId() ).getSigla();
													lancamento.setUnidadeOrigem(unidadeOrigem);
													lancamento.setUnidadeDestino(unidadeDestino);
													}
									);
		return  lancamentos;
	}

	@RequestMapping( value = "/tipos" )
	public ResponseEntity<List <Tipo>> tipos(){
		List<Tipo> tipos = materiaisService.listarTipos();
		return ResponseEntity.status(HttpStatus.OK).body(tipos);
	}
	
	@RequestMapping(value= "/status")
	public ResponseEntity<List<Status>> status(){
		List<Status> status = materiaisService.listarStatus();
		return ResponseEntity.status(HttpStatus.OK).body(status);
	}
	
}
