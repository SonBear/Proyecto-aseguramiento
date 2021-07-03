package com.cherrysoft;

import com.cherrysoft.controlador.LoginControlador;
import com.cherrysoft.seguridad.ConfiguracionSistema;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ConfiguracionSistema.configurarSistemaInicial();
        LoginControlador login = new LoginControlador();
        login.abrirVentana();
    }
}
