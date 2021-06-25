package com.cherrysoft.model.repository;

import com.cherrysoft.model.data.Cliente;
import java.util.stream.StreamSupport;

/**
 *
 * @author Victor Cauich
 */
public class ClienteRepository extends CrudRepository<Cliente, Integer> {

    public Cliente obtenerClientePorNombre(String cliente) {
        Cliente clnte = StreamSupport
                .stream(this.findAll().spliterator(), false)
                .filter((us) -> us.getNombre().equals(cliente))
                .findFirst().orElse(null);
        return clnte;
    }
}
