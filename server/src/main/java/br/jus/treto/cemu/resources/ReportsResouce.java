package br.jus.treto.cemu.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.treto.cemu.domain.Estoque;
import br.jus.treto.cemu.domain.Guia;
import br.jus.treto.cemu.domain.ItemMovimentacao;
import br.jus.treto.cemu.domain.Material;
import br.jus.treto.cemu.resources.dto.EstoqueDto;
import br.jus.treto.cemu.resources.dto.EstoqueReportDto;
import br.jus.treto.cemu.resources.dto.GuiaReportDto;
import br.jus.treto.cemu.resources.dto.MaterialReportDto;
import br.jus.treto.cemu.services.EstoqueService;
import br.jus.treto.cemu.services.GuiasService;
import br.jus.treto.cemu.services.MateriaisService;
import br.jus.treto.cemu.services.ReportsService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@CrossOrigin
@RestController
@RequestMapping( value = "/reports" )
public class ReportsResouce {

	@Autowired
	private ReportsService reportsService;
	
	@Autowired
	private GuiasService guiasService;
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Autowired
	private MateriaisService materiaisService;
	
	private String REPORTS_PATH = "classpath:reports/MyReports/";
	
	@GetMapping
	public ResponseEntity<List<GuiaReportDto>> listar() {
		List<Guia> guias = reportsService.listar();
		List<GuiaReportDto> listGuiaReportDto =  GuiaReportDto.converter( guias, guiasService );
		 return ResponseEntity.status(HttpStatus.OK).body( listGuiaReportDto );
	}
	
	@GetMapping("/itens/{format}")		
	public void generateReportItens( HttpServletResponse response, @PathVariable String format ) throws JRException, IOException {
				Guia guia = guiasService.buscar(1L) ;
				List<ItemMovimentacao> itens =  guia.getMovimentacao().getItens();				
				List<ItemMovimentacao> listItemMovimentacao = new ArrayList<ItemMovimentacao>();
				listItemMovimentacao.addAll( itens  );
				String arquivoJxml = "classpath:reports/MyReports/itensMovimentacao.jrxml"; 
     			Map<String, Object> parametros = parametroInicial();
     			parametros.put("SUBREPORT_DATA", listItemMovimentacao );

    	        reportsService.generateReporItemMovimentacao( listItemMovimentacao, parametros, format, arquivoJxml ,response);
	}

	
	@GetMapping("/guias/{format}")		
	public void imprimeGuias( HttpServletResponse response, @PathVariable String format ) throws JRException, IOException {
					List<Guia> listGuias = reportsService.listar();		
					gerarImpressaoGuia(  listGuias, format,  guiasService, response );
	}
	
	//GERA GUIAS//
	@GetMapping("/guias/{id}/{format}")		
	public void imprimeGuia( @PathVariable Long id, @PathVariable String format, HttpServletResponse response  ) throws JRException, IOException{
					Guia guia = guiasService.buscar(id) ;			
					List<Guia> listGuia = new ArrayList<Guia>();
					listGuia.add(guia);
					gerarImpressaoGuia(  listGuia, format,  guiasService, response );
	}
	
	private void gerarImpressaoGuia( List<Guia> listGuia, String format, GuiasService guiasService, HttpServletResponse response  ) throws JRException, IOException {	
					List<GuiaReportDto> listGuiaReportDto =  GuiaReportDto.converter( listGuia, guiasService );
					
			    	String subReportJxml = REPORTS_PATH+ "itensMovimentacao.jrxml"; 
					File fileSubReportile = ResourceUtils.getFile(subReportJxml );
					//JasperCompileManager.compileReportToFile(fileSubReportile.getPath());
					JasperReport jasperReportCompiled = JasperCompileManager.compileReport(fileSubReportile.getPath());
					
					String nomeArquivoJxml = REPORTS_PATH+"guia.jrxml"; 
         			Map<String, Object> parametros = parametroInicial();
        	        parametros.put( "date", new java.util.Date() );
//        	        parametros.put( "dataInicial", new SimpleDateFormat('dd/MM/yyyy').parse(startDate) );
//        	        parametros.put( "dataFinal", new SimpleDateFormat('dd/MM/yyyy').parse(endDate) );     
        	        parametros.put("REPORTS_JASPER_PATH", REPORTS_PATH);
        	        parametros.put("REPORTS_COMPILED_FILE_JASPER", jasperReportCompiled);
        	        reportsService.generateReportGuia( listGuiaReportDto, parametros, format,  nomeArquivoJxml, response);
	}

	//GERA ESTOQUES//
	@GetMapping("/estoques/{format}")		
	public void imprimeEstoques( @PathVariable String format, HttpServletResponse response  ) throws JRException, IOException{
		List<Estoque> listaEstoque = estoqueService.listar();
		gerarImpressaoEstoque(  listaEstoque, format,  guiasService, response );
	}
	
      private void gerarImpressaoEstoque(List<Estoque> listaEstoque, String format,
			GuiasService guiasService, HttpServletResponse response) throws JRException, IOException {
    	  	List<EstoqueReportDto> listaEstoqueReportDto =  EstoqueReportDto.converter(  listaEstoque, materiaisService );
	    	String nomeArquivoJxml = REPORTS_PATH+"estoque.jrxml"; 
			File fileSubReportile = ResourceUtils.getFile(nomeArquivoJxml );
			//JasperCompileManager.compileReportToFile(fileSubReportile.getPath());
			JasperReport jasperReportCompiled = JasperCompileManager.compileReport(fileSubReportile.getPath());
 			Map<String, Object> parametros = parametroInicial();
	        parametros.put( "date", new java.util.Date() );    
	        parametros.put("REPORTS_JASPER_PATH", REPORTS_PATH);
	        parametros.put("REPORTS_COMPILED_FILE_JASPER", jasperReportCompiled);
	        reportsService.generateReportEstoque( listaEstoqueReportDto, parametros, format,  nomeArquivoJxml, response);
	}

  	//GERA MATERIAIS//
  	@GetMapping("/materiais/{format}")		
  	public void imprimeMateriais( @PathVariable String format, HttpServletResponse response  ) throws JRException, IOException{
  		List<Material> listaMateriais = materiaisService.listar();
  		gerarImpressaoMateriais(  listaMateriais, format,  materiaisService, response );
  	}
  	
        private void gerarImpressaoMateriais(List<Material> listaMaterial, String format,
        	MateriaisService materiaisService, HttpServletResponse response) throws JRException, IOException {
      	  	List<MaterialReportDto> listaMaterialReportDto =  MaterialReportDto.converter(  listaMaterial, materiaisService );
  	    	String nomeArquivoJxml = REPORTS_PATH+"materiais.jrxml"; 
  			File fileSubReportile = ResourceUtils.getFile(nomeArquivoJxml );
  			//JasperCompileManager.compileReportToFile(fileSubReportile.getPath());
  			JasperReport jasperReportCompiled = JasperCompileManager.compileReport(fileSubReportile.getPath());
   			Map<String, Object> parametros = parametroInicial();
  	        parametros.put( "date", new java.util.Date() );    
  	        parametros.put("REPORTS_JASPER_PATH", REPORTS_PATH);
  	        parametros.put("REPORTS_COMPILED_FILE_JASPER", jasperReportCompiled);
  	        reportsService.generateReportMaterial( listaMaterialReportDto, parametros, format,  nomeArquivoJxml, response);
  	}
      
	// Parametros iniciais para o relat[orios
     private Map<String, Object> parametroInicial(){
         	Map<String, Object> parametros = new HashMap<String, Object>();
         	//    parametros.put( "login", session?.login );
         	parametros.put( "nomeTribunal", "Tribunal Regional Eleitora do Tocantins" );
         	parametros.put( "nomeSecretariaTribunal", "Secretaria de Tecnologia da Informaçäo" );
         	parametros.put( "pathBrasao",    REPORTS_PATH+"brasao.gif"  );
         return parametros;
     }
      
}