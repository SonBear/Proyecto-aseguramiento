/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.controlador;

import com.cherrysoft.model.service.ServicioPromocionesImp;
import com.cherrysoft.util.EnumTiposDePromociones;
import com.cherrysoft.vistas.PanelPromocionTipo1;
import com.cherrysoft.vistas.PanelPromocionTipo2;
import com.cherrysoft.vistas.PanelPromocionTipo3;
import com.cherrysoft.vistas.PanelPromocionTipo4;
import com.cherrysoft.vistas.VistaPromociones;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

/**
 *
 * @author Equipo1
 */
public class ControladorPromociones {
    private PanelPromocionTipo1 panelTipo1;
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
    }
    
    private void actionCrearPromocion(ActionEvent e) {
        EnumTiposDePromociones promocionActiva = (EnumTiposDePromociones) vista.getComboTipoPromocion().getSelectedItem();
        Date inicio = parseDate("2021-06-15");
        Date fin = parseDate("2021-07-15");        
        if(promocionActiva == EnumTiposDePromociones.Tipo1) {
            promocionT1.crearPromocion(inicio, fin);
        }
        else if(promocionActiva == EnumTiposDePromociones.Tipo2) {
            System.out.println("Promocion TIPO 2");
        }
        else if(promocionActiva == EnumTiposDePromociones.Tipo3) {
            System.out.println("Promocion TIPO 3");
        }
        else if(promocionActiva == EnumTiposDePromociones.Tipo4) {
            System.out.println("Promocion TIPO 4");            
        }
    }    
    
    private Date parseDate(String date) {
        Date parsedDate = null;
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            parsedDate = ft.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorPromociones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parsedDate;    
    }
    
    private void loadPanel(JPanel panel) {
        vista.getPanelDetallesPromocion().removeAll();
        vista.getPanelDetallesPromocion().setLayout(new BorderLayout());
        vista.getPanelDetallesPromocion().add(panel, BorderLayout.CENTER);
        vista.getPanelDetallesPromocion().validate();
        vista.getPanelDetallesPromocion().repaint();        
    }    
    
}
