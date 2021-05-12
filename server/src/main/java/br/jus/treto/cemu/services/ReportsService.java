package br.jus.treto.cemu.services;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.jus.treto.cemu.domain.Guia;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterContext;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

//import net.sf.jasperreports.export.Exporter;
//import net.sf.jasperreports.export.SimpleExporterInput;
//import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
//import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
//import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportsService {

	@Autowired
	private GuiasService guiasService;
	
	public List<Guia> listar(){
		return guiasService.listar();
	}
	
   	public void generateReportGuia( Long id, Map<String, Object> parametros, String format, String  arquivoJxml, HttpServletResponse response  ) throws JRException, IOException {
		Guia guia = guiasService.buscar(id) ;
		List<Guia> list = new ArrayList<Guia>();
		list.add(guia);
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource( list );
        JasperPrint rJasperPrintRsult = generator( arquivoJxml, parametros, datasource );
        generateOut( rJasperPrintRsult, format, response );
    }
	
	public void generateReporGuias( Map<String, Object> parametros, String format, String  arquivoJxml, HttpServletResponse response  ) throws JRException, IOException {
		List<Guia> guias = guiasService.listar();
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource( guias );
        JasperPrint rJasperPrintRsult = generator( arquivoJxml, parametros, datasource );
        generateOut( rJasperPrintRsult, format, response );
    }

	private JasperPrint generator( String nomeArquivo, Map<String, Object> parameters, JRBeanCollectionDataSource dataSource) throws JRException, FileNotFoundException{
        // ler datasource json
		JRBeanCollectionDataSource datasource =dataSource;
		  File file = ResourceUtils.getFile(nomeArquivo );
        // Compile the Jasper report from .jrxml to .japser
        final JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
	    // Filling the report with the  data and additional parameters information.
		return JasperFillManager.fillReport( jasperReport,  parameters, datasource);	
    }

  private ByteArrayOutputStream  generateOut(JasperPrint rJasperPrintRsult, String reportFormat, HttpServletResponse response) throws IOException, JRException  {	  
	    String mineType;
	  	final String filePath = "\\";
		// exporta para o fromat desejado
//		if( reportFormat.equalsIgnoreCase("html") ) {
//			String mimeType = "text/html" ;
//			String arquivoNome = "reportGuias.html";
//			OutputStream outputStream = getOutputStream( response, mimeType, arquivoNome );
//		//	JasperExportManager.exportReportToHtmlFile(rJasperPrintRsult,  arquivoNome );
//		     JasperExportManager.exportReportToHtmlFile(rJasperPrintRsult,  "sample_report.html");
//		}
//		if( reportFormat.equalsIgnoreCase("pdf") ) {
//			String mimeType = "application/pdf" ;
//			String arquivoNome = "reportGuias.pdf";
//			OutputStream outputStream = getOutputStream( response, mimeType, arquivoNome );
//			JasperExportManager. exportReportToPdfStream(rJasperPrintRsult,outputStream);
//		}
		
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	    if( reportFormat.equalsIgnoreCase("pdf") ) {
	 			String mimeType = "application/pdf" ;
	 			String arquivoNome = "reportGuias.pdf";
	 			OutputStream outputStream = getOutputStream( response, mimeType, arquivoNome );
	    	
			    JRPdfExporter exporter = new JRPdfExporter();
			    exporter.setExporterInput(new SimpleExporterInput(rJasperPrintRsult));
			    exporter.setExporterOutput( new SimpleOutputStreamExporterOutput(outputStream));
		
			   SimplePdfReportConfiguration reportConfig= new SimplePdfReportConfiguration();
			   reportConfig.setSizePageToContent(true);
			   reportConfig.setForceLineBreakPolicy(false);
		
			   SimplePdfExporterConfiguration exportConfig= new SimplePdfExporterConfiguration();
			    exportConfig.setMetadataAuthor("baeldung");
			    exportConfig.setEncrypted(true);
			    exportConfig.setAllowedPermissionsHint("PRINTING");
		
			    exporter.setConfiguration(reportConfig);
			    exporter.setConfiguration(exportConfig);
		
				 try {
				   	exporter.exportReport();
				 } catch (JRException e) {
				     throw new RuntimeException(e);
				 }
		    
		}
	    	    
         if ( reportFormat.equalsIgnoreCase( "xls" ) ){
	  			String mimeType = "aapplication/vnd.ms-excel" ;
	  			String arquivoNome = "reportGuias.xls";
	  			OutputStream outputStream = getOutputStream( response, mimeType, arquivoNome );

        	   JRXlsxExporter exporter = new JRXlsxExporter(); // initialize exporter 
        	   exporter.setExporterInput(new SimpleExporterInput(rJasperPrintRsult)); // set compiled report as input
        	   exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream)); // set output file via path with filename
        	    SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
        	    config.setOnePagePerSheet(true); // setup configuration
        	    config.setDetectCellType(true);
        	    config.setIgnoreCellBackground(true);
        	    config.setIgnoreCellBorder(false);
        	    config.setWhitePageBackground(false);
        	    config.setWrapText(false);
        	    config.setRemoveEmptySpaceBetweenRows(true);
        	    config.setCollapseRowSpan(true);
        	    exporter.setConfiguration(config); // set configuration      	
        	    try {
        	    		exporter.exportReport();
        	    } catch (JRException e) {
        	        	throw new RuntimeException(e);
        	    }
        	         	 
        }
     	if ( reportFormat.equalsIgnoreCase( "html" ) ){
  			String mimeType = "text/htmll" ;
  			String arquivoNome = "reportGuias.html";
  			OutputStream outputStream = getOutputStream( response, mimeType, arquivoNome );
     		
        	 HtmlExporter exporter = new HtmlExporter();
        	 exporter.setExporterInput(new SimpleExporterInput(rJasperPrintRsult)); // set compiled report as input
         	 // Set input ...
         	// exporter.setExporterOutput( new SimpleHtmlExporterOutput("employeeReport.html"));  // set output file via path with filename
         	 exporter.setExporterOutput( new SimpleHtmlExporterOutput(outputStream));  // set output file via path with filename
    	     try {
    	    		exporter.exportReport();
    	     } catch (JRException e) {
	        		throw new RuntimeException(e);
    	     }
	      		
        }
         
         return byteArrayOutputStream;
         		
	}
     
	private OutputStream  getOutputStream( HttpServletResponse response, String type, String arquivoNome  ) throws IOException {
		response.setContentType(type);
		response.setHeader("Content-disposition", "inline; filename="+arquivoNome);
		OutputStream outputStream = response.getOutputStream();
		return outputStream;
	}

}
