package com.cherrysoft;

import com.cherrysoft.controlador.InventarioControlador;
import com.cherrysoft.controlador.LoginControlador;
import com.cherrysoft.interfaces.UsuarioService;
import com.cherrysoft.model.data.Rol;
import com.cherrysoft.model.repository.UsuarioRepository;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.service.UsuarioImp;
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
        LoginControlador co = new LoginControlador();
        co.abrirVentana();

        UsuarioService usuarioService = new UsuarioImp();

        usuarioService.registrarUsuario("SonBear28", "SonBear28", "emmanuelisai_28@hotmail.com", Rol.NORMAL);
    }
}
