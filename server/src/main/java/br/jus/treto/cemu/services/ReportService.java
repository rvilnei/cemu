package br.jus.treto.cemu.services;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.jus.treto.cemu.domain.Guia;

@Service
public class ReportService {

	@Autowired
	private GuiasService guiasService;
	
	public String exportReport( String reportFormat )  throws FileNotFoundException {
		List<Guia> guias = guiasService.listar();
		//Ler e compila o arquivo
		File file =  ResourceUtils.getFile( "classpath:data/guias.jxlm");
	//	JasperReport jasperReport = JasperCompileManager.compileReport();
		return null;
	}
	
}
