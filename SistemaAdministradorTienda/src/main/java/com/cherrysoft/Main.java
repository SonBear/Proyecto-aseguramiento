package com.cherrysoft;

import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.repository.UsuarioRepository;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) {

        UsuarioRepository usR = new UsuarioRepository();
        Usuario us = new Usuario();
        us.setAdmin(1);
        us.setContrasenia("password");
        us.setCorreo("correo");

        usR.save(us);

    }
}
