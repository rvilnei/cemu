package br.jus.treto.cemu.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.treto.cemu.services.ReportsService;
import net.sf.jasperreports.engine.JRException;

@CrossOrigin
@RestController
@RequestMapping( value = "/reports" )
public class ReportsResouce {

	@Autowired
	private ReportsService reportsService;
	
	@GetMapping("/{reportFormat}")		
	public void generateReportGuias( HttpServletResponse response, @PathVariable String reportFormat ) throws JRException, IOException {
         			String nomeArquivoJR = "diarias_por_servidor_periodo_sgp";
         	//		String extensaoArquivo = " params.extensao";
         			Map<String, Object> parametros = parametroInicial();
         		//	String (startDate, endDate) = params.dt_inicio_dt_fim.tokenize( '-' )
        	        parametros.put( "date", new java.util.Date() );
//        	        parametros.put( "dataInicial", new SimpleDateFormat('dd/MM/yyyy').parse(startDate) );
//        	        parametros.put( "dataFinal", new SimpleDateFormat('dd/MM/yyyy').parse(endDate) );
//        	        generate( nomeArquivoJR, extensaoArquivo, parametros )
        	        
        	        OutputStream outputStream  = getOutStream(response  );
        	        reportsService.generateRepost(nomeArquivoJR, parametros, reportFormat, outputStream);
        	        
	}
	
	private OutputStream  getOutStream( HttpServletResponse response ) throws IOException {
		response.setContentType("aplication/x-download");
		response.setHeader("Content-Disposition", String.format( "attachment ; filename=\"guiaspdf.pdf\"")   );
		OutputStream outputStream = response.getOutputStream() ;
		return outputStream;
	}
	
      // Parametros iniciais para o relat[orios
     private Map<String, Object> parametroInicial(){

         Map<String, Object> parametros = new HashMap<String, Object>();
     //    parametros.put( "login", session?.login );
         parametros.put( "nomeTribunal", "Tribunal Regional Eleitoral" );
         parametros.put( "nomeSecretariaTribunal", "Secretaria de Tecnologia da Informaçäo" );
       // parametros.put( "pathBrasao",   grailsApplication.mainContext.getResource('MyReports/brasao.gif').file.getPath()   );
         return parametros;
     }
     
     
}
