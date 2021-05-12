package br.jus.treto.cemu.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.treto.cemu.domain.Guia;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.resources.dto.GuiaReportDto;
import br.jus.treto.cemu.resources.dto.MaterialDto;
import br.jus.treto.cemu.resources.dto.MovimentacaoDto;
import br.jus.treto.cemu.services.GuiasService;
import br.jus.treto.cemu.services.ReportsService;
import net.sf.jasperreports.engine.JRException;

@CrossOrigin
@RestController
@RequestMapping( value = "/reports" )
public class ReportsResouce {

	@Autowired
	private ReportsService reportsService;
	
	@Autowired
	private GuiasService guiasService;
	
	@GetMapping
	public ResponseEntity<List<GuiaReportDto>> listar() {
		List<Guia> guias = reportsService.listar();
		List<GuiaReportDto> listGuiaReportDto =  GuiaReportDto.converter( guias, reportsService );
    	listGuiaReportDto = atribuirUnidades( listGuiaReportDto );
		 return ResponseEntity.status(HttpStatus.OK).body( listGuiaReportDto );
	}
	
	
	@GetMapping("/{format}")		
	public void generateReportGuias( HttpServletResponse response, @PathVariable String format ) throws JRException, IOException {
					String arquivoJxml = "classpath:reports/MyReports/guias.jrxml"; 
         			Map<String, Object> parametros = parametroInicial();
         		//	String (startDate, endDate) = params.dt_inicio_dt_fim.tokenize( '-' )
        	        parametros.put( "date", new java.util.Date() );
//        	        parametros.put( "dataInicial", new SimpleDateFormat('dd/MM/yyyy').parse(startDate) );
//        	        parametros.put( "dataFinal", new SimpleDateFormat('dd/MM/yyyy').parse(endDate) );     
        	        reportsService.generateReporGuias(parametros, format, arquivoJxml ,response);
	}
	
	@GetMapping("/{id}/{format}")		
	public void generateReportGuia(  @PathVariable Long id, @PathVariable String format, HttpServletResponse response  ) throws JRException, IOException {
					String arquivoJxml = "classpath:reports/MyReports/guias.jrxml"; 
         			Map<String, Object> parametros = parametroInicial();
         		//	String (startDate, endDate) = params.dt_inicio_dt_fim.tokenize( '-' )
        	        parametros.put( "date", new java.util.Date() );
//        	        parametros.put( "dataInicial", new SimpleDateFormat('dd/MM/yyyy').parse(startDate) );
//        	        parametros.put( "dataFinal", new SimpleDateFormat('dd/MM/yyyy').parse(endDate) );     
        	      //  OutputStream outputStream  = getOutputStream(response  );
        	        reportsService.generateReportGuia( id, parametros, format,  arquivoJxml, response);
	}
	
	
	
//	@RequestMapping( value = "{unidadeId}/saida", method = RequestMethod.GET, produces = "application/json"  )
//	public ResponseEntity<List<MovimentacaoDto>> listarSaida(  @PathVariable("unidadeId") Long unidadeId  ) {
//	
//		List<MovimentacaoDto> listMovimentacaoaDto =  MovimentacaoDto.converter( movimentacoesService.listarSaida( unidadeId ) );
//		listMovimentacaoaDto = atribuirUnidades( listMovimentacaoaDto );
//		return  ResponseEntity.status(HttpStatus.OK).body(  listMovimentacaoaDto );
//	}
	
	private List<GuiaReportDto> atribuirUnidades( List<GuiaReportDto> guiasReportDto ){
		guiasReportDto.forEach( guaReportDto ->
										{	
										     unidadeDescricao(guaReportDto);
										}
									);
		return  guiasReportDto;

	}
	
	private GuiaReportDto unidadeDescricao( GuiaReportDto guiaReportDto ) {
		String unidadeOrigem = guiasService.getUnidade(guiaReportDto.getUnidadeorigemId() ).getSigla();
		String unidadeDestino = guiasService.getUnidade(guiaReportDto.getUnidadedestinoId() ).getSigla();
		guiaReportDto.setUnidadeorigem(unidadeOrigem);
		guiaReportDto.setUnidadedestino(unidadeDestino);
		
		return guiaReportDto;
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
