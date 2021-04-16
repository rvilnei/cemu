package br.jus.treto.cemu.services;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.jus.treto.cemu.domain.Guia;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

@Service
public class ReportsService {

	@Autowired
	private GuiasService guiasService;
	
	public String exportReport( String reportFormat )  throws FileNotFoundException, JRException {
		String path = "path";
		List<Guia> guias = guiasService.listar();
		//Ler e compila o arquivo
		File file =  ResourceUtils.getFile( "classpath:data/guias.jxlm");
		JasperReport jasperReport = JasperCompileManager.compileReport( file.getAbsolutePath() ) ;
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource( guias );
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "TRE-TO/STI/SEDSA");
		JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport,  parameters, datasource);

		if( reportFormat.equalsIgnoreCase("html") ) {
			JasperExportManager.exportReportToHtmlFile( jasperPrint, path+"destFileName " );
		}
		if( reportFormat.equalsIgnoreCase("pdf") ) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path+"destFileName" );
		}
		
		return "report genereted in path: "+path;
	}
	
}
