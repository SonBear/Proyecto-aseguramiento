package com.cherrysoft.model.service;

import com.cherrysoft.model.data.Articulo;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Emmanuel Chable
 */
public class InventarioImpTest {

    private final Faker faker = new Faker();

    public InventarioImpTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testObtenerTodosLosArticulos() {
        System.out.println("obtenerTodosLosArticulos");
        InventarioImp instance = new InventarioImp();
        instance.registrarArticulo(Integer.MAX_VALUE, faker.medical().medicineName(),
                faker.medical().symptoms(), BigDecimal.TEN, faker.number()
                .randomDigit());
        List<Articulo> result = instance.obtenerTodosLosArticulos();
        assertTrue(result.size() > 0);

    }

    @Test
    public void testEliminarArticulo() throws Exception {
        System.out.println("eliminarArticulo");

        InventarioImp instance = new InventarioImp();
        Articulo articulo = instance.obtenerArticuloPorId(Integer.MAX_VALUE);
        Articulo expResult = instance.eliminarArticulo(articulo);
        Articulo result = instance.eliminarArticulo(articulo);
        assertEquals(expResult, result);

    }

    @Test
    public void testActualizarArticulo_Articulo() {
        System.out.println("actualizarArticulo");
        InventarioImp instance = new InventarioImp();
        Articulo a = instance.registrarArticulo(Integer.MAX_VALUE, faker.medical().medicineName(), faker.medical().symptoms(), BigDecimal.TEN, faker.number().randomDigit());
        Articulo articulo = a;
        Articulo expResult = a;
        articulo.setDescripcion("D");
        expResult.setDescripcion("D");

        Articulo result = instance.actualizarArticulo(articulo);
        assertEquals(expResult, result);
    }

    @Test
    public void testObtenerArticulosDisponibles() {
        System.out.println("obtenerArticulosDisponibles");
        InventarioImp instance = new InventarioImp();
        List<Articulo> result = instance.obtenerArticulosDisponibles();
        result.forEach((r) -> {
            if (r.getCantidad() <= 0) {
                assertTrue(false);
            }
        });
    }

    @Test
    public void testObtenerArticulosPorRangoCantidad() {
        System.out.println("obtenerArticulosPorRangoCantidad");
        Integer inferior = 2;
        Integer superior = 4;
        InventarioImp instance = new InventarioImp();

        List<Articulo> result = instance.obtenerArticulosPorRangoCantidad(inferior, superior);
        result.forEach((r) -> {
            if (r.getCantidad() < inferior || r.getCantidad() > superior) {
                assertTrue(false);
            }
        });

    }

    @Test
    public void testActualizarArticulo_Integer_Integer() throws Exception {
        System.out.println("actualizarArticulo");
        Integer articuloId = Integer.MAX_VALUE;
        Integer cantidad = 12;
        InventarioImp instance = new InventarioImp();
        Articulo expResult = null;
        Articulo result = instance.actualizarArticulo(articuloId, cantidad);
        assertTrue(result.getCantidad() == cantidad);

    }

    @Test
    public void testRegistrarArticulosPorCsv() throws Exception {
        System.out.println("registrarArticulosPorCsv");
        //Cambiar por una ruta de archivo valida
        String rutaArchivo = "C:\\Users\\IGNITER\\Desktop\\csb.txt";
        InventarioImp instance = new InventarioImp();
        List<Articulo> result = instance.registrarArticulosPorCsv(rutaArchivo);
        result.forEach((i) -> {
            System.out.println(i.getId());
            System.out.println(i.getCantidad());
            System.out.println(i.getDescripcion());
            System.out.println(i.getNombre());
            System.out.println(i.getPrecio());
        });
        if (result.size() > 0) {
            System.out.println("Prueba ");

        }
    }

}
