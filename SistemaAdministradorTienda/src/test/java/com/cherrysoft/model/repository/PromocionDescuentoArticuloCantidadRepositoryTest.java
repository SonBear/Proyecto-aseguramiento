/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.repository;

import com.cherrysoft.model.data.PromocionDescuentoArticuloCantidad;
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
public class PromocionDescuentoArticuloCantidadRepositoryTest {
    private static PromocionDescuentoArticuloCantidadRepository promocionTipo1Repository;
    private static PromocionesTestUtil utilidades;
    private ArticuloRepository articulosRepository;    
    
    public PromocionDescuentoArticuloCantidadRepositoryTest() {
        utilidades = new PromocionesTestUtil();
        utilidades.iniciarArticulos();
        promocionTipo1Repository = new PromocionDescuentoArticuloCantidadRepository();        
        articulosRepository = new ArticuloRepository();            
    }
    
    @BeforeAll
    public static void setUpClass() {
        utilidades.borrarTodasLasPromociones(promocionTipo1Repository);                
    }
    
    @AfterAll
    public static void tearDownClass() {
        utilidades.borrarTodasLasPromociones(promocionTipo1Repository);        
    }    
    
    @BeforeEach
    public void setUp() {
        promocionTipo1Repository = new PromocionDescuentoArticuloCantidadRepository(); 
    }
    
    @AfterEach
    public void tearDown() {
        if (promocionTipo1Repository != null) {
            promocionTipo1Repository.getEntityManager().close();
        }
        System.out.println("Promocion tipo1 repository cerrado");        
    }

    @Test
    public void testFindAll() {
        System.out.println("Ejecutando prueba listar promociones tipo 1");
        utilidades.borrarTodasLasPromociones(promocionTipo1Repository);     

        List<PromocionDescuentoArticuloCantidad> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(utilidades.crearPromocionTipo1());
        promocionesEsperadas.add(utilidades.crearPromocionTipo1());
        promocionesEsperadas.add(utilidades.crearPromocionTipo1());

        promocionesEsperadas.forEach(promo -> promocionTipo1Repository.save(promo));

        Iterable<PromocionDescuentoArticuloCantidad> promocionesActuales = promocionTipo1Repository.findAll();
        
        assertIterableEquals(promocionesEsperadas, promocionesActuales);
    }
    
    @Test
    public void testfindById() {
        System.out.println("Ejecutando prueba buscar promocion tipo 1 por ID");
        utilidades.borrarTodasLasPromociones(promocionTipo1Repository);             
        
        PromocionDescuentoArticuloCantidad promocionEsperada = utilidades.crearPromocionTipo1();
        promocionTipo1Repository.save(promocionEsperada);

        Optional<PromocionDescuentoArticuloCantidad> promocionActual = promocionTipo1Repository.findById(promocionEsperada.getId());
        assertEquals(promocionEsperada.getId(), promocionActual.get().getId());
    }      
    
    @Test
    public void testSave() {
        System.out.println("Ejecutando prueba guardar una promocion tipo 1");
        utilidades.borrarTodasLasPromociones(promocionTipo1Repository);             
        
        PromocionDescuentoArticuloCantidad promocionEsperada = utilidades.crearPromocionTipo1();
        promocionTipo1Repository.save(promocionEsperada);
        Optional<PromocionDescuentoArticuloCantidad> promocionActual = promocionTipo1Repository.findById(promocionEsperada.getId());        

        assertTrue(promocionActual.get() != null);
    }       
    
    @Test
    public void testDelete() {
        System.out.println("Ejecutando prueba borrar una promocion tipo 1 de la bd");    
        utilidades.borrarTodasLasPromociones(promocionTipo1Repository);        

        List<PromocionDescuentoArticuloCantidad> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(utilidades.crearPromocionTipo1());
        promocionesEsperadas.add(utilidades.crearPromocionTipo1());
        promocionesEsperadas.add(utilidades.crearPromocionTipo1());

        promocionesEsperadas.forEach(promo -> promocionTipo1Repository.save(promo));        

        final int ID_ELIMINAR = promocionesEsperadas.get(1).getId();
        PromocionDescuentoArticuloCantidad promoEncontrada = promocionTipo1Repository.findById(ID_ELIMINAR).get();
        promocionTipo1Repository.delete(promoEncontrada);
        Optional<PromocionDescuentoArticuloCantidad> promoEliminada = promocionTipo1Repository.findById(ID_ELIMINAR);
        assertFalse(promoEliminada.isPresent());
    }      
    
    @Test
    public void testDeleteById() {
        System.out.println("Ejecutando prueba eliminar promocion tipo 1 por Id...");
        
        PromocionDescuentoArticuloCantidad promocion = utilidades.crearPromocionTipo1();
        promocionTipo1Repository.save(promocion);
        
        final int ID_ELIMINAR = promocion.getId();
        promocionTipo1Repository.deleteById(ID_ELIMINAR);        
        
        Optional<PromocionDescuentoArticuloCantidad> promoEliminada = promocionTipo1Repository.findById(ID_ELIMINAR);
        assertFalse(promoEliminada.isPresent());
    }        

    @Test
    public void testUpdate() {
        System.out.println("Ejecutando prueba actualizar una promocion tipo 1...");

        PromocionDescuentoArticuloCantidad promocion = utilidades.crearPromocionTipo1();
        promocionTipo1Repository.save(promocion);
        
        promocion.setDescuento(0.75);
        promocionTipo1Repository.update(promocion);
        
        PromocionDescuentoArticuloCantidad promocionActualizada = promocionTipo1Repository.findById(promocion.getId()).get();

        assertEquals(promocion.getDescuento(), promocionActualizada.getDescuento());
    }    
    
}
