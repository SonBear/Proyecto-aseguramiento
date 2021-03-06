/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.repository;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.PromocionArticuloCompra;
import com.cherrysoft.model.data.PromocionArticuloRegaloPorCompras;
import com.cherrysoft.util.PromocionesTestUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Equipo1
 */
public class PromocionArticuloRegaloPorComprasRepositoryTest {

    private static PromocionArticuloRegaloPorComprasRepository promocionTipo2Repository;
    private static PromocionesTestUtil utilidades;
    private ArticuloRepository articulosRepository;

    public PromocionArticuloRegaloPorComprasRepositoryTest() {
        articulosRepository = new ArticuloRepository();
    }

    @BeforeAll
    public static void setUpClass() {
        promocionTipo2Repository = new PromocionArticuloRegaloPorComprasRepository();
        utilidades = new PromocionesTestUtil();
        utilidades.borrarTodasLasPromociones(promocionTipo2Repository);
        utilidades.iniciarArticulos();
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
    public void testFindAll() {
        System.out.println("Ejecutando prueba listar promociones tipo 2");
        utilidades.borrarTodasLasPromociones(promocionTipo2Repository);

        List<PromocionArticuloRegaloPorCompras> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(utilidades.crearPromocionTipo2());
        promocionesEsperadas.add(utilidades.crearPromocionTipo2());
        promocionesEsperadas.add(utilidades.crearPromocionTipo2());

        promocionesEsperadas.forEach(promo -> promocionTipo2Repository.save(promo));

        Iterable<PromocionArticuloRegaloPorCompras> promocionesActuales = promocionTipo2Repository.findAll();

        assertIterableEquals(promocionesEsperadas, promocionesActuales);
    }

    @Test
    public void testfindById() {
        System.out.println("Ejecutando prueba buscar promocion tipo 2 por ID");
        utilidades.borrarTodasLasPromociones(promocionTipo2Repository);

        PromocionArticuloRegaloPorCompras promocionEsperada = utilidades.crearPromocionTipo2();
        promocionTipo2Repository.save(promocionEsperada);

        Optional<PromocionArticuloRegaloPorCompras> promocionActual = promocionTipo2Repository.findById(promocionEsperada.getId());
        assertEquals(promocionEsperada.getId(), promocionActual.get().getId());
    }

    @Test
    public void testSave() {
        System.out.println("Ejecutando prueba guardar una promocion tipo 2...");
        utilidades.borrarTodasLasPromociones(promocionTipo2Repository);

        PromocionArticuloRegaloPorCompras promocionEsperada = utilidades.crearPromocionTipo2();
        promocionTipo2Repository.save(promocionEsperada);
        Optional<PromocionArticuloRegaloPorCompras> promocionActual = promocionTipo2Repository.findById(promocionEsperada.getId());

        assertTrue(promocionActual.get() != null);
    }

    @Test
    public void testDelete() {
        System.out.println("Ejecutando prueba borrar una promocion tipo 2 de la bd");
        utilidades.borrarTodasLasPromociones(promocionTipo2Repository);

        List<PromocionArticuloRegaloPorCompras> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(utilidades.crearPromocionTipo2());
        promocionesEsperadas.add(utilidades.crearPromocionTipo2());
        promocionesEsperadas.add(utilidades.crearPromocionTipo2());

        promocionesEsperadas.forEach(promo -> promocionTipo2Repository.save(promo));

        final int ID_ELIMINAR = promocionesEsperadas.get(1).getId();
        PromocionArticuloRegaloPorCompras promoEncontrada = promocionTipo2Repository.findById(ID_ELIMINAR).get();
        promocionTipo2Repository.delete(promoEncontrada);
        Optional<PromocionArticuloRegaloPorCompras> promoEliminada = promocionTipo2Repository.findById(ID_ELIMINAR);
        assertFalse(promoEliminada.isPresent());
    }

    @Test
    public void testDeleteById() {
        System.out.println("Ejecutando prueba eliminar promocion tipo 2 por Id...");

        PromocionArticuloRegaloPorCompras promocion = utilidades.crearPromocionTipo2();
        promocionTipo2Repository.save(promocion);

        final int ID_ELIMINAR = promocion.getId();
        promocionTipo2Repository.deleteById(ID_ELIMINAR);

        Optional<PromocionArticuloRegaloPorCompras> promoEliminada = promocionTipo2Repository.findById(ID_ELIMINAR);
        assertFalse(promoEliminada.isPresent());
    }

    @Test
    public void testUpdate() {
        System.out.println("Ejecutando prueba actualizar una promocion tipo 2...");

        PromocionArticuloRegaloPorCompras promocion = utilidades.crearPromocionTipo2();
        promocionTipo2Repository.save(promocion);

        PromocionArticuloCompra promoArticulo = new PromocionArticuloCompra();
        Articulo articuloAAgregar = utilidades.crearArticulo();
        articulosRepository.save(articuloAAgregar);
        promoArticulo.setArticulo(articuloAAgregar);
        promoArticulo.setPromocion(promocion);

        List<PromocionArticuloCompra> articulosPromocion = promocion.getArticulosCompraPromocion();
        articulosPromocion.add(promoArticulo);
        promocion.setArticulosCompraPromocion(articulosPromocion);

        promocionTipo2Repository.update(promocion);
        PromocionArticuloRegaloPorCompras promocionActualizada = promocionTipo2Repository.findById(promocion.getId()).get();

        Articulo articuloPromocionEsperada = promocion.getArticulosCompraPromocion().get(2).getArticulo();
        Articulo articuloPromocionActual = promocionActualizada.getArticulosCompraPromocion().get(2).getArticulo();

        assertEquals(articuloPromocionEsperada.getNombre(), articuloPromocionActual.getNombre());
    }

}
