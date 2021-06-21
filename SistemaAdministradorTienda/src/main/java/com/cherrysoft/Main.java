package com.cherrysoft;

import com.cherrysoft.controlador.InventarioControlador;
import com.cherrysoft.model.repository.UsuarioRepository;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) {

        InventarioControlador c = new InventarioControlador();
        c.abrirVentana();
    }
}
