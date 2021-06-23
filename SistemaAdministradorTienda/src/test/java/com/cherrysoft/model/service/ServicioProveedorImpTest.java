package com.cherrysoft.model.service;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.ArticuloProveedor;
import com.cherrysoft.model.data.CompraProveedor;
import com.cherrysoft.model.data.Proveedor;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.repository.ArticuloRepository;
import com.cherrysoft.util.PromocionesTestUtil;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Eusebio Ajas
 */
public class ServicioProveedorImpTest {

    private final Faker faker = new Faker();
    private static ServicioProveedoresImp servicioProveedores;
    private static ArticuloRepository articuloRepository;
    private static PromocionesTestUtil utilidades;

    public ServicioProveedorImpTest() {
        utilidades = new PromocionesTestUtil();
    }
    
    @BeforeAll
    public static void setup() {
        servicioProveedores = new ServicioProveedoresImp();
        articuloRepository = new ArticuloRepository();
        System.out.println("Proveedor repository creado");
    }

    @AfterAll
    public static void tearDown() {
        if (servicioProveedores != null) {
            servicioProveedores.getProveedorRepository()
                    .getEntityManager().clear();
        }

        if (articuloRepository != null) {
            articuloRepository.getEntityManager().clear();
        }

        System.out.println("Proveedor repository cerrado");
    }

    @Test
    public void testObtenerProveedorMenorPrecioPorArticulo() {
        System.out.println("Corriendo prueba proveedor menor precio por articulo...");
        crearRelacionArticuloProveedor();
        ArticuloProveedor articuloProveedorMenor = servicioProveedores.obtenerProveedorMenorPrecioArticulo(100);

        Assertions.assertEquals("John Doe", articuloProveedorMenor.getProveedor().getNombre());
    }

    @Test
    public void testObtenerProveedores() {
        System.out.println("Corriendo prueba listar proveedores...");

        List<Proveedor> proveedoresEsperados = new ArrayList<>();
        proveedoresEsperados.add(crearProveedor());
        proveedoresEsperados.add(crearProveedor());
        proveedoresEsperados.add(crearProveedor());
        proveedoresEsperados.add(crearProveedor());

        proveedoresEsperados.forEach(proveedor -> servicioProveedores
                .save(proveedor));

        Iterable<Proveedor> proveedoresActuales = servicioProveedores.obtenerProveedores();
        long count = StreamSupport.stream(proveedoresActuales.spliterator(), false).count();

        Assertions.assertEquals(7, count);
    }

    @Test
    public void testObtenerListaArticulosCompraProveedor() {
        System.out.println("Corriendo prueba articulos comprados a un proveedor...");
        Usuario usuario = new Usuario();
        usuario.setCorreo(faker.internet().emailAddress());
        usuario.setContrasenia(faker.internet().password());

        List<CompraProveedor> listaComprasProveedor = new ArrayList<>();

        Proveedor proveedor = crearProveedor();

        ArticuloProveedor articuloProveedor1 = crearArticuloProveedor(proveedor, utilidades.crearArticulo());
        ArticuloProveedor articuloProveedor2 = crearArticuloProveedor(proveedor, utilidades.crearArticulo());
        ArticuloProveedor articuloProveedor3 = crearArticuloProveedor(proveedor, utilidades.crearArticulo());

        List<ArticuloProveedor> articulosProveedor = new ArrayList<>();
        articulosProveedor.add(articuloProveedor1);
        articulosProveedor.add(articuloProveedor2);
        articulosProveedor.add(articuloProveedor3);

        proveedor.setProveedorArticulos(articulosProveedor);

        CompraProveedor compraProveedor1 = crearCompraProveedor(articuloProveedor1, usuario);
        CompraProveedor compraProveedor2 = crearCompraProveedor(articuloProveedor2, usuario);
        CompraProveedor compraProveedor3 = crearCompraProveedor(articuloProveedor3, usuario);

        listaComprasProveedor.add(compraProveedor1);
        listaComprasProveedor.add(compraProveedor2);
        listaComprasProveedor.add(compraProveedor3);

        usuario.setComprasProveedor(listaComprasProveedor);

        servicioProveedores.save(proveedor);
        servicioProveedores.saveUser(usuario);

        List<Articulo> articulosActuales = servicioProveedores
                .obtenerListaArticulosCompraProveedor(proveedor.getId(), usuario.getId());

        Assertions.assertEquals(3, articulosActuales.size());
    }
    
    @Test
    public void obtenerMontoPagoProveedor(){
        System.out.println("Corriendo prueba monto total de pago de articulos de un proveedor...");
        
        Usuario usuario = new Usuario();
        usuario.setCorreo(faker.internet().emailAddress());
        usuario.setContrasenia(faker.internet().password());

        List<CompraProveedor> listaComprasProveedor = new ArrayList<>();

        Proveedor proveedor = crearProveedor();

        ArticuloProveedor articuloProveedor1 = crearArticuloProveedor(proveedor, crearArticulo());
        ArticuloProveedor articuloProveedor2 = crearArticuloProveedor(proveedor, crearArticulo());

        List<ArticuloProveedor> articulosProveedor = new ArrayList<>();
        articulosProveedor.add(articuloProveedor1);
        articulosProveedor.add(articuloProveedor2);
        proveedor.setProveedorArticulos(articulosProveedor);

        CompraProveedor compraProveedor = crearCompraProveedor(articuloProveedor1, usuario);
        CompraProveedor compraProveedor2 = crearCompraProveedor(articuloProveedor2, usuario);
        
        listaComprasProveedor.add(compraProveedor);
        listaComprasProveedor.add(compraProveedor2);

        usuario.setComprasProveedor(listaComprasProveedor);

        servicioProveedores.save(proveedor);
        servicioProveedores.saveUser(usuario);
        
        BigDecimal montoEsperado = BigDecimal.valueOf(80); 
        BigDecimal montoActual = servicioProveedores.obtenerMontoPagoProveedor(proveedor.getId(), usuario.getId());
        
        Assertions.assertEquals(montoEsperado, montoActual);
    }

    private CompraProveedor crearCompraProveedor(ArticuloProveedor articuloProveedor, Usuario usuario) {
        CompraProveedor compraProveedor = new CompraProveedor();
        compraProveedor.setArticuloProveedor(articuloProveedor);
        compraProveedor.setCantidad(4);
        compraProveedor.calcularCostoTotal();
        compraProveedor.setUsuario(usuario);

        return compraProveedor;
    }

    private ArticuloProveedor crearArticuloProveedor(Proveedor proveedor, Articulo articulo) {
        ArticuloProveedor articuloProveedor = new ArticuloProveedor();
        articuloProveedor.setProveedor(proveedor);
        articuloProveedor.setArticulo(articulo);
        articuloProveedor.setCantidad(4);
        articuloProveedor.setPrecio(BigDecimal.valueOf(10));

        return articuloProveedor;
    }

    private void crearRelacionArticuloProveedor() {
        Articulo articulo = crearArticulo();
        articulo.setId(100);
        articuloRepository.save(articulo);

        // proveedor 1
        ArticuloProveedor articuloProveedor = new ArticuloProveedor();
        Proveedor proveedor1 = crearProveedor();
        proveedor1.setNombre("John Doe");
        articuloProveedor.setArticulo(articulo);
        articuloProveedor.setCantidad(articulo.getCantidad());
        articuloProveedor.setProveedor(proveedor1);
        articuloProveedor.setPrecio(BigDecimal.valueOf(8));

        List<ArticuloProveedor> articulosProveedor1 = new ArrayList<>();
        articulosProveedor1.add(articuloProveedor);
        proveedor1.setProveedorArticulos(articulosProveedor1);
        servicioProveedores.save(proveedor1);

        // Proveedor 2
        ArticuloProveedor articuloProveedor2 = new ArticuloProveedor();
        Proveedor proveedor2 = crearProveedor();
        articuloProveedor2.setArticulo(articulo);
        articuloProveedor2.setCantidad(articulo.getCantidad());
        articuloProveedor2.setProveedor(proveedor2);
        articuloProveedor2.setPrecio(BigDecimal.valueOf(57));

        List<ArticuloProveedor> articulosProveedor2 = new ArrayList<>();
        articulosProveedor2.add(articuloProveedor2);
        proveedor2.setProveedorArticulos(articulosProveedor2);
        servicioProveedores.save(proveedor2);

        // Proveedor 3
        ArticuloProveedor articuloProveedor3 = new ArticuloProveedor();
        Proveedor proveedor3 = crearProveedor();
        articuloProveedor3.setArticulo(articulo);
        articuloProveedor3.setCantidad(articulo.getCantidad());
        articuloProveedor3.setProveedor(proveedor3);
        articuloProveedor3.setPrecio(BigDecimal.valueOf(30));

        List<ArticuloProveedor> articulosProveedor3 = new ArrayList<>();
        articulosProveedor3.add(articuloProveedor3);
        proveedor3.setProveedorArticulos(articulosProveedor3);
        servicioProveedores.save(proveedor3);
    }

    private Articulo crearArticulo() {
        Articulo articulo = new Articulo();
        articulo.setId(faker.random().nextInt(1, 17));
        articulo.setNombre(faker.commerce().productName());
        articulo.setPrecio(new BigDecimal(faker.number().randomDouble(2, 10, 150)));
        articulo.setCantidad(faker.random().nextInt(1, 10));
        articulo.setDescripcion(faker.lorem().paragraph());

        return articulo;
    }

    private Proveedor crearProveedor() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(faker.artist().name());
        proveedor.setDireccion(faker.address().fullAddress());
        proveedor.setTelefono(faker.phoneNumber().cellPhone());
        proveedor.setEmail(faker.internet().emailAddress());

        return proveedor;
    }
}
