package com.cherrysoft;

import com.cherrysoft.controlador.ControladorPromociones;
import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.ArticuloProveedor;
import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.data.Promocion;
import com.cherrysoft.model.data.PromocionArticuloCompra;
import com.cherrysoft.model.data.PromocionArticuloRegaloPorCompras;
import com.cherrysoft.model.data.Proveedor;
import com.cherrysoft.model.repository.ArticuloRepository;
import com.cherrysoft.model.repository.ClienteRepository;
import com.cherrysoft.model.repository.ProveedorRepository;
import com.cherrysoft.model.service.ServicioPromocionesImp;
import com.cherrysoft.vistas.VistaPromociones;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) {
        //test
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Carlos Chan");
        cliente1.setCorreo("Carlos@correo.com");
        
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Emmanuel Chable");
        cliente2.setCorreo("Emman@correo.com");        
        
        ClienteRepository clienteRepository = new ClienteRepository();
        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);
        
        Articulo articulo = new Articulo();
        articulo.setDescripcion("refresco sabor limon");
        articulo.setCantidad(12);
        articulo.setNombre("Lima limon");
        articulo.setPrecio(BigDecimal.TEN);
        
        Articulo articulo2 = new Articulo();
        articulo2.setDescripcion("Gansito xd");
        articulo2.setCantidad(10);
        articulo2.setNombre("Gansito");
        articulo2.setPrecio(BigDecimal.valueOf(15));        

        Articulo articulo3 = new Articulo();
        articulo3.setDescripcion("Doritos nacho");
        articulo3.setCantidad(5);
        articulo3.setNombre("Doritos");
        articulo3.setPrecio(BigDecimal.valueOf(12));             
        
        ArticuloRepository articuloRepository = new ArticuloRepository();
        articuloRepository.save(articulo);
        articuloRepository.save(articulo2);
        articuloRepository.save(articulo3);        

        Proveedor proveedor = new Proveedor();
        proveedor.setDireccion("C 56 y F");
        proveedor.setEmail("email@Gmail.com");
        proveedor.setTelefono("+52 001992");
        proveedor.setNombre("Proveedor 1");

        ProveedorRepository proveedorRepository = new ProveedorRepository();
        proveedorRepository.save(proveedor);

        ArticuloProveedor articuloProveedor = new ArticuloProveedor();
        articuloProveedor.setArticulo(articulo);
        articuloProveedor.setProveedor(proveedor);
        articuloProveedor.setPrecio(BigDecimal.valueOf(80));
        articuloProveedor.setCantidad(2);
        
        ArticuloProveedor articuloProveedor2 = new ArticuloProveedor();
        articuloProveedor2.setArticulo(articulo2);
        articuloProveedor2.setProveedor(proveedor);
        articuloProveedor2.setPrecio(BigDecimal.valueOf(15));
        articuloProveedor2.setCantidad(1);     
        
        ArticuloProveedor articuloProveedor3 = new ArticuloProveedor();
        articuloProveedor3.setArticulo(articulo3);
        articuloProveedor3.setProveedor(proveedor);
        articuloProveedor3.setPrecio(BigDecimal.valueOf(12));
        articuloProveedor3.setCantidad(3);            
        
        //proveedor.setProveedorArticulos(Arrays.asList(articuloProveedor));
        proveedor.agregarArticulo(articuloProveedor);
        proveedor.agregarArticulo(articuloProveedor2);
        proveedor.agregarArticulo(articuloProveedor3);
        proveedorRepository.save(proveedor);

        Proveedor p = proveedorRepository.findById(1).get();
        Iterable<Proveedor> lista = proveedorRepository.findAll();
        lista.forEach(prov -> {
            System.out.println(prov.getNombre());
        });
        //System.out.println(p.getNombre());
        //System.out.println(p.getProveedorArticulos().get(0).getArticulo().getNombre());
    
        /******************************************
        
            PRUEBAS DE PROMOCIONES
        
        ******************************************/
        ServicioPromocionesImp servicio = new ServicioPromocionesImp();
        
        // Promocion Tipo1
        servicio.crearPromocionTipo1(articulo2, 1, 0.1, new Date(2021, 6, 30), new Date(2021, 7, 15));
        
        // Promocion Tipo2
        List<PromocionArticuloCompra> promo2Articulos = new ArrayList();
        PromocionArticuloCompra promo2Art1 = new PromocionArticuloCompra();
        promo2Art1.setArticulo(articulo);
        
        PromocionArticuloCompra promo2Art2 = new PromocionArticuloCompra();
        promo2Art2.setArticulo(articulo2);
        
        promo2Articulos.add(promo2Art1);
        promo2Articulos.add(promo2Art2);
        
        servicio.crearPromocionTipo2(promo2Articulos, articulo3, new Date(2021, 6, 30), new Date(2021, 7, 15));
        
        // Promocion Tipo3        
        List<PromocionArticuloCompra> promo3Articulos = new ArrayList();
        PromocionArticuloCompra promo3Art1 = new PromocionArticuloCompra();
        promo3Art1.setArticulo(articulo2);
        
        PromocionArticuloCompra promo3Art2 = new PromocionArticuloCompra();
        promo3Art2.setArticulo(articulo3);
        
        promo3Articulos.add(promo3Art1);
        promo3Articulos.add(promo3Art2);
        
        servicio.crearPromocionTipo3(0.3, promo3Articulos, new Date(2021, 6, 30), new Date(2021, 7, 15));   
        
        // Promocion Tipo4
        servicio.crearPromocionTipo4(0.05, new Date(2021, 6, 30), new Date(2021, 7, 15));
        
        // Obtener promociones
        List<Promocion> promociones = servicio.obtenerPromociones();
        
        // Eliminar promociones
        //servicio.eliminarPromocion(promociones.get(2));
        
        // Asociar promocion a un cliente
        servicio.asignarPromocionCliente(promociones.get(1), cliente1);
        
        /*
        
            PROBANDO LA VISTA
        
        */
        VistaPromociones vista = new VistaPromociones();
        ControladorPromociones control = new ControladorPromociones(vista);
        vista.setVisible(true);
    }
}
