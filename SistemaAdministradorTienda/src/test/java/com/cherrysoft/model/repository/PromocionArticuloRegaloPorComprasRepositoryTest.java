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
import java.util.Optional;
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
        promocionTipo2Repository = new PromocionArticuloRegaloPorComprasRepository(); 
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
        borrarTodasLasPromociones();     

        List<PromocionArticuloRegaloPorCompras> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(crearPromocion());
        promocionesEsperadas.add(crearPromocion());
        promocionesEsperadas.add(crearPromocion());

        promocionesEsperadas.forEach(promo -> promocionTipo2Repository.save(promo));

        Iterable<PromocionArticuloRegaloPorCompras> promocionesActuales = promocionTipo2Repository.findAll();
        
        System.out.println("Promociones actuales: ");
        promocionesActuales.forEach(promo -> {
            System.out.println(promo.getId());
        });
        
        System.out.println("Promociones esperadas: ");
        promocionesEsperadas.forEach(promo -> {
            System.out.println(promo.getId());
        });
        assertIterableEquals(promocionesEsperadas, promocionesActuales);
    }
    
    
    @Test
    public void testfindById() {
        System.out.println("Ejecutando prueba buscar promocion tipo 2 por ID");
        borrarTodasLasPromociones();             
        
        PromocionArticuloRegaloPorCompras promocionEsperada = crearPromocion();
        promocionTipo2Repository.save(promocionEsperada);

        Optional<PromocionArticuloRegaloPorCompras> promocionActual = promocionTipo2Repository.findById(promocionEsperada.getId());
        assertEquals(promocionEsperada.getId(), promocionActual.get().getId());
    }   
    
    @Test
    public void testSave() {
        System.out.println("Ejecutando prueba guardar una promocion tipo 2...");
        borrarTodasLasPromociones();             
        
        PromocionArticuloRegaloPorCompras promocionEsperada = crearPromocion();
        promocionTipo2Repository.save(promocionEsperada);
        Optional<PromocionArticuloRegaloPorCompras> promocionActual = promocionTipo2Repository.findById(promocionEsperada.getId());        

        assertTrue(promocionActual.get() != null);
    }    
    
    @Test
    public void testDelete() {
        System.out.println("Ejecutando prueba borrar una promocion tipo 2 de la bd");    
        borrarTodasLasPromociones();        

        List<PromocionArticuloRegaloPorCompras> promocionesEsperadas = new ArrayList<>();
        promocionesEsperadas.add(crearPromocion());
        promocionesEsperadas.add(crearPromocion());
        promocionesEsperadas.add(crearPromocion());

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
        
        PromocionArticuloRegaloPorCompras promocion = crearPromocion();
        promocionTipo2Repository.save(promocion);
        
        final int ID_ELIMINAR = promocion.getId();
        promocionTipo2Repository.deleteById(ID_ELIMINAR);        
        
        Optional<PromocionArticuloRegaloPorCompras> promoEliminada = promocionTipo2Repository.findById(ID_ELIMINAR);
        assertFalse(promoEliminada.isPresent());
    }        
    
    @Test
    public void testUpdate() {
        System.out.println("Ejecutando prueba actualizar una promocion tipo 2...");

        PromocionArticuloRegaloPorCompras promocion = crearPromocion();
        promocionTipo2Repository.save(promocion);
        
        PromocionArticuloCompra promoArticulo = new PromocionArticuloCompra();         
        Articulo articuloAAgregar = crearArticulo();
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
    
    /*
        Codigo de apoyo
    */
    private PromocionArticuloRegaloPorCompras crearPromocion() {        
        PromocionArticuloRegaloPorCompras promo = new PromocionArticuloRegaloPorCompras();
        promo.setArticuloDeRegalo(getArticuloAleatorio());
        System.out.println("Articulo : ");
        System.out.println(promo.getArticuloDeRegalo());
        promo.setArticulosCompraPromocion(crearArticulosPromocion(promo));
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
        for(int i = 0; i < NUM_ARTICULOS; i++) {
            Articulo articulo = crearArticulo();
            articulosRepository.save(articulo);
        }
    }
    
    private Articulo getArticuloAleatorio() {
        return articulosRepository.findById(faker.number().numberBetween(1, NUM_ARTICULOS)).get();
    }
    
    private Articulo getArticuloAleatorio(Articulo articuloAEvitar) {
        int idAleatorio = 1;
        do {
            idAleatorio = faker.number().numberBetween(1, NUM_ARTICULOS);
        } while(articuloAEvitar.getId() == idAleatorio);
        return articulosRepository.findById(idAleatorio).get();
    }
    
    private List<PromocionArticuloCompra> crearArticulosPromocion(PromocionArticuloRegaloPorCompras promo) {
        List<PromocionArticuloCompra> promoArticulos = new ArrayList();
        PromocionArticuloCompra promoArticulo1 = new PromocionArticuloCompra(); 
        promoArticulo1.setArticulo(getArticuloAleatorio(promo.getArticuloDeRegalo()));
        promoArticulo1.setPromocion(promo);
        
        PromocionArticuloCompra promoArticulo2 = new PromocionArticuloCompra(); 
        promoArticulo2.setArticulo(getArticuloAleatorio(promo.getArticuloDeRegalo()));  
        promoArticulo2.setPromocion(promo);
        
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
    
    private void borrarTodasLasPromociones() {
        Iterable<PromocionArticuloRegaloPorCompras> promocionesActuales = promocionTipo2Repository.findAll();
        promocionesActuales.forEach(promo -> {
            System.out.println("Borrando: " + promo.getId());
            promocionTipo2Repository.delete(promo);
            System.out.println("Se borro");
        });
    }
    
}
