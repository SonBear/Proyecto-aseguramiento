package com.cherrysoft.controlador;

import com.cherrysoft.controlador.util.DialogosUtil;
import com.cherrysoft.controlador.util.TablaManager;
import com.cherrysoft.interfaces.UsuarioService;
import com.cherrysoft.model.data.Rol;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.service.UsuarioImp;
import com.cherrysoft.vista.UsuariosView;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;
import javax.swing.JComboBox;

/**
 * Controlador para la vista de administracion de usuarios y el servicio de
 * usuarios
 *
 * @author Emmanuel Chable
 */
public class UsuariosController extends Controlador {

    private final UsuariosView vista;
    private final UsuarioService usuarioService;
    private Usuario usuarioSeleccionado;
    protected Controlador controladorAnterior;

    public UsuariosController(Usuario usuario, Controlador controladorAnterior) {
        super(usuario, controladorAnterior);
        this.vista = new UsuariosView();
        this.usuarioService = new UsuarioImp();
        configurarControlador();
    }

    @Override
    public void abrirVentana() {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        actualizarTabla(usuarioService.obtenerUsuarios());
    }

    @Override
    public void cerrarVentana() {
        this.vista.setVisible(false);
        TablaManager.eliminarFilasTable(vista.getTablaUsuarios());
    }
    
    public void setControladorAnterior(Controlador controladorAnterior) {
        this.controladorAnterior = controladorAnterior;
    }

    @Override
    public void configurarControlador() {
        TablaManager.configurarTabla(vista.getTablaUsuarios(), new String[]{"id", "usuario", "nombre", "email", "rol"});
        TablaManager.establecerEventoClick(vista.getTablaUsuarios(), this::hacerClickTabla);
        vista.getBtnActualizar().addActionListener(this::actualizarUsuario);
        vista.getBtnRegistrar().addActionListener(this::registrarUsuario);
        vista.getBtnEliminar().addActionListener(this::eliminarUsuario);
        vista.getBtnRegresar().addActionListener((e) -> regresarControladorAnterior());
        establecerRoles();
    }

    private Usuario getUsuarioSelectedTable() {

        Object[] fila = TablaManager.obtenerFilaSeleccionada(vista.getTablaUsuarios());
        if (Objects.isNull(fila)) {
            System.out.println("No se ha seleccionado filas");
        }

        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorId(Integer.parseInt((String) fila[0]));
            return usuario;
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return null;
    }

    private void hacerClickTabla(MouseEvent e) {
        this.usuarioSeleccionado = getUsuarioSelectedTable();
        if (!Objects.isNull(this.usuarioSeleccionado)) {
            vista.getTxtEmailActualizar().setText(usuarioSeleccionado.getCorreo());
            vista.getTxtUsuarioActualizar().setText(usuarioSeleccionado.getUsuario());
            vista.getComboBoxRolesActualizar().setSelectedItem(usuarioSeleccionado.getPermiso().getRol());
        }
    }

    private void actualizarTabla(List<Usuario> usuarios) {

        TablaManager.eliminarFilasTable(vista.getTablaUsuarios());
        usuarios.forEach((usuario) -> {
            TablaManager.agregarFila(vista.getTablaUsuarios(), new String[]{usuario.getId() + "",
                usuario.getUsuario(), usuario.getCorreo(), usuario.getCorreo(),
                usuario.getPermiso().getRol().toString()});
        });
    }

    private void actualizarUsuario(ActionEvent e) {
        String email = vista.getTxtEmailActualizar().getText();
        String usuario = vista.getTxtUsuarioActualizar().getText();
        String contrasenia = vista.getTxtContraseniaActualizar().getText();
        Rol rol = (Rol) vista.getComboBoxRolesActualizar().getSelectedItem();
        usuarioService.actualizarDatosUsuario(usuarioSeleccionado, usuario, email, usuario, rol);
        actualizarTabla(usuarioService.obtenerUsuarios());
    }

    private void registrarUsuario(ActionEvent e) {
        String email = vista.getTxtEmailRegistro().getText();
        String usuario = vista.getTxtUsuarioRegistro().getText();
        String contrasenia = vista.getTxtContraseniaRegistro().getText();
        Rol rol = (Rol) vista.getComboBoxRolesRegistro().getSelectedItem();
        try {
            usuarioService.registrarUsuario(usuario, contrasenia, email, rol);
            actualizarTabla(usuarioService.obtenerUsuarios());
        } catch (Exception ex) {
            DialogosUtil.mostrarDialogoDeError(vista, ex.getMessage());
        }

    }

    private void eliminarUsuario(ActionEvent e) {
        if (!Objects.isNull(usuarioSeleccionado)) {
            usuarioService.eliminarUsuario(usuarioSeleccionado);
            actualizarTabla(usuarioService.obtenerUsuarios());
        }
    }

    private void establecerRoles() {
        JComboBox comboBoxActualizar = vista.getComboBoxRolesActualizar();
        JComboBox comboBoxRegristrar = vista.getComboBoxRolesRegistro();
        comboBoxRegristrar.removeAllItems();
        comboBoxRegristrar.addItem(Rol.ADMIN);
        comboBoxRegristrar.addItem(Rol.NORMAL);
        comboBoxActualizar.removeAllItems();
        comboBoxActualizar.addItem(Rol.ADMIN);
        comboBoxActualizar.addItem(Rol.NORMAL);

    }

}
