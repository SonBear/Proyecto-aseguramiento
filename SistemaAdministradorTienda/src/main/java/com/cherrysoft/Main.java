package com.cherrysoft;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.ArticuloProveedor;
import com.cherrysoft.model.data.Proveedor;
import com.cherrysoft.model.repository.ArticuloRepository;
import com.cherrysoft.model.repository.ProveedorRepository;
import com.cherrysoft.model.repository.UsuarioRepository;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) {
        //test
        UsuarioRepository us = new UsuarioRepository();
        System.out.println(us.findById(1).get().getNombre());

    }
}
