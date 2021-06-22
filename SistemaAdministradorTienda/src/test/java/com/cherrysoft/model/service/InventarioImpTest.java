/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.service;

import com.cherrysoft.model.data.Articulo;
import java.util.List;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
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
/*
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
    @IgnoreForBinding
    public void testObtenerTodosLosArticulos() {
        System.out.println("obtenerTodosLosArticulos");
        InventarioImp instance = new InventarioImp();
        List<Articulo> expResult = null;
        List<Articulo> result = instance.obtenerTodosLosArticulos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @IgnoreForBinding
    public void testEliminarArticulo() {
        System.out.println("eliminarArticulo");
        Articulo articulo = null;
        InventarioImp instance = new InventarioImp();
        Articulo expResult = null;
        Articulo result = instance.eliminarArticulo(articulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @IgnoreForBinding
    public void testActualizarArticulo_Articulo() {
        System.out.println("actualizarArticulo");
        Articulo articulo = null;
        InventarioImp instance = new InventarioImp();
        Articulo expResult = null;
        Articulo result = instance.actualizarArticulo(articulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @IgnoreForBinding
    public void testObtenerArticulosDisponibles() {
        System.out.println("obtenerArticulosDisponibles");
        InventarioImp instance = new InventarioImp();
        List<Articulo> expResult = null;
        List<Articulo> result = instance.obtenerArticulosDisponibles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @IgnoreForBinding
    public void testObtenerArticulosPorRangoCantidad() {
        System.out.println("obtenerArticulosPorRangoCantidad");
        Integer inferior = null;
        Integer superior = null;
        InventarioImp instance = new InventarioImp();
        List<Articulo> expResult = null;
        List<Articulo> result = instance.obtenerArticulosPorRangoCantidad(inferior, superior);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testRegistrarArticulosPorCsv() throws Exception {
        System.out.println("registrarArticulosPorCsv");
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

    @Test
    @IgnoreForBinding
    public void testActualizarArticulo_Integer_Integer() throws Exception {
        System.out.println("actualizarArticulo");
        Integer articuloId = null;
        Integer cantidad = null;
        InventarioImp instance = new InventarioImp();
        Articulo expResult = null;
        Articulo result = instance.actualizarArticulo(articuloId, cantidad);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
}
