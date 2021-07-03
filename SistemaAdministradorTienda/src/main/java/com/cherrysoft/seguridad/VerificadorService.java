package com.cherrysoft.seguridad;

import com.cherrysoft.model.data.Usuario;

/**
 *
 * @author Emmanuel Chable
 */
public interface VerificadorService {

    public boolean esUsuarioAdmin(Usuario usuario);

    public void guardarAccionUsuario(Usuario usuario, String accion);
}
