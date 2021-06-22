/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.service;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.Promocion;
import com.cherrysoft.model.data.PromocionArticuloRegaloPorCompras;
import com.cherrysoft.model.data.PromocionDescuentoArticuloCantidad;
import com.cherrysoft.model.data.PromocionDescuentoArticuloPorCompras;
import com.cherrysoft.model.data.PromocionGeneralArticulos;
import com.cherrysoft.model.repository.ArticuloRepository;
import com.cherrysoft.model.repository.PromocionArticuloRegaloPorComprasRepository;
import com.cherrysoft.model.repository.PromocionDescuentoArticuloCantidadRepository;
import com.cherrysoft.model.repository.PromocionDescuentoArticuloPorComprasRepository;
import com.cherrysoft.model.repository.PromocionGeneralArticulosRepository;
import com.cherrysoft.util.PromocionesTestUtil;
import java.util.ArrayList;
import java.util.Date;
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
public class ServicioPromocionesImpTest {
    private ServicioPromocionesImp servicio;
    private static PromocionesTestUtil utilidades;
    private ArticuloRepository articulosRepository;   
    private PromocionDescuentoArticuloCantidadRepository promocionTipo1Repository;
    private PromocionArticuloRegaloPorComprasRepository promocionTipo2Repository;
    private PromocionDescuentoArticuloPorComprasRepository promocionTipo3Repository;
    private PromocionGeneralArticulosRepository promocionTipo4Repository;
    
    public ServicioPromocionesImpTest() {
        servicio = new ServicioPromocionesImp();
        articulosRepository  = new ArticuloRepository();
        utilidades = new PromocionesTestUtil();
        promocionTipo1Repository = new PromocionDescuentoArticuloCantidadRepository();        
        promocionTipo2Repository = new PromocionArticuloRegaloPorComprasRepository();     
        promocionTipo3Repository = new PromocionDescuentoArticuloPorComprasRepository();   
        promocionTipo4Repository = new PromocionGeneralArticulosRepository();        
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

    /**
     * Test of crearPromocionTipo1 method, of class ServicioPromocionesImp.
     */
    @Test
    public void testCrearPromocionTipo1() {
        System.out.println("Ejecutando prueba crear promocion tipo 1");
        Articulo articulo = utilidades.crearArticulo();
        articulosRepository.save(articulo);
        
        Date inicio = utilidades.crearFecha("2021-06-15");
        Date fin = utilidades.crearFecha("2021-07-15");
        PromocionDescuentoArticuloCantidad promo = servicio.crearPromocionTipo1(articulo, 2, 0.15, inicio, fin);
        
        Optional<PromocionDescuentoArticuloCantidad> promoAgregada = promocionTipo1Repository.findById(promo.getId());
        assertTrue(promoAgregada.isPresent());
    }

    
    @Test
    public void testCrearPromocionTipo2() {
        System.out.println("Ejecutando prueba crear promocion tipo 2"); 
        
        PromocionArticuloRegaloPorCompras promo = new PromocionArticuloRegaloPorCompras();
        promo.setArticuloDeRegalo(utilidades.getArticuloAleatorio());
        promo.setArticulosCompraPromocion(utilidades.crearArticulosPromocion(promo));
        promo.setFechaInicio(utilidades.crearFecha("2021-06-15"));
        promo.setFechaFinal(utilidades.crearFecha("2021-07-20"));        
        
        PromocionArticuloRegaloPorCompras promoLocal = servicio.crearPromocionTipo2(promo.getArticulosCompraPromocion(), promo.getArticuloDeRegalo(), promo.getFechaInicio(), promo.getFechaFinal());
        
        Optional<PromocionArticuloRegaloPorCompras> promoAgregada = promocionTipo2Repository.findById(promoLocal.getId());
        assertTrue(promoAgregada.isPresent());        
    }

    
    @Test
    public void testCrearPromocionTipo3() {
        System.out.println("Ejecutando prueba crear promocion tipo 3"); 

        PromocionDescuentoArticuloPorCompras promocion = utilidades.crearPromocionTipo3();
        PromocionDescuentoArticuloPorCompras promocionLocal = servicio.crearPromocionTipo3(promocion.getDescuento(), promocion.getArticulosCompraPromocion(), promocion.getFechaInicio(), promocion.getFechaFinal());
        
        Optional<PromocionDescuentoArticuloPorCompras> promoAgregada = promocionTipo3Repository.findById(promocionLocal.getId());
        assertTrue(promoAgregada.isPresent());             
    }

    
    @Test
    public void testCrearPromocionTipo4() {
        System.out.println("Ejecutando prueba crear promocion tipo 4"); 

        PromocionGeneralArticulos promocion = utilidades.crearPromocionTipo4();
        PromocionGeneralArticulos promocionLocal = servicio.crearPromocionTipo4(promocion.getDescuento(), promocion.getFechaInicio(), promocion.getFechaFinal());
        
        Optional<PromocionGeneralArticulos> promoAgregada = promocionTipo4Repository.findById(promocionLocal.getId());
        assertTrue(promoAgregada.isPresent());                 
    }

    
    @Test
    public void testObtenerPromociones() {
        System.out.println("Ejecutando prueba listar todas las promociones");
        utilidades.borrarTodasLasPromociones(promocionTipo4Repository);     
        utilidades.borrarTodasLasPromociones(promocionTipo3Repository);     
        utilidades.borrarTodasLasPromociones(promocionTipo2Repository);     
        utilidades.borrarTodasLasPromociones(promocionTipo1Repository);             

        List<PromocionGeneralArticulos> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(utilidades.crearPromocionTipo4());
        promocionesEsperadas.add(utilidades.crearPromocionTipo4());
        promocionesEsperadas.add(utilidades.crearPromocionTipo4());

        promocionesEsperadas.forEach(promo -> promocionTipo4Repository.save(promo));
        
        List<Promocion> promocionesActuales = servicio.obtenerPromociones();
        assertIterableEquals(promocionesEsperadas, promocionesActuales);        
    }

}
