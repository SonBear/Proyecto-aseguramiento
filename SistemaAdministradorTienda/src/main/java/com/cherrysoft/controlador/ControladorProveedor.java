package com.cherrysoft.controlador;

import com.cherrysoft.controlador.Controlador;
import com.cherrysoft.controlador.util.TablaManager;
import com.cherrysoft.model.data.Proveedor;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.repository.ProveedorRepository;
import com.cherrysoft.vista.ProveedoresForm;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author Eusebio Ajas
 */
public class ControladorProveedor extends Controlador {

    private final ProveedoresForm proveedoresForm;
    private final ProveedorRepository proveedoresRepository;
    private Proveedor proveedorSelecionado;

    public ControladorProveedor(Usuario usuario, Controlador controladorAnterior) {
        super(usuario, controladorAnterior);
        proveedoresForm = new ProveedoresForm();
        proveedoresRepository = new ProveedorRepository();
        configurarControlador();
    }

    private void guardar(ActionEvent event) {
        Proveedor proveedor = new Proveedor();
        if (validarCampos()) {
            actualizarCampos(proveedor);
            proveedoresRepository.save(proveedor);
        } else {
            JOptionPane.showMessageDialog(proveedoresForm, "Datos incompletos. Por favor de proporcionar todos los datos");
        }
        actualizarTabla();
    }

    private void crear(ActionEvent event) {
        limpiarCampos();
    }

    private void actualizar(ActionEvent event) {
        Proveedor proveedorSelecionado = getProveedorSelectedTable();
        proveedoresForm.setTxtID(proveedorSelecionado.getId() + "");
        actualizarCampos(proveedorSelecionado);
        proveedoresRepository.update(proveedorSelecionado);
        actualizarTabla();
    }

    private void eliminar(ActionEvent event) {
        Proveedor proveedorSelecionado = getProveedorSelectedTable();
        proveedoresRepository.delete(proveedorSelecionado);
        actualizarTabla();
    }

    private void salir(ActionEvent event) {
        cerrarVentana();
    }

    private void actualizarCampos(Proveedor proveedor) {
        proveedor.setNombre(proveedoresForm.getTxtNombre());
        proveedor.setDireccion(proveedoresForm.getTxtDireccion());
        proveedor.setEmail(proveedoresForm.getTxtEmail());
        proveedor.setTelefono(proveedoresForm.getTxtTelefono());
    }

    private boolean validarCampos() {
        boolean estaNombre = !proveedoresForm.getTxtNombre().equals("");
        boolean estaEmail = !proveedoresForm.getTxtEmail().equals("");
        boolean estaDireccion = !proveedoresForm.getTxtDireccion().equals("");
        boolean estaTelefono = !proveedoresForm.getTxtTelefono().equals("");

        return estaNombre && estaEmail && estaDireccion && estaTelefono;
    }

    private void limpiarCampos() {
        proveedoresForm.setTxtID("");
        proveedoresForm.setTxtNombre("");
        proveedoresForm.setTxtEmail("");
        proveedoresForm.setTxtDireccion("");
        proveedoresForm.setTxtTelefono("");
    }

    private void actualizarCamposVista(Proveedor proveedor) {
        proveedoresForm.setTxtID(proveedor.getId() + "");
        proveedoresForm.setTxtNombre(proveedor.getNombre());
        proveedoresForm.setTxtEmail(proveedor.getEmail());
        proveedoresForm.setTxtDireccion(proveedor.getDireccion());
        proveedoresForm.setTxtTelefono(proveedor.getTelefono());
    }

    private Proveedor getProveedorSelectedTable() {

        Object[] fila = TablaManager.obtenerFilaSeleccionada(proveedoresForm.getTablaProveedores());
        if (Objects.isNull(fila)) {
            System.out.println("No se ha seleccionado filas");
        }

        try {
            Proveedor proveedor = proveedoresRepository.findById(Integer.parseInt((String) fila[0])).get();
            return proveedor;
        } catch (NumberFormatException ex) {
            System.out.println("Error");
        }
        return null;
    }

    private void hacerClickTabla(MouseEvent event) {
        proveedorSelecionado = getProveedorSelectedTable();
        if (!Objects.isNull(proveedorSelecionado)) {
            actualizarCamposVista(proveedorSelecionado);
        }
    }

    private void actualizarTabla() {
        List<Proveedor> proveedores = new ArrayList<>();
        proveedoresRepository.findAll().iterator().forEachRemaining(proveedores::add);
        System.out.println("EJecutandon pruebas....");
        TablaManager.eliminarFilasTable(proveedoresForm.getTablaProveedores());
        proveedores.forEach((proveedor) -> {
            TablaManager.agregarFila(proveedoresForm.getTablaProveedores(), new String[]{
                proveedor.getId() + "",
                proveedor.getNombre(),
                proveedor.getEmail(),
                proveedor.getTelefono(),
                proveedor.getDireccion()
            });
        });
    }

    @Override
    public void abrirVentana() {
        proveedoresForm.setLocationRelativeTo(null);
        proveedoresForm.setVisible(true);
    }

    @Override
    public void cerrarVentana() {
        proveedoresForm.setVisible(false);
        TablaManager.eliminarFilasTable(proveedoresForm.getTablaProveedores());
    }

    @Override
    public void configurarControlador() {
        TablaManager.configurarTabla(proveedoresForm.getTablaProveedores(), new String[]{"ID", "Nombre", "Email", "Telefono", "Direccion"});
        TablaManager.establecerEventoClick(proveedoresForm.getTablaProveedores(), this::hacerClickTabla);
        proveedoresForm.getBtnGuardar().addActionListener(this::guardar);
        proveedoresForm.getBtnNuevo().addActionListener(this::crear);
        proveedoresForm.getBtnActualizar().addActionListener(this::actualizar);
        proveedoresForm.getBtnEliminar().addActionListener(this::eliminar);
        proveedoresForm.getBtnSalir().addActionListener((e) -> this.regresarControladorAnterior());
        actualizarTabla();
    }

}
