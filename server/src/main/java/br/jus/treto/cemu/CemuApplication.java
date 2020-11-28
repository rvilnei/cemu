package br.jus.treto.cemu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport  // Habilita Pagenable
public class CemuApplication   extends SpringBootServletInitializer {  // tomcat provided  - .war

	public static void main(String[] args) {
		SpringApplication.run(CemuApplication.class, args);
	}
	
	@Override  // tomcat provided - .war
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CemuApplication.class);
	}

}
