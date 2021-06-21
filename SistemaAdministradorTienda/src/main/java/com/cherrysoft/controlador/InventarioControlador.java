/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.controlador;

import com.cherrysoft.controlador.util.TablaManager;
import com.cherrysoft.interfaces.InventarioService;
import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.service.InventarioImp;
import com.cherrysoft.vista.AgregarArticuloForm;
import com.cherrysoft.vista.InventarioView;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Emmanuel Chable
 */
public class InventarioControlador extends Controlador {

    private final InventarioView vista;
    private final AgregarArticuloForm agregarArticuloForm;
    private final InventarioService inventarioService;
    private Articulo articuloSeleccionado;

    public InventarioControlador() {
        this.vista = new InventarioView();
        this.inventarioService = new InventarioImp();
        this.agregarArticuloForm = new AgregarArticuloForm(vista);
        configurarControlador();

    }

    @Override
    public void abrirVentana() {
        this.vista.setVisible(true);
        this.actualizarTabla(inventarioService.obtenerArticulosDisponibles());

    }

    @Override
    public void cerrarVentana() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configurarControlador() {
        vista.getTablaArticulos().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hacerClickTabla();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        TablaManager.configurarTabla(vista.getTablaArticulos(), new String[]{"id", "nombre", "cantidad", "precio"});
        vista.getBtnActualizarArticulo();
        vista.getBtnAgregarArticulosCVS().addActionListener(this::agregarArticulosCSV);
        vista.getBtnAñadirArticulo().addActionListener(this::agregarNuevoArticulo);
        vista.getBtnEliminarArticulo().addActionListener(this::eliminarArticulo);
        vista.getBtnFiltrar().addActionListener(this::filtrarArticulos);

    }

    @Override
    public void regresarControladorAnterior() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Articulo getArticuloSelectedTable() {

        Object[] fila = TablaManager.obtenerFilaSeleccionada(vista.getTablaArticulos());
        if (Objects.isNull(fila)) {
            System.out.println("No se ha seleccionado filas");
        }

        try {
            Articulo articulo = inventarioService.obtenerArticuloPorId(Integer.parseInt((String) fila[0]));
            return articulo;
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return null;
    }

    private void hacerClickTabla() {
        this.articuloSeleccionado = getArticuloSelectedTable();
        vista.getNombreArticuloLabel().setText("nombre: " + articuloSeleccionado.getNombre());
        vista.getPrecioArticuloLabel().setText("precio: " + articuloSeleccionado.getPrecio().toString());
        vista.getIdArticuloLabel().setText("id: " + articuloSeleccionado.getId());
        vista.getCantidadArticuloLabel().setText("cantidad: " + articuloSeleccionado.getCantidad());
    }

    private void actualizarTabla(List<Articulo> articulos) {

        TablaManager.eliminarFilasTable(vista.getTablaArticulos());
        articulos.forEach((articulo) -> {
            TablaManager.agregarFila(vista.getTablaArticulos(), new String[]{
                articulo.getId() + "", articulo.getNombre(), articulo.getCantidad() + "",
                articulo.getPrecio().toString()});
        });
    }

    private void filtrarArticulos(ActionEvent e) {
        Integer min = Integer.parseInt(vista.getTxtMin().getText());
        Integer max = Integer.parseInt(vista.getTxtMax().getText());
        actualizarTabla(inventarioService.obtenerArticulosPorRangoCantidad(min, max));
    }

    private void agregarArticulosCSV(ActionEvent e) {

        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showDialog(vista, "Seleccionar");
            File file = fc.getSelectedFile();
            inventarioService.registrarArticulosPorCsv(file.getAbsolutePath());
            actualizarTabla(inventarioService.obtenerArticulosDisponibles());
        } catch (IOException ex) {
        } catch (NumberFormatException ex) {
        }
    }

    private void eliminarArticulo(ActionEvent e) {
        if (Objects.isNull(articuloSeleccionado)) {
            JOptionPane.showMessageDialog(vista, "No se ha seleccionado un articulo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        inventarioService.eliminarArticulo(articuloSeleccionado);
        actualizarTabla(inventarioService.obtenerArticulosDisponibles());
    }

    private void agregarNuevoArticulo(ActionEvent e) {
        agregarArticuloForm.setVisible(true);
        agregarArticuloForm.getBtnAceptar().addActionListener((ev) -> {
            Integer id = Integer.parseInt(agregarArticuloForm.getTxtCodigoBarras().getText());
            Integer cantidad = Integer.parseInt(agregarArticuloForm.getTxtCantidad().getText());
            String descripcion = agregarArticuloForm.getTxtDescripcion().getText();
            BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(agregarArticuloForm.getTxtPrecio().getText()));
            String nombre = agregarArticuloForm.getTxtNombre().getText();
            inventarioService.registrarArticulo(id, nombre, descripcion, precio, cantidad);
            agregarArticuloForm.setVisible(false);
            actualizarTabla(inventarioService.obtenerArticulosDisponibles());
        });

        agregarArticuloForm.getBtnCancelar().addActionListener((ev) -> {
            agregarArticuloForm.setVisible(false);
        });
    }

}
