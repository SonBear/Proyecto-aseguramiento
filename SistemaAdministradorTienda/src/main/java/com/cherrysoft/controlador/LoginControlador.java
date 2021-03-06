package com.cherrysoft.controlador;

import com.cherrysoft.controlador.util.DialogosUtil;
import com.cherrysoft.interfaces.UsuarioService;
import com.cherrysoft.model.service.UsuarioImp;
import com.cherrysoft.vista.LoginView;
import java.awt.event.ActionEvent;

/**
 * Controlador para la vista del login y servicio de usuarios
 *
 * @author Emmanuel Chable
 */
public class LoginControlador extends Controlador {

    private final LoginView vista;
    private final UsuarioService usuarioService;
    private MenuControlador menuControlador;

    public LoginControlador() {
        vista = new LoginView();
        usuarioService = new UsuarioImp();
        configurarControlador();
    }

    @Override
    public void abrirVentana() {
        this.vista.setVisible(true);
    }

    @Override
    public void cerrarVentana() {
        this.vista.getTxtUsuario().setText("");
        this.vista.getTxtContrasenia().setText("");
        this.vista.dispose();

    }

    @Override
    public void configurarControlador() {
        this.vista.setLocationRelativeTo(null);
        this.vista.getBtnIniciarSesion().addActionListener(this::iniciarSesion);
    }

    @Override
    public void regresarControladorAnterior() {
        System.out.println("Sin controlador anterior");
    }

    private void iniciarSesion(ActionEvent e) {
        String usuarioNombre = vista.getTxtUsuario().getText();
        String contrasenia = vista.getTxtContrasenia().getText();
        try {
            this.usuario = usuarioService.login(usuarioNombre, contrasenia);
            menuControlador = new MenuControlador(usuario, this);
            menuControlador.abrirVentana();
            cerrarVentana();
        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

}
