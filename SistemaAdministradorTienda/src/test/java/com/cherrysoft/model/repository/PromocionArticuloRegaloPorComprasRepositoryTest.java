/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.repository;

import com.cherrysoft.controlador.ControladorPromociones;
import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.PromocionArticuloCompra;
import com.cherrysoft.model.data.PromocionArticuloRegaloPorCompras;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final Faker faker = new Faker();
    private final int NUM_ARTICULOS = 5;    
    private ArticuloRepository articulosRepository;
    
    
    public PromocionArticuloRegaloPorComprasRepositoryTest() {
        promocionTipo2Repository = new PromocionArticuloRegaloPorComprasRepository();        
        articulosRepository = new ArticuloRepository();    
        iniciarArticulos();          
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
        if (promocionTipo2Repository != null) {
            promocionTipo2Repository.getEntityManager().close();
        }
        System.out.println("Promocion tipo2 repository cerrado");        
    }

    @Test
    public void testFindAll() {
        System.out.println("Ejecutando prueba listar promociones tipo 2");

        List<PromocionArticuloRegaloPorCompras> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(crearPromocion());
        promocionesEsperadas.add(crearPromocion());
        promocionesEsperadas.add(crearPromocion());

        promocionesEsperadas.forEach(promo -> promocionTipo2Repository.save(promo));

        Iterable<PromocionArticuloRegaloPorCompras> promocionesActuales = promocionTipo2Repository.findAll();
        assertIterableEquals(promocionesEsperadas, promocionesActuales);
    }
    
    /*
        Codigo de apoyo
    */
    private PromocionArticuloRegaloPorCompras crearPromocion() {        
        PromocionArticuloRegaloPorCompras promo = new PromocionArticuloRegaloPorCompras();
        promo.setArticuloDeRegalo(getArticuloAleatorio());
        promo.setArticulosCompraPromocion(crearArticulosPromocion());
        promo.setFechaInicio(crearFecha("2021-06-15"));
        promo.setFechaFinal(crearFecha("2021-07-20"));
        return promo;
    }    
    
    private Articulo crearArticulo() {
        Articulo articulo = new Articulo();
        articulo.setNombre(faker.food().fruit());
        articulo.setDescripcion("Descripcion: " + faker.number().digits(3));
        articulo.setPrecio(BigDecimal.valueOf(faker.number().numberBetween(5, 100)));
        articulo.setCantidad(faker.number().numberBetween(1, 15));
        return articulo;
    }
    
    private void iniciarArticulos() {
        System.out.println("----------INICIANDO AGREGAR ARTICULOS-------------");
        for(int i = 0; i < NUM_ARTICULOS; i++) {
            Articulo articulo = crearArticulo();
            articulosRepository.save(articulo);
        }
        System.out.println("Articulos agredaos:-----------------");
        Iterable<Articulo> articulos = articulosRepository.findAll();
        articulos.forEach(art -> System.out.println(art.getNombre()));
    }
    
    private Articulo getArticuloAleatorio() {
        return articulosRepository.findById(faker.number().numberBetween(1, NUM_ARTICULOS)).get();
    }
    
    private List<PromocionArticuloCompra> crearArticulosPromocion() {
        List<PromocionArticuloCompra> promoArticulos = new ArrayList();
        PromocionArticuloCompra promoArticulo1 = new PromocionArticuloCompra(); 
        promoArticulo1.setArticulo(getArticuloAleatorio());
        PromocionArticuloCompra promoArticulo2 = new PromocionArticuloCompra(); 
        promoArticulo2.setArticulo(getArticuloAleatorio());  
        promoArticulos.add(promoArticulo1);
        promoArticulos.add(promoArticulo2);
        return promoArticulos;
    }
    
    private Date crearFecha(String date) {
        Date parsedDate = null;
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            parsedDate = ft.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorPromociones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parsedDate;            
    }
    
}
