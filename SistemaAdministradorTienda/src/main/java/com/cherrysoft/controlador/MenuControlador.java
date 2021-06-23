/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.controlador;

import com.cherrysoft.controlador.util.DialogosUtil;
import com.cherrysoft.interfaces.UsuarioService;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.vista.MenuView;
import com.cherrysoft.vistas.VistaPromociones;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author VictorEnrique
 */
public class MenuControlador extends Controlador {

    private final MenuView vista;
    private UsuarioService usuarioService;
    protected Controlador controladorAnterior;

    public MenuControlador() {
        vista = new MenuView();

        configurarControlador();
    }

    public void setUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void abrirVentana() {
        this.vista.setVisible(true);
    }

    @Override
    public void cerrarVentana() {
        this.vista.setVisible(false);
    }

    @Override
    public void configurarControlador() {
        this.vista.setLocationRelativeTo(null);
        this.vista.getBotonCerrarSesion().addActionListener(this::cerrarSesion);
        this.vista.getBotonClientes().addActionListener(this::Clientes);
        this.vista.getBotonInventario().addActionListener(this::Inventario);
        this.vista.getBotonPromosiones().addActionListener(this::Promociones);
        this.vista.getBotonProveedores().addActionListener(this::Proveedores);
        this.vista.getBotonSalir().addActionListener(this::Salir);
        this.vista.getBotonTickets().addActionListener(this::Tickets);
        this.vista.getBotonUsuarios().addActionListener(this::Usuarios);
        this.vista.getBotonUtilidades().addActionListener(this::Utilidades);
        this.vista.getBotonVender().addActionListener(this::Vender);
        this.vista.getBotonVentasDiarias().addActionListener(this::VentasDiarias);
    }

    @Override
    public void setControladorAnterior(Controlador controladorAnterior) {
        this.controladorAnterior = controladorAnterior;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void cerrarSesion(ActionEvent e) {
        try {
            cerrarVentana();
            regresarControladorAnterior();
        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    //PENDIENTE
    private void Clientes(ActionEvent e) {
        try {
            cerrarVentana();

        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    private void Inventario(ActionEvent e) {
        try {
            cerrarVentana();
            InventarioControlador inventarioControlador = new InventarioControlador(usuario, this);
            inventarioControlador.abrirVentana();
        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    private void Promociones(ActionEvent e) {
        try {
            cerrarVentana();
            VistaPromociones vistaPromociones = new VistaPromociones();
            ControladorPromociones controladorPromociones = new ControladorPromociones(vistaPromociones);
            controladorPromociones.setControladorAnterior(this);
        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    //PENDIENTE
    private void Proveedores(ActionEvent e) {
        try {
            cerrarVentana();

        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    private void Salir(ActionEvent e) {
        try {
            int resultado = JOptionPane.showConfirmDialog(null, "Método con 2 parámetros");
            if (resultado == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Que tenga buen día.");
                System.exit(0);
            }
        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    //PENDIENTE
    private void Tickets(ActionEvent e) {
        try {
            cerrarVentana();

        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    private void Usuarios(ActionEvent e) {
        try {
            cerrarVentana();
            UsuariosController usuariosController = new UsuariosController(this.usuario, this);
            usuariosController.abrirVentana();
        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    //PENDIENTE
    private void Utilidades(ActionEvent e) {
        try {
            cerrarVentana();

        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    //PENDIENTE
    private void Vender(ActionEvent e) {
        try {
            cerrarVentana();

        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    //PENDIENTE
    private void VentasDiarias(ActionEvent e) {
        try {
            cerrarVentana();

        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

}
