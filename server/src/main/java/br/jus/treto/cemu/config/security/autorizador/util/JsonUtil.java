package br.jus.treto.cemu.config.security.autorizador.util;


import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T parse(final String json, final Class<T> classeAlvo) {
        try {
            return objectMapper.readValue(json, classeAlvo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String serialize(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

