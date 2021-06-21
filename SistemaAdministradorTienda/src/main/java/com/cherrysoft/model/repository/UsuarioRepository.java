package com.cherrysoft.model.repository;

import com.cherrysoft.model.data.Usuario;
import java.util.stream.StreamSupport;

/**
 *
 * @author Emmanuel Chable
 */
public class UsuarioRepository extends CrudRepository<Usuario, Integer> {

    public Usuario obtenerUsuarioPor(String usuario) {
        Usuario usr = StreamSupport
                .stream(this.findAll().spliterator(), false)
                .filter((us) -> us.getUsuario().equals(usuario))
                .findFirst().orElse(null);
        return usr;
    }
}
