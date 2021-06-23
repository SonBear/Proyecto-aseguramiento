/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.controlador;

import com.cherrysoft.excepciones.FechaInvalidaException;
import com.cherrysoft.model.service.ServicioPromocionesImp;
import com.cherrysoft.util.EnumTiposDePromociones;
import com.cherrysoft.vistas.PanelPromocionTipo2;
import com.cherrysoft.vistas.PanelPromocionTipo3;
import com.cherrysoft.vistas.PanelPromocionTipo4;
import com.cherrysoft.vistas.VistaPromociones;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Equipo1
 */
public class ControladorPromociones {
    private ControladorPanelPromocionTipo1 promocionT1;
    private PanelPromocionTipo2 panelTipo2;
    private PanelPromocionTipo3 panelTipo3;
    private PanelPromocionTipo4 panelTipo4;
    ServicioPromocionesImp servicioPromociones;
    private VistaPromociones vista;

    public ControladorPromociones(VistaPromociones vista) {
        this.vista = vista;
        this.initComponents();
    }
    
    private void initComponents() {
        this.promocionT1 = new ControladorPanelPromocionTipo1();
        this.panelTipo2 = new PanelPromocionTipo2();
        this.panelTipo3 = new PanelPromocionTipo3();
        this.panelTipo4 = new PanelPromocionTipo4();
        
        this.servicioPromociones = new ServicioPromocionesImp();
        
        this.loadPanel(promocionT1.getPanel());  
        
        this.vista.getComboTipoPromocion().setModel(new DefaultComboBoxModel(EnumTiposDePromociones.values()));
        this.vista.getBtnCrearPromocion().addActionListener(this::actionCrearPromocion);
        this.vista.getComboTipoPromocion().addActionListener(this::cambiarTipoDePromocion);
    }
    
    private void cambiarTipoDePromocion(ActionEvent e) {
        switch(getTipoDePromocionActiva()) {
            case Tipo1:
                loadPanel(promocionT1.getPanel());
                break;
            case Tipo2:
                loadPanel(panelTipo2);
                break;
             case Tipo3:
                loadPanel(panelTipo3);
                break;
            case Tipo4:
                loadPanel(panelTipo4);
                break;                
        }
    }
    
    private void actionCrearPromocion(ActionEvent e) { 
        try {
            String stringFechaInicio = vista.getTxtFechaDeinicio().getText();
            String stringFechaFin = vista.getTxtFechaDeFin().getText();
            Date inicio = parseDate(stringFechaInicio);
            Date fin = parseDate(stringFechaFin);
            
            switch(getTipoDePromocionActiva()) {
                case Tipo1:
                    promocionT1.crearPromocion(inicio, fin);
                    break;
                case Tipo2:
                    System.out.println("Promocion TIPO 2");
                    break;
                case Tipo3:
                    System.out.println("Promocion TIPO 3");
                    break;
                case Tipo4:
                    System.out.println("Promocion TIPO 4");
                    break;        
            }
        } catch (FechaInvalidaException ex) {
            mostrarMensaje(ex.getMessage());
        }
    }    
    
    private Date parseDate(String date) throws FechaInvalidaException {
        Date parsedDate = null;
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");            
            parsedDate = ft.parse(date);
        } catch (ParseException ex) {
            throw new FechaInvalidaException();
        }
        return parsedDate;    
    }
    
    private EnumTiposDePromociones getTipoDePromocionActiva() {
        EnumTiposDePromociones promocionActiva;
        promocionActiva = (EnumTiposDePromociones) vista.getComboTipoPromocion().getSelectedItem();
        return promocionActiva;
    }
    
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }    
    
    private void loadPanel(JPanel panel) {
        vista.getPanelDetallesPromocion().removeAll();
        vista.getPanelDetallesPromocion().setLayout(new BorderLayout());
        vista.getPanelDetallesPromocion().add(panel, BorderLayout.CENTER);
        vista.getPanelDetallesPromocion().validate();
        vista.getPanelDetallesPromocion().repaint();        
    }    
    
}
