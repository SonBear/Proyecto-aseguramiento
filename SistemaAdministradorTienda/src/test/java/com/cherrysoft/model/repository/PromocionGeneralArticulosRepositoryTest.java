/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.repository;

import com.cherrysoft.model.data.PromocionGeneralArticulos;
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
public class PromocionGeneralArticulosRepositoryTest {
    private static PromocionGeneralArticulosRepository promocionTipo4Repository;
    private static PromocionesTestUtil utilidades;
    private ArticuloRepository articulosRepository;       
    
    public PromocionGeneralArticulosRepositoryTest() {
        articulosRepository = new ArticuloRepository();               
    }
    
    @BeforeAll
    public static void setUpClass() {
        promocionTipo4Repository = new PromocionGeneralArticulosRepository();                
        utilidades = new PromocionesTestUtil();        
        utilidades.borrarTodasLasPromociones(promocionTipo4Repository); 
        utilidades.iniciarArticulos();        
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (promocionTipo4Repository != null) {
            promocionTipo4Repository.getEntityManager().close();
        }
        System.out.println("Promocion tipo4 repository cerrado");                                 
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
          
    }

    @Test
    public void testFindAll() {
        System.out.println("Ejecutando prueba listar promociones tipo 4");
        utilidades.borrarTodasLasPromociones(promocionTipo4Repository);     

        List<PromocionGeneralArticulos> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(utilidades.crearPromocionTipo4());
        promocionesEsperadas.add(utilidades.crearPromocionTipo4());
        promocionesEsperadas.add(utilidades.crearPromocionTipo4());

        promocionesEsperadas.forEach(promo -> promocionTipo4Repository.save(promo));

        Iterable<PromocionGeneralArticulos> promocionesActuales = promocionTipo4Repository.findAll();
        
        assertIterableEquals(promocionesEsperadas, promocionesActuales);
    }    
    
    @Test
    public void testfindById() {
        System.out.println("Ejecutando prueba buscar promocion tipo 4 por ID");
        utilidades.borrarTodasLasPromociones(promocionTipo4Repository);             
        
        PromocionGeneralArticulos promocionEsperada = utilidades.crearPromocionTipo4();
        promocionTipo4Repository.save(promocionEsperada);

        Optional<PromocionGeneralArticulos> promocionActual = promocionTipo4Repository.findById(promocionEsperada.getId());
        assertEquals(promocionEsperada.getId(), promocionActual.get().getId());
    }         
    
    @Test
    public void testSave() {
        System.out.println("Ejecutando prueba guardar una promocion tipo 4...");
        utilidades.borrarTodasLasPromociones(promocionTipo4Repository);             
        
        PromocionGeneralArticulos promocionEsperada = utilidades.crearPromocionTipo4();
        promocionTipo4Repository.save(promocionEsperada);
        Optional<PromocionGeneralArticulos> promocionActual = promocionTipo4Repository.findById(promocionEsperada.getId());        

        assertTrue(promocionActual.get() != null);
    }        
    
    @Test
    public void testDelete() {
        System.out.println("Ejecutando prueba borrar una promocion tipo 4 de la bd");    
        utilidades.borrarTodasLasPromociones(promocionTipo4Repository);        

        List<PromocionGeneralArticulos> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(utilidades.crearPromocionTipo4());
        promocionesEsperadas.add(utilidades.crearPromocionTipo4());
        promocionesEsperadas.add(utilidades.crearPromocionTipo4());

        promocionesEsperadas.forEach(promo -> promocionTipo4Repository.save(promo));        

        final int ID_ELIMINAR = promocionesEsperadas.get(1).getId();
        PromocionGeneralArticulos promoEncontrada = promocionTipo4Repository.findById(ID_ELIMINAR).get();
        promocionTipo4Repository.delete(promoEncontrada);
        Optional<PromocionGeneralArticulos> promoEliminada = promocionTipo4Repository.findById(ID_ELIMINAR);
        assertFalse(promoEliminada.isPresent());
    }       
    
    @Test
    public void testDeleteById() {
        System.out.println("Ejecutando prueba eliminar promocion tipo 4 por Id...");
        
        PromocionGeneralArticulos promocion = utilidades.crearPromocionTipo4();
        promocionTipo4Repository.save(promocion);
        
        final int ID_ELIMINAR = promocion.getId();
        promocionTipo4Repository.deleteById(ID_ELIMINAR);        
        
        Optional<PromocionGeneralArticulos> promoEliminada = promocionTipo4Repository.findById(ID_ELIMINAR);
        assertFalse(promoEliminada.isPresent());
    }        
    
    @Test
    public void testUpdate() {
        System.out.println("Ejecutando prueba actualizar una promocion tipo 4...");

        PromocionGeneralArticulos promocion = utilidades.crearPromocionTipo4();
        promocionTipo4Repository.save(promocion);
        
        promocion.setDescuento(0.40);
        promocionTipo4Repository.update(promocion);
        
        PromocionGeneralArticulos promocionActualizada = promocionTipo4Repository.findById(promocion.getId()).get();

        assertEquals(promocion.getDescuento(), promocionActualizada.getDescuento());
    }         
    
}
