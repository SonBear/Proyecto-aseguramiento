package com.cherrysoft.controlador;

import com.cherrysoft.controlador.util.DialogosUtil;
import com.cherrysoft.controlador.util.TablaManager;
import com.cherrysoft.interfaces.InventarioService;
import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.service.InventarioImp;
import com.cherrysoft.vista.AgregarArticuloForm;
import com.cherrysoft.vista.InventarioView;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityExistsException;
import javax.swing.JFileChooser;

/**
 * Controlador para el servicio y vista del inventario
 *
 * @author Emmanuel Chable
 */
public class InventarioControlador extends Controlador {

    private final InventarioView vista;
    private final AgregarArticuloForm agregarArticuloForm;
    private final AgregarArticuloForm actualizarArticuloForm;
    private final InventarioService inventarioService;
    private Articulo articuloSeleccionado;

    public InventarioControlador(Usuario usuario, Controlador controladorAnterior) {
        super(usuario, controladorAnterior);
        this.vista = new InventarioView();
        this.inventarioService = new InventarioImp();
        this.agregarArticuloForm = new AgregarArticuloForm(vista);
        this.actualizarArticuloForm = new AgregarArticuloForm(vista);
        configurarControlador();

    }

    @Override
    public void abrirVentana() {
        this.vista.setVisible(true);
        this.actualizarTabla(inventarioService.obtenerArticulosDisponibles());

    }

    @Override
    public void cerrarVentana() {
        this.vista.setVisible(false);
        TablaManager.eliminarFilasTable(vista.getTablaArticulos());
    }

    @Override
    public void configurarControlador() {
        vista.setLocationRelativeTo(null);

        TablaManager.configurarTabla(vista.getTablaArticulos(), new String[]{"id", "nombre", "cantidad", "precio"});
        TablaManager.establecerEventoClick(vista.getTablaArticulos(), this::hacerClickTabla);

        vista.getBtnActualizarArticulo().addActionListener(this::abrirActualizarForm);
        vista.getBtnAgregarArticulosCVS().addActionListener(this::agregarArticulosCSV);
        vista.getBtnAñadirArticulo().addActionListener(this::abrirAgregarForm);
        vista.getBtnEliminarArticulo().addActionListener(this::eliminarArticulo);
        vista.getBtnFiltrar().addActionListener(this::filtrarArticulos);
        vista.getBtnRegresar().addActionListener((e) -> this.regresarControladorAnterior());
        vista.getBtnBuscarArticuloEnProveedores().addActionListener(this::buscarEnProveedores);

        if (!verificador.esUsuarioAdmin(usuario)) {
            vista.getBtnActualizarArticulo().setEnabled(false);
            vista.getBtnAgregarArticulosCVS().setEnabled(false);
            vista.getBtnAñadirArticulo().setEnabled(false);
            vista.getBtnEliminarArticulo().setEnabled(false);
            vista.getBtnBuscarArticuloEnProveedores().setEnabled(false);
        }
        agregarArticuloForm.getBtnAceptar().addActionListener(this::agregarNuevoArticulo);
        agregarArticuloForm.getBtnCancelar().addActionListener(this::cerrarAgregarForm);
        agregarArticuloForm.getOperacionLabel().setText("\t Agregar nuevo articulo");
        agregarArticuloForm.setLocationRelativeTo(vista);
        actualizarArticuloForm.getBtnAceptar().addActionListener(this::actualizarArticulo);
        actualizarArticuloForm.getBtnCancelar().addActionListener(this::cerrarActualizarForm);
        actualizarArticuloForm.getOperacionLabel().setText("\t Actualizar articulo");
        actualizarArticuloForm.getCantidadLabel().setText("Cantidad a aumentar: ");
        actualizarArticuloForm.setLocationRelativeTo(vista);
    }

    private Articulo getArticuloSelectedTable() {

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

    private void hacerClickTabla(MouseEvent event) {
        this.articuloSeleccionado = getArticuloSelectedTable();
        if (!Objects.isNull(this.articuloSeleccionado)) {
            vista.getNombreArticuloLabel().setText("nombre: " + articuloSeleccionado.getNombre());
            vista.getPrecioArticuloLabel().setText("precio: " + articuloSeleccionado.getPrecio().toString());
            vista.getIdArticuloLabel().setText("id: " + articuloSeleccionado.getId());
            vista.getCantidadArticuloLabel().setText("cantidad: " + articuloSeleccionado.getCantidad());
        }
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
        try {
            Integer min = Integer.parseInt(vista.getTxtMin().getText());
            Integer max = Integer.parseInt(vista.getTxtMax().getText());
            actualizarTabla(inventarioService.obtenerArticulosPorRangoCantidad(min, max));
        } catch (NumberFormatException ex) {
            DialogosUtil.mostrarDialogoDeError(vista, "Error en el formato de los numeros introducidos");
        }
    }

    private void agregarArticulosCSV(ActionEvent e) {

        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showDialog(vista, "Seleccionar");
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                vista.getTxtRutaArchivo().setText(file.getAbsolutePath());
                List<Articulo> articulos = inventarioService.registrarArticulosPorCsv(vista.getTxtRutaArchivo().getText());
                actualizarTabla(inventarioService.obtenerArticulosDisponibles());
                articulos.forEach((articulo) -> {
                    verificador.guardarAccionUsuario(usuario, "Agrego o actualizo el articulo con el id: " + articulo.getId());
                });
            }

        } catch (FileNotFoundException ex) {
            DialogosUtil.mostrarDialogoDeError(vista, "No se ha encontrado el archivo especificado");
        } catch (NumberFormatException ex) {
            DialogosUtil.mostrarDialogoDeError(vista, "Error en el formato de los numeros");
        } catch (IOException ex) {
            DialogosUtil.mostrarDialogoDeError(vista, "Error al encontrar la ruta del archivo");

        } catch (IllegalArgumentException ex) {
            DialogosUtil.mostrarDialogoDeError(vista, "El archivo no cumple con las especificacions csv");
        }
    }

    private void eliminarArticulo(ActionEvent e) {
        if (Objects.isNull(articuloSeleccionado)) {
            DialogosUtil.mostrarDialogoDeError(vista, "Error no se ha seleccionado un articulo");
        } else if (DialogosUtil.mostrarDialogoDeConfirmacion(vista, "¿Desea eliminar el articulo?")) {
            Articulo articulo = inventarioService.eliminarArticulo(articuloSeleccionado);
            actualizarTabla(inventarioService.obtenerArticulosDisponibles());
            verificador.guardarAccionUsuario(usuario, "Eliminar articulo con el id: " + articulo.getId());
        }

    }

    private void agregarNuevoArticulo(ActionEvent e) {
        try {
            Integer id = Integer.parseInt(agregarArticuloForm.getTxtCodigoBarras().getText());
            Integer cantidad = Integer.parseInt(agregarArticuloForm.getTxtCantidad().getText());
            String descripcion = agregarArticuloForm.getTxtDescripcion().getText();
            BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(agregarArticuloForm.getTxtPrecio().getText()));
            String nombre = agregarArticuloForm.getTxtNombre().getText();
            inventarioService.registrarArticulo(id, nombre, descripcion, precio, cantidad);
            actualizarTabla(inventarioService.obtenerArticulosDisponibles());
            verificador.guardarAccionUsuario(usuario, "Nuedo articulo con el id: " + id);
            cerrarAgregarForm(e);
        } catch (NumberFormatException ex) {
            DialogosUtil.mostrarDialogoDeError(agregarArticuloForm, "Error en el formato de los numeros");
        } catch (EntityExistsException ex) {
            DialogosUtil.mostrarDialogoDeError(agregarArticuloForm, "El articulo con ese codigo ya se ha registrado");
        }

    }

    private void abrirAgregarForm(ActionEvent e) {
        agregarArticuloForm.setVisible(true);
    }

    private void cerrarAgregarForm(ActionEvent e) {
        agregarArticuloForm.getTxtCodigoBarras().setText("");
        agregarArticuloForm.getTxtCantidad().setText("");
        agregarArticuloForm.getTxtDescripcion().setText("");
        agregarArticuloForm.getTxtPrecio().setText("");
        agregarArticuloForm.getTxtNombre().setText("");
        agregarArticuloForm.setVisible(false);
    }

    private void abrirActualizarForm(ActionEvent e) {
        if (Objects.isNull(articuloSeleccionado)) {
            DialogosUtil.mostrarDialogoDeError(vista, "No se ha seleccionado un articulo");
        } else {
            actualizarArticuloForm.getTxtCodigoBarras().setText(articuloSeleccionado.getId() + "");
            actualizarArticuloForm.getTxtCantidad().setText(articuloSeleccionado.getCantidad() + "");
            actualizarArticuloForm.getTxtDescripcion().setText(articuloSeleccionado.getDescripcion());
            actualizarArticuloForm.getTxtPrecio().setText(articuloSeleccionado.getPrecio().toString());
            actualizarArticuloForm.getTxtNombre().setText(articuloSeleccionado.getNombre());
            actualizarArticuloForm.setVisible(true);
        }

    }

    private void cerrarActualizarForm(ActionEvent e) {
        actualizarArticuloForm.getTxtCodigoBarras().setText("");
        actualizarArticuloForm.getTxtCantidad().setText("");
        actualizarArticuloForm.getTxtDescripcion().setText("");
        actualizarArticuloForm.getTxtPrecio().setText("");
        actualizarArticuloForm.getTxtNombre().setText("");
        actualizarArticuloForm.setVisible(false);
    }

    private void actualizarArticulo(ActionEvent e) {

        try {
            Integer id = Integer.parseInt(actualizarArticuloForm.getTxtCodigoBarras().getText());
            Integer cantidad = Integer.parseInt(actualizarArticuloForm.getTxtCantidad().getText());
            String descripcion = actualizarArticuloForm.getTxtDescripcion().getText();
            BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(actualizarArticuloForm.getTxtPrecio().getText()));
            String nombre = actualizarArticuloForm.getTxtNombre().getText();

            articuloSeleccionado.setCantidad(articuloSeleccionado.getCantidad() + cantidad);
            articuloSeleccionado.setDescripcion(descripcion);
            articuloSeleccionado.setId(id);
            articuloSeleccionado.setNombre(nombre);
            articuloSeleccionado.setPrecio(precio);

            inventarioService.actualizarArticulo(articuloSeleccionado);
            actualizarTabla(inventarioService.obtenerArticulosDisponibles());
            verificador.guardarAccionUsuario(usuario, "Actualizar articulo con id: " + id);
            cerrarActualizarForm(e);
        } catch (NumberFormatException ex) {
            DialogosUtil.mostrarDialogoDeError(vista, "Error en el formato de los numeros");
        }
    }

    private void buscarEnProveedores(ActionEvent e) {
        System.out.println("Por implementar");
    }
}
