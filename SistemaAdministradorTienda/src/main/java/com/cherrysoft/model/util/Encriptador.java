/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
