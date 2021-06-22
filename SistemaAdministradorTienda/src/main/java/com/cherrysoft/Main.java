package com.cherrysoft;

import com.cherrysoft.controlador.InventarioControlador;
import com.cherrysoft.controlador.LoginControlador;
import com.cherrysoft.controlador.UsuariosController;
import com.cherrysoft.model.repository.UsuarioRepository;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.seguridad.ConfiguracionSistema;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ConfiguracionSistema.configurarSistemaInicial();
        UsuarioRepository user = new UsuarioRepository();
        Usuario usuario = user.findById(1).get();
        InventarioControlador c = new InventarioControlador(usuario, null);
        c.abrirVentana();
        LoginControlador co = new LoginControlador();
        co.abrirVentana();
        UsuariosController con = new UsuariosController(usuario, null);
        con.abrirVentana();
    }
}
