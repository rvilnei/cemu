package br.jus.treto.cemu.services.exceptions;

public class GenericsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3439810099183208048L;

	public GenericsException( String mensagem ) {
		super(mensagem);
	}

	public GenericsException( String mensagem, Throwable causa ) {
		super(mensagem, causa);
	}
		
}
