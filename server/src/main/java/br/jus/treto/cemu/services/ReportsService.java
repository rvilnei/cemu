package br.jus.treto.cemu.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import br.jus.treto.cemu.domain.Guia;
import br.jus.treto.cemu.domain.ItemMovimentacao;
import br.jus.treto.cemu.resources.dto.EstoqueReportDto;
import br.jus.treto.cemu.resources.dto.GuiaReportDto;
import br.jus.treto.cemu.resources.dto.MaterialReportDto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportsService {

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private GuiasService guiasService;
	
	public List<Guia> listar(){
		return guiasService.listar();
	}
	
   	public void generateReportGuia(  List<GuiaReportDto> guiasReportDto,Map<String, Object> parametros, String format, String  arquivoJxml, HttpServletResponse response  ) throws JRException, IOException {
		List<GuiaReportDto> listGuiaReportDto = guiasReportDto;
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource( listGuiaReportDto );
        JasperPrint rJasperPrintRsult = generator( arquivoJxml, parametros, datasource );
        generateOut( rJasperPrintRsult, format, response );
    }
	
	public void generateReporGuias( List<GuiaReportDto> guiasReportDto, Map<String, Object> parametros, String format, String  arquivoJxml, HttpServletResponse response  ) throws JRException, IOException {
		List<GuiaReportDto> listGuiaReportDto = guiasReportDto;
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource( listGuiaReportDto );
        JasperPrint rJasperPrintRsult = generator( arquivoJxml, parametros, datasource );
        generateOut( rJasperPrintRsult, format, response );
    }

   	public void generateReportEstoque(  List<EstoqueReportDto> estoqueReportDto,Map<String, Object> parametros, String format, String  arquivoJxml, HttpServletResponse response  ) throws JRException, IOException {
		List<EstoqueReportDto> listestoqueReportDto = estoqueReportDto;
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource( listestoqueReportDto );
        JasperPrint rJasperPrintRsult = generator( arquivoJxml, parametros, datasource );
        generateOut( rJasperPrintRsult, format, response );
    }
	
	public void generateReportMaterial(List<MaterialReportDto> lmaterialReportDto, Map<String, Object> parametros,
			String format, String nomeArquivoJxml, HttpServletResponse response) throws JRException, IOException {
			List<MaterialReportDto> listaMaterialReportDto = lmaterialReportDto;
			JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listaMaterialReportDto  );
			JasperPrint jasperPrintResul = generator( nomeArquivoJxml, parametros, datasource );
			generateOut( jasperPrintResul, format, response );
	}
   	
	private JasperPrint generator( String nomeArquivo, Map<String, Object> parameters, JRBeanCollectionDataSource dataSource) throws JRException, IOException{
        // ler datasource json
		JRBeanCollectionDataSource datasource =dataSource;
		// File file = ResourceUtils.getFile(nomeArquivo );
		  
        Resource resource = resourceLoader.getResource(nomeArquivo);
        InputStream fileInputStream = resource.getInputStream();
		  
        // Compile the Jasper report from .jrxml to .japser
        // final JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
        final JasperReport jasperReport = JasperCompileManager.compileReport(fileInputStream);
        
	    // Filling the report with the  data and additional parameters information.
		return JasperFillManager.fillReport( jasperReport,  parameters, datasource);	
    }
 
  private ByteArrayOutputStream  generateOut(JasperPrint rJasperPrintRsult, String reportFormat, HttpServletResponse response) throws IOException, JRException  {	  
	    String mineType;
	  	final String filePath = "\\";
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	    if( reportFormat.equalsIgnoreCase("pdf") ) {
	 			String mimeType = "application/pdf" ;
	 			String arquivoNome = "reportGuias.pdf";
	 			OutputStream outputStream = getOutputStream( response, mimeType, arquivoNome );
			    JRPdfExporter exporter = new JRPdfExporter();
			    exporter.setExporterInput(new SimpleExporterInput(rJasperPrintRsult));
		//     exporter.setExporterOutput( new SimpleOutputStreamExporterOutput(outputStream));
			    exporter.setExporterOutput( new SimpleOutputStreamExporterOutput(response.getOutputStream()));
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
		response.setHeader("Content-Disposition", "inline;filename="+arquivoNome);
//		response.setHeader("Content-Disposition", "attachment;filename=somefile.ext");
		OutputStream outputStream = response.getOutputStream();
		return outputStream;
	}

	public void generateReporItemMovimentacao(List<ItemMovimentacao> listItemMovimentacao,
			Map<String, Object> parametros, String format, String arquivoJxml, HttpServletResponse response) throws IOException, JRException {
			JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource( listItemMovimentacao );
	        JasperPrint rJasperPrintRsult = generator( arquivoJxml, parametros, datasource );
	        generateOut( rJasperPrintRsult, format, response );
	}

}
