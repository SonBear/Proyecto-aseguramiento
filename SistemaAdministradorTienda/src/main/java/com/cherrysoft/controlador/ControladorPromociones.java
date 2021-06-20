/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.controlador;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.data.Promocion;
import com.cherrysoft.model.repository.ArticuloRepository;
import com.cherrysoft.model.repository.ClienteRepository;
import com.cherrysoft.model.service.ServicioPromocionesImp;
import com.cherrysoft.util.EnumTiposDePromociones;
import com.cherrysoft.vistas.PanelPromocionTipo1;
import com.cherrysoft.vistas.PanelPromocionTipo2;
import com.cherrysoft.vistas.PanelPromocionTipo3;
import com.cherrysoft.vistas.PanelPromocionTipo4;
import com.cherrysoft.vistas.VistaPromociones;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

/**
 *
 * @author Equipo1
 */
public class ControladorPromociones {
    private PanelPromocionTipo1 panelTipo1;
    private PanelPromocionTipo2 panelTipo2;
    private PanelPromocionTipo3 panelTipo3;
    private PanelPromocionTipo4 panelTipo4;
    private ClienteRepository clientesRepository;
    private ArticuloRepository articulosRepository;
    HashMap<Integer, Cliente> clientesAsignados;
    ServicioPromocionesImp servicioPromociones;
    private VistaPromociones vista;

    public ControladorPromociones(VistaPromociones vista) {
        this.vista = vista;
        this.initComponents();
    }
    
    private void initComponents() {
        this.panelTipo1 = new PanelPromocionTipo1();
        this.panelTipo2 = new PanelPromocionTipo2();
        this.panelTipo3 = new PanelPromocionTipo3();
        this.panelTipo4 = new PanelPromocionTipo4();
        this.servicioPromociones = new ServicioPromocionesImp();
        this.loadPanel(panelTipo1);        
        this.vista.getComboTipoPromocion().setModel(new DefaultComboBoxModel(EnumTiposDePromociones.values()));
        
        this.clientesRepository = new ClienteRepository();
        this.articulosRepository = new ArticuloRepository();

        this.clientesAsignados = new HashMap<Integer, Cliente>();
         
        this.cargarClientes();
        this.cargarArticulos();
        
        this.panelTipo1.getBtnAgregarCliente().addActionListener(this::actionAsignarCliente);
        this.vista.getBtnCrearPromocion().addActionListener(this::actionCrearPromocion);
    }
    
    private void cargarClientes() {
        panelTipo1.getComboClientes().removeAllItems();
        Iterable<Cliente> listaClientes = clientesRepository.findAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        listaClientes.forEach(cliente -> {
            model.addElement(cliente);
        });
        panelTipo1.getComboClientes().setModel(model);
    }
    
    private void cargarArticulos() {
        panelTipo1.getComboArticuloAComprar().removeAllItems();
        Iterable<Articulo> listaArticulos = articulosRepository.findAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        listaArticulos.forEach(articulo -> {
            model.addElement(articulo);
        });
        panelTipo1.getComboArticuloAComprar().setModel(model);
    }
    
    private void actionAsignarCliente(ActionEvent e) {
        Cliente seleccion = (Cliente) panelTipo1.getComboClientes().getSelectedItem();        
        clientesAsignados.put(seleccion.getId(), seleccion);
        JTable tablaClientes = panelTipo1.getTableClientesAsignados();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Email");
        clientesAsignados.entrySet().forEach(entry -> {
           model.addRow(new String[]{entry.getValue().getNombre(), entry.getValue().getCorreo()});
        });     
        tablaClientes.setModel(model);
    }
    
    private void actionCrearPromocion(ActionEvent e) {
        Date inicio = new Date(2021, 6, 30);
        Date fin = new Date(2021, 7, 15);
        Articulo articulo = (Articulo) panelTipo1.getComboArticuloAComprar().getSelectedItem();
        int cantidad = Integer.parseInt(panelTipo1.getTxtCantidad().getText());
        double descuento = Double.parseDouble(panelTipo1.getTxtDescuento().getText());
        
        Promocion promo = servicioPromociones.crearPromocionTipo1(articulo, cantidad, descuento, inicio, fin);
        clientesAsignados.entrySet().forEach(entry -> {
            servicioPromociones.asignarPromocionCliente(promo, entry.getValue());
        });           
    }
    
    private void loadPanel(JPanel panel) {
        vista.getPanelDetallesPromocion().removeAll();
        vista.getPanelDetallesPromocion().setLayout(new BorderLayout());
        vista.getPanelDetallesPromocion().add(panel, BorderLayout.CENTER);
        vista.getPanelDetallesPromocion().validate();
        vista.getPanelDetallesPromocion().repaint();        
    }    
    
}
