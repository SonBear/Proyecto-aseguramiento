package com.cherrysoft;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.ArticuloProveedor;
import com.cherrysoft.model.data.Proveedor;
import com.cherrysoft.model.repository.ArticuloRepository;
import com.cherrysoft.model.repository.ProveedorRepository;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) {
        //ConfiguracionSistema.realizarConfiguracion();
        //test
        Articulo articulo = new Articulo();
        articulo.setDescripcion("refresco sabor limon");
        articulo.setCantidad(12);
        articulo.setNombre("Lima limon");
        articulo.setPrecio(BigDecimal.TEN);

        ArticuloRepository articuloRepository = new ArticuloRepository();
        articuloRepository.save(articulo);

        Proveedor proveedor = new Proveedor();
        proveedor.setDireccion("C 56 y F");
        proveedor.setEmail("email@Gmail.com");
        proveedor.setTelefono("+52 001992");

        ProveedorRepository proveedorRepository = new ProveedorRepository();
        proveedorRepository.save(proveedor);

        ArticuloProveedor articuloProveedor = new ArticuloProveedor();
        articuloProveedor.setArticulo(articulo);
        articuloProveedor.setProveedor(proveedor);
        articuloProveedor.setPrecio(BigDecimal.valueOf(80));
        articuloProveedor.setCantidad(2);

        proveedor.setProveedorArticulos(Arrays.asList(articuloProveedor));
        proveedorRepository.save(proveedor);

        Proveedor p = proveedorRepository.findById(1).get();
        System.out.println(p.getProveedorArticulos().get(0).getArticulo().getNombre());

    }
}
