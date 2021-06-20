package com.cherrysoft;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.ArticuloProveedor;
import com.cherrysoft.model.data.Proveedor;
import com.cherrysoft.model.repository.ArticuloRepository;
import com.cherrysoft.model.repository.ProveedorRepository;
import com.cherrysoft.model.service.InventarioImp;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //test
        InventarioImp t = new InventarioImp();
        List<Articulo> result = t.registrarArticulosPorCsv("C:\\Users\\IGNITER\\Desktop\\csb.txt");
        result.forEach((i) -> {
            System.out.println(i.getId());
            System.out.println(i.getCantidad());
            System.out.println(i.getDescripcion());
            System.out.println(i.getNombre());
            System.out.println(i.getPrecio());
        });
    }
}
