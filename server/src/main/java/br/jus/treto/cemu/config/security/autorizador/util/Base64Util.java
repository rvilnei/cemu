package br.jus.treto.cemu.config.security.autorizador.util;


import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Base64Util {

    private static Encoder encoder;

    private static Encoder getEncoder() {
        if (encoder == null) {
            encoder = Base64.getEncoder();
        }
        return encoder;
    }

    public static String toBase64(final byte[] bytes) {
        return getEncoder().encodeToString(bytes);
    }

    public static String toBase64(final String value) {
        final Charset charset = Charset.forName("utf-8");
        final byte[] bytes = value.getBytes(charset);

        return Base64Util.toBase64(bytes);
    }

}


