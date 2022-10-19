package br.jus.treto.cemu.config.security.autorizador;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.jus.treto.cemu.config.security.autorizador.util.Base64Util;
import br.jus.treto.cemu.config.security.autorizador.util.JsonUtil;
import br.jus.treto.cemu.domain.Unidade;
import br.jus.treto.cemu.repository.UnidadesRepository;
import br.jus.treto.cemu.resources.dto.AutorizadorUsuario;


/*
import br.jus.treto.userman.seguranca.dto.AutorizadorUsuario;
import br.jus.treto.userman.service.ConfiguracaoService;
import br.jus.treto.userman.util.Base64Util;
import br.jus.treto.userman.util.JsonUtil;
*/

@Component
public class AutenticacaoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ConfiguracaoService configService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final UnidadesRepository unidadesRepository;
    
    // public static final String AUTORIZADOR_URL = "userman.autorizador.url";
    // public static final String AUTORIZADOR_CHAVE_SISTEMA = "userman.autorizador.chave-sistema";

     public static final String AUTORIZADOR_URL = "https://autorizador.tre-to.jus.br/v2/autenticar/";
     public static final String AUTORIZADOR_CHAVE_SISTEMA = "SDU";    
    
    @Autowired
    public AutenticacaoService(ConfiguracaoService configService, UnidadesRepository unidadesRepository) {
        this.configService = configService;
		this.unidadesRepository = unidadesRepository;
    }

    public AutorizadorUsuario autenticar(final String login, final String senha) {
     //   final String autorizadorUrl = configService.getString(AUTORIZADOR_URL);
     //   final String chaveSistema = configService.getString(AUTORIZADOR_CHAVE_SISTEMA);

        final String autorizadorUrl = AUTORIZADOR_URL;
        final String chaveSistema = AUTORIZADOR_CHAVE_SISTEMA;
        
        
        final HttpEntity<MultiValueMap<String, String>> requestEntity = criarEntidadeReq(login, senha, chaveSistema);
        try {
            logger.debug("Chamando Autorizador => POST {} => login: {}, sistema: {}", autorizadorUrl, login,
                    chaveSistema);

            final AutorizadorUsuario usuario = restTemplate.postForObject(autorizadorUrl, requestEntity,
                    AutorizadorUsuario.class);

            if (logger.isDebugEnabled()) {
                logger.debug("Autenticação Ok => {}", JsonUtil.serialize(usuario));
            }

            if (usuario == null) {
                return null;
            }
            usuario.setLogin(login);
            return usuario;
        } catch (RuntimeException ex) {
            if (ex instanceof HttpClientErrorException) {
                final HttpClientErrorException httpError = (HttpClientErrorException) ex;
                if (httpError.getStatusCode().is4xxClientError()) {
                    return null;
                }
            }
            throw new RuntimeException("Erro ao realizar autenticação", ex);
        }
    }

    private HttpEntity<MultiValueMap<String, String>> criarEntidadeReq(final String login, final String senha,
            final String chaveSistema) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final String senhaCodificada = Base64Util.toBase64(senha);

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>(3);
        params.set("login", login);
        params.set("senha", senhaCodificada);
        params.set("sistema", chaveSistema);

        return new HttpEntity<>(params, headers);
    }

	public Unidade findUnidadeIdBySigla(String sigla) {
		return this.unidadesRepository.findBySigla(sigla).get();
	}

}

