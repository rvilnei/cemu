package br.jus.treto.cemu.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.jus.treto.cemu.domain.Estoque;
import br.jus.treto.cemu.domain.Lancamento;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.domain.Status;
import br.jus.treto.cemu.domain.Tipo;
import br.jus.treto.cemu.domain.Unidade;
import br.jus.treto.cemu.repository.LancamentosRepository;
import br.jus.treto.cemu.repository.MateriaisRepository;
import br.jus.treto.cemu.repository.StatusRepository;
import br.jus.treto.cemu.repository.TipoRepository;
import br.jus.treto.cemu.repository.UnidadesRepository;
import br.jus.treto.cemu.services.exceptions.MaterialNaoEncontradoException;

@Service
public class MateriaisService {
	@Autowired
	private EstoqueService estoqueService;
	@Autowired
	private MateriaisRepository materiaisRepository ;
	@Autowired
	private LancamentosRepository lancamentoRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private TipoRepository tipoRepository;
	@Autowired
	UnidadesRepository unidadesRepository;
	
	public List<Material> listar(){
		return materiaisRepository.findAll();
	}
	
	public Material buscar(Long id) {
		Material material = materiaisRepository.findById(id).orElse(null);
		if(material == null) {
			throw new MaterialNaoEncontradoException("O material não foi encontrado");
		}
		return material;
	}
	
	public Material salvar(Material material) {
		material.setId(null);
		return materiaisRepository.save(material);
	}
	
	public void delete( Long id ) {
		try {
			materiaisRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new MaterialNaoEncontradoException("O Material não foi encontrado");
		}
	}
	
	public void atualizar(Material material) {
		verificarExistencia(material);
		materiaisRepository.save(material);
	}
	
	private void verificarExistencia(Material material) {
		buscar(material.getId());
	}
	
	public Lancamento salvaLancamento( Long materialId, Lancamento lancamento ) {
		Material material = buscar(materialId);
		lancamento.setMaterial(material);
		lancamento.setData( LocalDate.now()  );
		System.out.println( "unid ID"+lancamento.getUnidadeorigemId() );
		salvarEstoque(material.getId(), lancamento.getUnidadeorigemId() , lancamento.getQuantidade() );
		return lancamentoRepository.save(lancamento);
	}
	
	public void salvarEstoque(Long materialId, Long unidadeId, Integer qtd){
		estoqueService.atualizarEstoque(materialId, unidadeId, qtd);
	}
	
	public List<Lancamento> listarLancamentos( Long materialId){
		Material material = buscar(materialId);
		return material.getLancamentos();
	}
	
	public List<Tipo> listarTipos(){
		return tipoRepository.findAll();
	}
	
	public List<Status> listarStatus(){
		return statusRepository.findAll();
	}
	
	public Unidade getUnidade( Long id ) {
		return unidadesRepository.findById(id).orElse(null);
	}

	public Tipo buscarTipo(Long id) {
		Tipo tipo = tipoRepository.findById(id).orElse(null);
		if(tipo == null) {
			throw new MaterialNaoEncontradoException("O tipo não foi encontrado");
		}
		return tipo;
	}
	
	public Status buscarStatus(Long id) {
		Status status = statusRepository.findById(id).orElse(null);
		if(status == null) {
			throw new MaterialNaoEncontradoException("O status não foi encontrado");
		}
		return status;
	}
	
}
