package br.jus.treto.cemu.config.security.autorizador;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoService {

    private final Environment env;

    @Autowired
    public ConfiguracaoService(Environment env) {
        this.env = env;
    }

    @Cacheable(cacheNames = "ConfigService", key = "#key + '-string'")
    public String getString(final String key) {
        return env.getProperty(key);
    }

    @Cacheable(cacheNames = "ConfigService", key = "#key + '-int'")
    public Integer getInt(final String key) {
        final String val = this.getString(key);
        if (StringUtils.isBlank(val)) {
            return null;
        }
        return Integer.valueOf(val);
    }

    @Cacheable(cacheNames = "ConfigService", key = "#key + '-csv'")
    public List<String> getCsv(final String key) {
        final String val = this.getString(key);
        if (StringUtils.isBlank(val)) {
            return Collections.emptyList();
        }
        final String[] parts = val.split(",");
        return Collections.unmodifiableList(Arrays.asList(parts));
    }

    @Cacheable(cacheNames = "ConfigService", key = "#key + '-bool'")
    public Boolean getBoolean(final String key) {
        final String val = this.getString(key);
        if (StringUtils.isBlank(val)) {
            return false;
        }
        return Boolean.valueOf(val);
    }

}

