/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.controlador;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.repository.ArticuloRepository;
import com.cherrysoft.model.repository.ClienteRepository;
import com.cherrysoft.util.EnumTiposDePromociones;
import com.cherrysoft.vistas.PanelPromocionTipo1;
import com.cherrysoft.vistas.PanelPromocionTipo2;
import com.cherrysoft.vistas.PanelPromocionTipo3;
import com.cherrysoft.vistas.PanelPromocionTipo4;
import com.cherrysoft.vistas.VistaPromociones;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
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
        this.loadPanel(panelTipo1);        
        this.vista.getComboTipoPromocion().setModel(new DefaultComboBoxModel(EnumTiposDePromociones.values()));
        
        this.clientesRepository = new ClienteRepository();
        this.articulosRepository = new ArticuloRepository();

        this.clientesAsignados = new HashMap<Integer, Cliente>();
         
        this.cargarClientes();
        this.cargarArticulos();
        
        this.panelTipo1.getBtnAgregarCliente().addActionListener(this::actionAsignarCliente);
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
       /* System.out.println("<--Hashmap-->");        
        clientesAsignados.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
        System.out.println("<- Fin ->");*/
    }
    
    private void loadPanel(JPanel panel) {
        vista.getPanelDetallesPromocion().removeAll();
        vista.getPanelDetallesPromocion().setLayout(new BorderLayout());
        vista.getPanelDetallesPromocion().add(panel, BorderLayout.CENTER);
        vista.getPanelDetallesPromocion().validate();
        vista.getPanelDetallesPromocion().repaint();        
    }    
    
}
