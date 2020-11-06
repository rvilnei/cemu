package br.jus.treto.cemu.services.exceptions;

public class MaterialNaoEncontradoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3439810099183208048L;

	public MaterialNaoEncontradoException( String mensagem ) {
		super(mensagem);
	}

	public MaterialNaoEncontradoException( String mensagem, Throwable causa ) {
		super(mensagem, causa);
	}
		
}
