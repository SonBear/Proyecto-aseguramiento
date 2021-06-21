/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.util;

import com.cherrysoft.controlador.ControladorPromociones;
import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.Promocion;
import com.cherrysoft.model.data.PromocionArticuloCompra;
import com.cherrysoft.model.data.PromocionArticuloRegaloPorCompras;
import com.cherrysoft.model.data.PromocionDescuentoArticuloCantidad;
import com.cherrysoft.model.repository.ArticuloRepository;
import com.cherrysoft.model.repository.CrudRepository;
import com.cherrysoft.model.repository.PromocionArticuloRegaloPorComprasRepository;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de utilidades que son empleadas en pruebas relacionadas a promociones
 * 
 * @author Equipo1
 */
public class PromocionesTestUtil {
    private final Faker faker = new Faker();
    private final int NUM_ARTICULOS = 5;    
    private ArticuloRepository articulosRepository;
    
    public PromocionesTestUtil() {
        articulosRepository = new ArticuloRepository();            
    }    
    
    public PromocionDescuentoArticuloCantidad crearPromocionTipo1() {
        PromocionDescuentoArticuloCantidad promo = new PromocionDescuentoArticuloCantidad();
        promo.setArticulo(getArticuloAleatorio());
        promo.setCantidad(1);
        promo.setDescuento(0.2);
        promo.setFechaInicio(crearFecha("2021-06-15"));
        promo.setFechaFinal(crearFecha("2021-07-20"));
        return promo;
    }
    
    public PromocionArticuloRegaloPorCompras crearPromocionTipo2() {        
        PromocionArticuloRegaloPorCompras promo = new PromocionArticuloRegaloPorCompras();
        promo.setArticuloDeRegalo(getArticuloAleatorio());
        promo.setArticulosCompraPromocion(crearArticulosPromocion(promo));
        promo.setFechaInicio(crearFecha("2021-06-15"));
        promo.setFechaFinal(crearFecha("2021-07-20"));
        return promo;
    }    
    
    public Articulo crearArticulo() {
        Articulo articulo = new Articulo();
        articulo.setNombre(faker.food().fruit());
        articulo.setDescripcion("Descripcion: " + faker.number().digits(3));
        articulo.setPrecio(BigDecimal.valueOf(faker.number().numberBetween(5, 100)));
        articulo.setCantidad(faker.number().numberBetween(1, 15));
        return articulo;
    }
    
    public void iniciarArticulos() {
        for(int i = 0; i < NUM_ARTICULOS; i++) {
            Articulo articulo = crearArticulo();
            articulosRepository.save(articulo);
        }
    }
    
    public Articulo getArticuloAleatorio() {
        return articulosRepository.findById(faker.number().numberBetween(1, NUM_ARTICULOS)).get();
    }
    
    public Articulo getArticuloAleatorio(Articulo articuloAEvitar) {
        int idAleatorio = 1;
        do {
            idAleatorio = faker.number().numberBetween(1, NUM_ARTICULOS);
        } while(articuloAEvitar.getId() == idAleatorio);
        return articulosRepository.findById(idAleatorio).get();
    }
    
    public List<PromocionArticuloCompra> crearArticulosPromocion(Promocion promo) {
        List<PromocionArticuloCompra> promoArticulos = new ArrayList();
        PromocionArticuloCompra promoArticulo1 = new PromocionArticuloCompra(); 
        promoArticulo1.setArticulo(getArticuloAleatorio());
        promoArticulo1.setPromocion(promo);
        
        PromocionArticuloCompra promoArticulo2 = new PromocionArticuloCompra(); 
        promoArticulo2.setArticulo(getArticuloAleatorio());  
        promoArticulo2.setPromocion(promo);
        
        promoArticulos.add(promoArticulo1);
        promoArticulos.add(promoArticulo2);
        return promoArticulos;
    }
    
    public Date crearFecha(String date) {
        Date parsedDate = null;
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            parsedDate = ft.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorPromociones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parsedDate;            
    }
    
    public void borrarTodasLasPromociones(CrudRepository repository) {
        Iterable<Promocion> promocionesActuales = repository.findAll();
        promocionesActuales.forEach(promo -> {
            repository.delete(promo);
        });
    }    
    
}
