package com.cherrysoft.interfaces;

import com.cherrysoft.model.data.Permiso;
import com.cherrysoft.model.data.Rol;
import com.cherrysoft.model.data.Usuario;
import java.util.List;

/**
 *
 * @author Emmanuel Chable
 */
public interface UsuarioService {

    public Usuario login(String usuario, String password) throws Exception;

    //Operaciones del administrador
    public void registrarUsuario(String usuario, String password, String email, Rol rol) throws Exception;

    public Usuario actualizarDatosUsuario(Usuario usr, String usuario, String email, String password, Rol rol);

    public List<Usuario> obtenerUsuarios();

    public Usuario eliminarUsuario(Usuario usuario);

    public Usuario obtenerUsuarioPorId(Integer id) throws Exception;

}
