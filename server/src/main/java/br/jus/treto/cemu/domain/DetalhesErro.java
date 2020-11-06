package br.jus.treto.cemu.domain;

public class DetalhesErro {
	private String titulo;
	private Long status;
	private Long timestemp;
	private String mensagemdesenvolvedor;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Long getTimestemp() {
		return timestemp;
	}
	public void setTimestemp(Long timestemp) {
		this.timestemp = timestemp;
	}
	public String getMensagemdesenvolvedor() {
		return mensagemdesenvolvedor;
	}
	public void setMensagemdesenvolvedor(String mensagemdesenvolvedor) {
		this.mensagemdesenvolvedor = mensagemdesenvolvedor;
	}
}
