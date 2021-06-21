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
import com.cherrysoft.util.ComboBoxUtil;
import com.cherrysoft.vistas.PanelPromocionTipo1;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Equipo1
 */
public class ControladorPanelPromocionTipo1 {

    private PanelPromocionTipo1 panelTipo1;
    private ClienteRepository clientesRepository;
    private ArticuloRepository articulosRepository;
    ServicioPromocionesImp servicioPromociones;    
    private ComboBoxUtil comboUtil;
    HashMap<Integer, Cliente> clientesAsignados;

    public ControladorPanelPromocionTipo1() {
        this.panelTipo1 = new PanelPromocionTipo1();
        this.comboUtil = new ComboBoxUtil();        
        this.initComponents();
    }
    
    private void initComponents() {
        comboUtil.rellenarComboConClientes(panelTipo1.getComboClientes());
        comboUtil.rellenarComboConArticulos(panelTipo1.getComboArticuloAComprar());
        servicioPromociones = new ServicioPromocionesImp();
        clientesAsignados = new HashMap();
        
        panelTipo1.getBtnAgregarCliente().addActionListener(this::actionAsignarCliente);        
    }

    private void actionAsignarCliente(ActionEvent e) {
        Cliente seleccion = (Cliente) panelTipo1.getComboClientes().getSelectedItem();
        clientesAsignados.put(seleccion.getId(), seleccion);
        rellenarTablaClientesAsignados(clientesAsignados, panelTipo1.getTableClientesAsignados());
    }
    
    public void crearPromocion(Date inicio, Date fin) {    
        Articulo articulo = this.getArticuloAComprar();
        int cantidad = this.getCantidadAComprar();
        double descuento = this.getDescuento();
        Promocion promo = servicioPromociones.crearPromocionTipo1(articulo, cantidad, descuento, inicio, fin);
        clientesAsignados.entrySet().forEach(entry -> {
            servicioPromociones.asignarPromocionCliente(promo, entry.getValue());
        });   
        mostrarMensaje("La promoción fue creada exitosamente");
        reiniciarPanel();
    }
    
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    private void reiniciarPanel() {
        clientesAsignados = new HashMap();
        panelTipo1.getTxtCantidad().setText("");
        panelTipo1.getTxtDescuento().setText("");
        rellenarTablaClientesAsignados(clientesAsignados, panelTipo1.getTableClientesAsignados());        
    }
    
    private void rellenarTablaClientesAsignados(HashMap<Integer, Cliente> clientesAsignados, JTable tabla) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Email");
        clientesAsignados.entrySet().forEach(entry -> {
            model.addRow(new String[]{entry.getValue().getNombre(), entry.getValue().getCorreo()});
        });
        tabla.setModel(model);        
    }
    
    public HashMap<Integer, Cliente> getClientesAsignados() {
        return clientesAsignados;
    }
    
    public Articulo getArticuloAComprar() {
        return (Articulo) panelTipo1.getComboArticuloAComprar().getSelectedItem();
    }
    
    public int getCantidadAComprar() {
        return Integer.parseInt(panelTipo1.getTxtCantidad().getText());
    }
    
    public double getDescuento() {
        return Double.parseDouble(panelTipo1.getTxtDescuento().getText());
    }
    
    public PanelPromocionTipo1 getPanel() {
        return panelTipo1;
    }

}
