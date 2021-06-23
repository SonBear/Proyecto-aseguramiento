package com.cherrysoft.controlador;

import com.cherrysoft.controlador.util.DialogosUtil;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.vista.MenuView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author VictorEnrique
 */
public class MenuControlador extends Controlador {

    private final MenuView vista;

    public MenuControlador(Usuario usuario, Controlador controladorAnterior) {
        super(usuario, controladorAnterior);
        vista = new MenuView();
        configurarControlador();
    }

    @Override
    public void abrirVentana() {
        this.vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);
    }

    @Override
    public void cerrarVentana() {
        this.vista.dispose();
    }

    @Override
    public void configurarControlador() {
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

    private void cerrarSesion(ActionEvent e) {
        try {
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

            InventarioControlador inventarioControlador = new InventarioControlador(usuario, this);
            inventarioControlador.abrirVentana();
            cerrarVentana();
        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    private void Promociones(ActionEvent e) {

        try {
            ControladorPromociones controladorPromociones = new ControladorPromociones(this.usuario, this);
            controladorPromociones.abrirVentana();
            cerrarVentana();

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
            int resultado = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea salir?");
            if (resultado == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(vista, "Que tenga buen día.");
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

            UsuariosController usuariosController = new UsuariosController(this.usuario, this);
            usuariosController.abrirVentana();
            cerrarVentana();
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
