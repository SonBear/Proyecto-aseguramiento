package com.cherrysoft.model.repository;

import com.cherrysoft.model.data.Cliente;
import java.util.stream.StreamSupport;

/**
 *
 * @author Emmanuel Chable
 */
public class ClienteRepository extends CrudRepository<Cliente, Integer> {

    public Cliente obtenerClientePor(String cliente) {
        Cliente clnte = StreamSupport
                .stream(this.findAll().spliterator(), false)
                .filter((us) -> us.getNombre().equals(cliente))
                .findFirst().orElse(null);
        return clnte;
    }
}
