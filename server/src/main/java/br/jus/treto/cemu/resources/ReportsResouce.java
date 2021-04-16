package br.jus.treto.cemu.resources;

import java.io.FileNotFoundException;

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
@RequestMapping(value = "/reports")
public class ReportsResouce {

	@Autowired
	private ReportsService reportsService;
	
     @GetMapping( "/guias/{fromat}" )	
	public String generateReportGuias( @PathVariable String format ) throws FileNotFoundException, JRException {
		return reportsService.exportReport(format);
	}
	
}
