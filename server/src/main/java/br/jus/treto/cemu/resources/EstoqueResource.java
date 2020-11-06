package br.jus.treto.cemu.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.jus.treto.cemu.domain.Estoque;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.domain.Unidade;
import br.jus.treto.cemu.resources.dto.EstoqueDto;
import br.jus.treto.cemu.services.EstoqueService;
import br.jus.treto.cemu.services.MateriaisService;

@CrossOrigin
@RestController
@RequestMapping( value = "/estoques" )
public class EstoqueResource {
	
	@Autowired
	private EstoqueService estoqueService;
	@Autowired
	private MateriaisService materiaisService;

	
	@RequestMapping( method = RequestMethod.GET  )
	public ResponseEntity<List<EstoqueDto>> listar() {
	
		List<EstoqueDto> listaDto =  EstoqueDto.converter(  estoqueService.listar());
		listaDto.forEach( estoque -> {						String unidadeSigla = estoqueService.getUnidade(estoque.getUnidadeId()).getSigla();
															estoque.setUnidadeSigla(unidadeSigla);
															Material material = materiaisService.buscar( estoque.getMaterialId()  );  
															estoque.setMaterialNome( material.getNome()	);
															estoque.setCodigoBarras( material.getCodigobarras() );
															estoque.setUnidadeSigla(unidadeSigla);
														}
									);
		return  ResponseEntity.status(HttpStatus.OK).body(  listaDto );
	}
	
	@RequestMapping(value = "/{unidadeId}", method = RequestMethod.GET)
	public ResponseEntity<List<EstoqueDto>> buscarEstoqueUnidade( @PathVariable("unidadeId") Long unidadeId) {
		List<Estoque> listaEstoqueUnidade =  estoqueService.buscaEstoqueUnidade(unidadeId);
		
		List<EstoqueDto> listaDto =  EstoqueDto.converter(  listaEstoqueUnidade );
		listaDto.forEach( estoque -> {						String unidadeSigla = estoqueService.getUnidade(estoque.getUnidadeId()).getSigla();
															estoque.setUnidadeSigla(unidadeSigla);
															Material material = materiaisService.buscar( estoque.getMaterialId()  );  
															estoque.setMaterialNome( material.getNome()	);
															estoque.setCodigoBarras( material.getCodigobarras() );
															estoque.setUnidadeSigla(unidadeSigla);
														}
									);
		
		
		return  ResponseEntity.status(HttpStatus.OK).body(listaDto);
	}
	
	@RequestMapping( method = RequestMethod.POST  )
	public ResponseEntity<?> salvar( @RequestBody Estoque estoque ) {
		Long material_id = estoque.getMaterialId();
		Long unidadeId = estoque.getUnidadeId();
		/** Lança exceção se material não existir , OBS : implementar p unidade tb **/
	    Material material =  materiaisService.buscar(material_id);
		/**OBS: Deve verificar se a unidade tb existe antes de salvar **/
		 estoqueService.verificarExistencia( estoque );
		estoque = estoqueService.salvar(estoque);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(estoque.getMaterialId()+estoque.getUnidadeId()).toUri();
		return ResponseEntity.created(uri).body(estoque);
	}
	
	@RequestMapping(value = "/{materialId}/{unidadeId}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("materialId") Long materialId, @PathVariable("unidadeId") Long unidadeId) {
		/** exception está sendo tratada no inteceptor ResourceExceptionHandler **/
		Estoque estoque =  estoqueService.buscar(materialId, unidadeId);
		return  ResponseEntity.status(HttpStatus.OK).body(estoque);
	}

	@RequestMapping(value= "/{materialId}/{unidade}", method=RequestMethod.DELETE) 
	public  ResponseEntity<Void> deletar(@PathVariable("materialId") Long materialId, @PathVariable("unidade") Long unidadeId) {
		/** exception está sendo tratada no inteceptor ResourceExceptionHandler **/
		estoqueService.deletar(materialId, unidadeId);
		return ResponseEntity.noContent().build();	
	}
	 
	@RequestMapping(value= "/{materialId}/{unidade}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Estoque estoque, @PathVariable("materialId") Long materialId, @PathVariable("unidade") Long unidadeId) {
		estoque.setMaterialId(materialId); // para garantir q o recurso q será atualizado é o do material id e da unidade
		estoque.setUnidadeId(unidadeId);
		/** exception está sendo tratada no inteceptor ResourceExceptionHandler **/
		estoqueService.atualizar(estoque);
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping( value = "/unidades" )
	public ResponseEntity<List <Unidade>> unidades(){
		List<Unidade> unidades = estoqueService.listarUnidades();
		return ResponseEntity.status(HttpStatus.OK).body(unidades);
	}
	
}