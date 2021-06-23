package com.cherrysoft.model.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Emmanuel Chable
 */
public class Encriptador {

    public static String encriptarContrasenia(String contrasenia) {
        return DigestUtils.sha256Hex(contrasenia);
    }
}
