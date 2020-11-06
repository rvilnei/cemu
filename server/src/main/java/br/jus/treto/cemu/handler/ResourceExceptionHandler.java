package br.jus.treto.cemu.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.jus.treto.cemu.domain.DetalhesErro;
import br.jus.treto.cemu.services.exceptions.GenericsException;
import br.jus.treto.cemu.services.exceptions.MaterialNaoEncontradoException;

@ControllerAdvice // intercepta toda exception do código
public class ResourceExceptionHandler {

	@ExceptionHandler(MaterialNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleMaterialNaoEncontradoException
							(MaterialNaoEncontradoException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
	//	erro.setTitulo("O material não foi encontrado !");
		erro.setTitulo(e.getMessage());
		erro.setMensagemdesenvolvedor("http://erros.tre-to.jus.br/404"); // uma pagina informando sobre os erros
		erro.setTimestemp(System.currentTimeMillis());
		
		// return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	@ExceptionHandler(GenericsException.class)
	public ResponseEntity<DetalhesErro> handleGenericsException
							(GenericsException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
	//	erro.setTitulo("O material não foi encontrado !");
		erro.setTitulo(e.getMessage());
		erro.setMensagemdesenvolvedor("http://erros.tre-to.jus.br/404"); // uma pagina informando sobre os erros
		erro.setTimestemp(System.currentTimeMillis());
		
		// return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
}
