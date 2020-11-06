package br.jus.treto.cemu.services;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.jus.treto.cemu.domain.Guia;
import br.jus.treto.cemu.domain.Movimentacao;
import br.jus.treto.cemu.repository.GuiasRespository;
import br.jus.treto.cemu.services.exceptions.MaterialNaoEncontradoException;

@Service
public class GuiasService {
	
	@Autowired
	private GuiasRespository guiasRespository;
	
	public List<Guia> listar(){
		return guiasRespository.findAll();
	}

	public Guia salvar(Guia guia) {
		return guiasRespository.save(guia);
	}

	public Guia buscar(Long id) {
		Guia guia = guiasRespository.findById(id).orElse(null);
		if(guia == null) {
			throw new MaterialNaoEncontradoException("A guia não foi encontrado");
		}
		return guia;
	}

	public String getNumeroGuia() {
		Guia ultimaGuia = new Guia();

		Long id = guiasRespository.maxIdGuia();
		if(id != null  ) {
			ultimaGuia = guiasRespository.getOne(id);
		} else { // qdo ainda nenhuma Guia existir no banco
			Integer anoAtual = new Integer( LocalDateTime.now().getYear() ); 
			String ano = anoAtual.toString().concat( "0000" );
			ultimaGuia.setNumeroGuia(ano);
		}
		String numeroGerado = gerarNumero( ultimaGuia.getNumeroGuia());
		return numeroGerado;
	}
	
	private String gerarNumero( String maiorNumero ){
		Integer anoUltimaGuia =  new Integer( maiorNumero.substring(0, 4));
		Integer numeroUltimaGuia =  new Integer( maiorNumero.substring(4, 8));		
		Integer anoAtual = new Integer( LocalDateTime.now().getYear() ); 
		if( anoUltimaGuia < anoAtual ) {
			return anoAtual.toString().concat( "0001" );
		} else if( anoUltimaGuia.equals(anoAtual)  ) {
			numeroUltimaGuia = numeroUltimaGuia+1;
			String ano = anoUltimaGuia.toString();
			String numero = StringUtils.leftPad( numeroUltimaGuia.toString(), 4, "0");
			return ano.concat(numero);
		}  else {
			return "00000000";
		}
	}
	
	public void atualizar(Guia guia) {
		//verificarExistencia(material);
		guiasRespository.save(guia);
		
	}

	public void delete( Long id ) {
		Guia guia = buscar( id );
		Movimentacao movimentacao = guia.getMovimentacao();
		movimentacao.setGuia(null);
		guia.setMovimentacao(null);
		atualizar(guia);
		try {
			guiasRespository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new MaterialNaoEncontradoException("O guia não foi encontrado");
		}
	}
	
}
