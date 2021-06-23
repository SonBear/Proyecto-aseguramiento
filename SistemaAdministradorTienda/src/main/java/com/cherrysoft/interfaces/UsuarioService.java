package com.cherrysoft.interfaces;

import com.cherrysoft.model.data.Rol;
import com.cherrysoft.model.data.Usuario;
import java.util.List;

/**
 * Servicio de usuarios administra, a los usuarios del sistema
 *
 * @author Emmanuel Chable
 */
public interface UsuarioService {

    /**
     *
     * @param usuario
     * @param password
     * @return
     * @throws Exception
     */
    public Usuario login(String usuario, String password) throws Exception;

    /**
     *
     * @param usuario
     * @param password
     * @param email
     * @param rol
     * @throws Exception
     */
    public void registrarUsuario(String usuario, String password, String email, Rol rol) throws Exception;

    /**
     *
     * @param usr
     * @param usuario
     * @param email
     * @param password
     * @param rol
     * @return
     */
    public Usuario actualizarDatosUsuario(Usuario usr, String usuario, String email, String password, Rol rol);

    /**
     *
     * @return
     */
    public List<Usuario> obtenerUsuarios();

    /**
     *
     * @param usuario
     * @return
     */
    public Usuario eliminarUsuario(Usuario usuario);

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Usuario obtenerUsuarioPorId(Integer id) throws Exception;

}
