/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.controlador;

import com.cherrysoft.excepciones.FechaInvalidaException;
import com.cherrysoft.model.data.Usuario;
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

public class ControladorPromociones extends Controlador {

    private ControladorPanelPromocionTipo1 promocionT1;
    private PanelPromocionTipo2 panelTipo2;
    private PanelPromocionTipo3 panelTipo3;
    private PanelPromocionTipo4 panelTipo4;
    ServicioPromocionesImp servicioPromociones;
    private VistaPromociones vista;
    private Controlador controladorAnterior;


    public ControladorPromociones(Usuario usuario, Controlador controladorAnterior) {
        super(usuario, controladorAnterior);
        this.vista = new VistaPromociones();
        this.configurarControlador();
        this.configurarVista(vista);

    }

    
    public void setControladorAnterior(Controlador controladorAnterior) {
        this.controladorAnterior = controladorAnterior;
    }
    
    private void cambiarTipoDePromocion(ActionEvent e) {
        switch (getTipoDePromocionActiva()) {
            case Tipo1:
                loadPanel(this.vista, promocionT1.getPanel());
                break;
            case Tipo2:
                loadPanel(this.vista, panelTipo2);
                break;

             case Tipo3:
                loadPanel(this.vista, panelTipo3);
                break;
            case Tipo4:
                loadPanel(this.vista, panelTipo4);
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
    
    private void loadPanel(VistaPromociones vista, JPanel panel) {

        vista.getPanelDetallesPromocion().removeAll();
        vista.getPanelDetallesPromocion().setLayout(new BorderLayout());
        vista.getPanelDetallesPromocion().add(panel, BorderLayout.CENTER);
        vista.getPanelDetallesPromocion().validate();
        vista.getPanelDetallesPromocion().repaint();        
    }    
    
    private void configurarVista(VistaPromociones vista) {
        this.loadPanel(vista, promocionT1.getPanel()); 
        vista.getComboTipoPromocion().setModel(new DefaultComboBoxModel(EnumTiposDePromociones.values()));
        vista.getBtnCrearPromocion().addActionListener(this::actionCrearPromocion);
        vista.getComboTipoPromocion().addActionListener(this::cambiarTipoDePromocion); 
        
        if (!verificador.esUsuarioAdmin(usuario)) {
            vista.getComboTipoPromocion().setEnabled(false);
            vista.getTxtFechaDeinicio().setEnabled(false);
            vista.getTxtFechaDeFin().setEnabled(false);
            vista.getPanelDetallesPromocion().setEnabled(false);
            vista.getPanelDetallesPromocion().removeAll();
            vista.getBtnCrearPromocion().setEnabled(false);
        }

    }

    @Override
    public void abrirVentana() {
        this.vista = new VistaPromociones();  
        this.configurarVista(this.vista);
        this.vista.setVisible(true);
    }

    @Override
    public void cerrarVentana() {
        this.vista.dispose();
    }

    @Override
    public void configurarControlador() {
        this.promocionT1 = new ControladorPanelPromocionTipo1();
        this.panelTipo2 = new PanelPromocionTipo2();
        this.panelTipo3 = new PanelPromocionTipo3();
        this.panelTipo4 = new PanelPromocionTipo4();
        
        this.servicioPromociones = new ServicioPromocionesImp();
    }
    

}
