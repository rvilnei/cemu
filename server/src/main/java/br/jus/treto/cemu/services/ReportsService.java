package br.jus.treto.cemu.services;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
	
    @SuppressWarnings("unused")
	public void generateRepost( String nomeArquivo, Map<String, Object> parametros, String reportFormat, OutputStream outputStream ) throws JRException, FileNotFoundException
    {
        JasperPrint rJasperPrintRsult = generator( nomeArquivo, parametros );
        generateOut( rJasperPrintRsult, reportFormat, outputStream );
    }
	
	private JasperPrint generator( String nomeArquivo, Map<String, Object> parameters) throws JRException, FileNotFoundException
    {
        String  dotJasperFileName;
		List<Guia> guias = guiasService.listar();
        // ler response json
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource( guias );
         // Fetching the .jrxml file from the resources folder.
        //final InputStream jxmlStream = this.getClass().getResourceAsStream("classpath:reports/guias.jrxml");
		  File file = ResourceUtils.getFile( "classpath:reports/guias.jxml" );
        // Compile the Jasper report from .jrxml to .japser
       // final JasperReport jasperReport = JasperCompileManager.compileReport(jxmlStream);
        final JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
	    // Filling the report with the employee data and additional parameters information.
		return JasperFillManager.fillReport( jasperReport,  parameters, datasource);	
    }

  private void generateOut(JasperPrint rJasperPrintRsult, String reportFormat, OutputStream outputStream) throws JRException {
	  

	  	final String filePath = "\\";
	  	System.out.println( " ***** generateOut " );
		// exporta para o fromat desejado
		if( reportFormat.equalsIgnoreCase("html") ) {
			JasperExportManager.exportReportToHtmlFile( rJasperPrintRsult, filePath+"destFileName" );
		}
		if( reportFormat.equalsIgnoreCase("pdf") ) {
			System.out.println( "****pdf**" );
		//	JasperExportManager.exportReportToPdfFile(rJasperPrintRsult, "\\"+"guias.pdf" );
			JasperExportManager.exportReportToPdfStream(rJasperPrintRsult, outputStream);
			
		}
		
	}
    
    
    
    
    
//      
//	public String exportReport( String reportFormat, HttpServletResponse response )  throws FileNotFoundException, JRException {
//		String path = "path";
//		List<Guia> guias = guiasService.listar();
//		
////		//Ler e compila o arquivo
////		File file =  ResourceUtils.getFile( "classpath:data/guias.jxlm");
//		
//        // Fetching the .jrxml file from the resources folder.
//        final InputStream jxmlStream = this.getClass().getResourceAsStream("/relatorios/livros.jasper");
//		
//        // Compile the Jasper report from .jrxml to .japser
//        final JasperReport jasperReport = JasperCompileManager.compileReport(jxmlStream);
// 		
//        // ler response json
//		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource( guias );
//		
//	    // Adding the additional parameters to the pdf.
//		final Map<String, Object> parameters = new HashMap<>();
//		parameters.put("createdBy", "TRE-TO/STI/SEDSA");
//		
//		// Configura a respota para o tipo PDF
//		response.setContentType("application/pdf");
//		// Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
//		// para fazer download do relatório troque 'inline' por 'attachment'
//		response.setHeader("Content-Disposition", "inline; filename=livros.pdf");
//
////		// Faz a exportação do relatório para o HttpServletResponse
////		final OutputStream outStream = response.getOutputStream();
////		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
////		
//		 // Filling the report with the employee data and additional parameters information.
//		final JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport,  parameters, datasource);
//		
//		// exporta para o fromat desejado
//		if( reportFormat.equalsIgnoreCase("html") ) {
//			JasperExportManager.exportReportToHtmlFile( jasperPrint, path+"destFileName " );
//		}
//		if( reportFormat.equalsIgnoreCase("pdf") ) {
//			JasperExportManager.exportReportToPdfFile(jasperPrint, path+"destFileName" );
//		}
//			
//        // Users can change as per their project requrirements or can take it as request input requirement.
//        // For simplicity, this tutorial will automatically place the file under the "c:" drive.
//        // If users want to download the pdf file on the browser, then they need to use the "Content-Disposition" technique.
//        final String filePath = "\\";
//        // Export the report to a PDF file.
//        JasperExportManager.exportReportToPdfFile(jasperPrint, filePath + "Employee_report.pdf");
// 
//		return "report genereted in path: "+path;
//	}
	
	
}
