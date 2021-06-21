/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.repository;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.PromocionArticuloCompra;
import com.cherrysoft.model.data.PromocionArticuloRegaloPorCompras;
import com.cherrysoft.model.data.PromocionDescuentoArticuloPorCompras;
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
public class PromocionDescuentoArticuloPorComprasRepositoryTest {
    private static PromocionDescuentoArticuloPorComprasRepository promocionTipo3Repository;
    private static PromocionesTestUtil utilidades;
    private ArticuloRepository articulosRepository;    
    
    public PromocionDescuentoArticuloPorComprasRepositoryTest() {
        utilidades = new PromocionesTestUtil();
        utilidades.iniciarArticulos();
        promocionTipo3Repository = new PromocionDescuentoArticuloPorComprasRepository();        
        articulosRepository = new ArticuloRepository();            
    }
    
    @BeforeAll
    public static void setUpClass() {
        utilidades.borrarTodasLasPromociones(promocionTipo3Repository);                
    }
    
    @AfterAll
    public static void tearDownClass() {
        utilidades.borrarTodasLasPromociones(promocionTipo3Repository);                        
    }
    
    @BeforeEach
    public void setUp() {
        promocionTipo3Repository = new PromocionDescuentoArticuloPorComprasRepository(); 
    }
    
    @AfterEach
    public void tearDown() {
        if (promocionTipo3Repository != null) {
            promocionTipo3Repository.getEntityManager().close();
        }
        System.out.println("Promocion tipo3 repository cerrado");               
    }
    
    @Test
    public void testFindAll() {
        System.out.println("Ejecutando prueba listar promociones tipo 3");
        utilidades.borrarTodasLasPromociones(promocionTipo3Repository);     

        List<PromocionDescuentoArticuloPorCompras> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(utilidades.crearPromocionTipo3());
        promocionesEsperadas.add(utilidades.crearPromocionTipo3());
        promocionesEsperadas.add(utilidades.crearPromocionTipo3());

        promocionesEsperadas.forEach(promo -> promocionTipo3Repository.save(promo));

        Iterable<PromocionDescuentoArticuloPorCompras> promocionesActuales = promocionTipo3Repository.findAll();
        
        assertIterableEquals(promocionesEsperadas, promocionesActuales);
    }    
    
    @Test
    public void testfindById() {
        System.out.println("Ejecutando prueba buscar promocion tipo 3 por ID");
        utilidades.borrarTodasLasPromociones(promocionTipo3Repository);             
        
        PromocionDescuentoArticuloPorCompras promocionEsperada = utilidades.crearPromocionTipo3();
        promocionTipo3Repository.save(promocionEsperada);

        Optional<PromocionDescuentoArticuloPorCompras> promocionActual = promocionTipo3Repository.findById(promocionEsperada.getId());
        assertEquals(promocionEsperada.getId(), promocionActual.get().getId());
    }      
    
    @Test
    public void testSave() {
        System.out.println("Ejecutando prueba guardar una promocion tipo 3...");
        utilidades.borrarTodasLasPromociones(promocionTipo3Repository);             
        
        PromocionDescuentoArticuloPorCompras promocionEsperada = utilidades.crearPromocionTipo3();
        promocionTipo3Repository.save(promocionEsperada);
        Optional<PromocionDescuentoArticuloPorCompras> promocionActual = promocionTipo3Repository.findById(promocionEsperada.getId());        

        assertTrue(promocionActual.get() != null);
    }       
    
    @Test
    public void testDelete() {
        System.out.println("Ejecutando prueba borrar una promocion tipo 3 de la bd");    
        utilidades.borrarTodasLasPromociones(promocionTipo3Repository);        

        List<PromocionDescuentoArticuloPorCompras> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(utilidades.crearPromocionTipo3());
        promocionesEsperadas.add(utilidades.crearPromocionTipo3());
        promocionesEsperadas.add(utilidades.crearPromocionTipo3());

        promocionesEsperadas.forEach(promo -> promocionTipo3Repository.save(promo));        

        final int ID_ELIMINAR = promocionesEsperadas.get(1).getId();
        PromocionDescuentoArticuloPorCompras promoEncontrada = promocionTipo3Repository.findById(ID_ELIMINAR).get();
        promocionTipo3Repository.delete(promoEncontrada);
        Optional<PromocionDescuentoArticuloPorCompras> promoEliminada = promocionTipo3Repository.findById(ID_ELIMINAR);
        assertFalse(promoEliminada.isPresent());
    }      
    
    @Test
    public void testDeleteById() {
        System.out.println("Ejecutando prueba eliminar promocion tipo 3 por Id...");
        
        PromocionDescuentoArticuloPorCompras promocion = utilidades.crearPromocionTipo3();
        promocionTipo3Repository.save(promocion);
        
        final int ID_ELIMINAR = promocion.getId();
        promocionTipo3Repository.deleteById(ID_ELIMINAR);        
        
        Optional<PromocionDescuentoArticuloPorCompras> promoEliminada = promocionTipo3Repository.findById(ID_ELIMINAR);
        assertFalse(promoEliminada.isPresent());
    }    
    
    @Test
    public void testUpdate() {
        System.out.println("Ejecutando prueba actualizar una promocion tipo 3...");

        PromocionDescuentoArticuloPorCompras promocion = utilidades.crearPromocionTipo3();
        promocionTipo3Repository.save(promocion);
        
        PromocionArticuloCompra promoArticulo = new PromocionArticuloCompra();         
        Articulo articuloAAgregar = utilidades.crearArticulo();
        articulosRepository.save(articuloAAgregar);
        promoArticulo.setArticulo(articuloAAgregar);
        promoArticulo.setPromocion(promocion);
        
        List<PromocionArticuloCompra> articulosPromocion = promocion.getArticulosCompraPromocion();
        articulosPromocion.add(promoArticulo);
        promocion.setArticulosCompraPromocion(articulosPromocion);
        
        promocionTipo3Repository.update(promocion);
        PromocionDescuentoArticuloPorCompras promocionActualizada = promocionTipo3Repository.findById(promocion.getId()).get();
        
        Articulo articuloPromocionEsperada = promocion.getArticulosCompraPromocion().get(2).getArticulo();
        Articulo articuloPromocionActual = promocionActualizada.getArticulosCompraPromocion().get(2).getArticulo();        
        
        assertEquals(articuloPromocionEsperada.getNombre(), articuloPromocionActual.getNombre());
    }        
    
}
