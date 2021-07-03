/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.controlador;

import com.cherrysoft.controlador.util.DialogosUtil;
import com.cherrysoft.controlador.util.TablaManager;
import com.cherrysoft.interfaces.ClientesService;
import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.service.ClientesImp;
import com.cherrysoft.vista.ClientesView;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

/**
 * Controlador para la vista de administracion de clientes y el servicio de
 * clientes
 * @author VictorEnrique
 */
public class ClientesControlador extends Controlador {
    private final ClientesView vista;
    private final ClientesService clienteService;
    private Cliente clienteSeleccionado;
    
    public ClientesControlador(Usuario usuario, Controlador controladorAnterior) {
        super(usuario, controladorAnterior);
        this.vista = new ClientesView();
        this.clienteService = new ClientesImp();
        configurarControlador();
    }
    
    @Override
    public void abrirVentana() {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        actualizarTabla(clienteService.obtenerClientes());
    }

    @Override
    public void cerrarVentana() {
        this.vista.setVisible(false);
        TablaManager.eliminarFilasTable(vista.getTablaClientes());
    }

    @Override
    public void configurarControlador() {
        TablaManager.configurarTabla(vista.getTablaClientes(), new String[]{"ID", "nombre", "email"});
        TablaManager.establecerEventoClick(vista.getTablaClientes(), this::hacerClickTabla);
        vista.getBotonActualizar().addActionListener(this::actualizarCliente);
        vista.getBotonRegistrar().addActionListener(this::registrarCliente);
        vista.getBotonEliminar().addActionListener(this::eliminarCliente);
        vista.getBotonRegresar().addActionListener((e) -> regresarControladorAnterior());
    }

    private Cliente obtenerClienteSeleccionadoEnTabla() {

        Object[] fila = TablaManager.obtenerFilaSeleccionada(vista.getTablaClientes());
        if (Objects.isNull(fila)) {
            System.out.println("No se ha seleccionado filas");
        }

        try {
            Cliente cliente = clienteService.obtenerClientePorId(Integer.parseInt((String) fila[0]));
            return cliente;
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return null;
    }
    
    private void hacerClickTabla(MouseEvent e) {
        this.clienteSeleccionado = obtenerClienteSeleccionadoEnTabla();
        if (!Objects.isNull(this.clienteSeleccionado)) {
            vista.getTxtEmailActualizar().setText(clienteSeleccionado.getCorreo());
            vista.getTxtClienteActualizar().setText(clienteSeleccionado.getNombre());
        }
    }
    
    private void actualizarTabla(List<Cliente> clientes) {
        TablaManager.eliminarFilasTable(vista.getTablaClientes());
        clientes.forEach((cliente) -> {
            TablaManager.agregarFila(vista.getTablaClientes(), new String[]{cliente.getId() + "",
                cliente.getNombre(), cliente.getCorreo()});
        });
    }

    private void actualizarCliente(ActionEvent e) {
        String email = vista.getTxtEmailActualizar().getText();
        String cliente = vista.getTxtClienteActualizar().getText();
        clienteService.actualizarDatosCliente(clienteSeleccionado, cliente, email);
        actualizarTabla(clienteService.obtenerClientes());
    }

    private void registrarCliente(ActionEvent e) {
        String email = vista.getTxtEmailRegistro().getText();
        String cliente = vista.getTxtClienteRegistro().getText();
        try {
            clienteService.registrarCliente(cliente, email);
            actualizarTabla(clienteService.obtenerClientes());
        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }
    }

    private void eliminarCliente(ActionEvent e) {
        if (!Objects.isNull(clienteSeleccionado)) {
            clienteService.eliminarCliente(clienteSeleccionado);
            actualizarTabla(clienteService.obtenerClientes());
        }
    }
}
