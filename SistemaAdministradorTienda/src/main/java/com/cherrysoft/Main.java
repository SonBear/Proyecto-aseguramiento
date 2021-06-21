package com.cherrysoft;

import com.cherrysoft.model.repository.UsuarioRepository;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) {

        UsuarioRepository us = new UsuarioRepository();
        System.out.println(us.findById(1).get().getNombre());

    }
}
