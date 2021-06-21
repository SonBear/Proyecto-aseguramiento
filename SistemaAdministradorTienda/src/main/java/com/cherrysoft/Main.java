package com.cherrysoft;

import com.cherrysoft.controlador.InventarioControlador;
import com.cherrysoft.model.repository.UsuarioRepository;
import com.cherrysoft.model.data.Usuario;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) {

        UsuarioRepository user = new UsuarioRepository();
        Usuario usuario = user.findById(1).get();
        InventarioControlador c = new InventarioControlador(usuario, null);
        c.abrirVentana();

    }
}
