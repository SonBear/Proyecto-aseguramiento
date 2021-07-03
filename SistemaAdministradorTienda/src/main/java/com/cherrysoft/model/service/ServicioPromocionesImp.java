/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.service;

import com.cherrysoft.interfaces.ServicioPromociones;
import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.data.Promocion;
import com.cherrysoft.model.data.PromocionArticuloCompra;
import com.cherrysoft.model.data.PromocionArticuloRegaloPorCompras;
import com.cherrysoft.model.data.PromocionDescuentoArticuloCantidad;
import com.cherrysoft.model.data.PromocionDescuentoArticuloPorCompras;
import com.cherrysoft.model.data.PromocionGeneralArticulos;
import com.cherrysoft.model.repository.PromocionArticuloRegaloPorComprasRepository;
import com.cherrysoft.model.repository.PromocionDescuentoArticuloCantidadRepository;
import com.cherrysoft.model.repository.PromocionDescuentoArticuloPorComprasRepository;
import com.cherrysoft.model.repository.PromocionGeneralArticulosRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase encargada de brindar servicios relacionados a promociones
 * 
 * @author Equipo1
 */
public class ServicioPromocionesImp implements ServicioPromociones {
    PromocionDescuentoArticuloCantidadRepository repositoryT1;
    PromocionArticuloRegaloPorComprasRepository repositoryT2;
    PromocionDescuentoArticuloPorComprasRepository repositoryT3;
    PromocionGeneralArticulosRepository repositoryT4;

    public ServicioPromocionesImp() {
        repositoryT1 = new PromocionDescuentoArticuloCantidadRepository();  
        repositoryT2 = new PromocionArticuloRegaloPorComprasRepository();
        repositoryT3 = new PromocionDescuentoArticuloPorComprasRepository();
        repositoryT4 = new PromocionGeneralArticulosRepository();
    }
    
    /**
     * Metodo que crea y almacena en la base de datos una promocion de tipo 1 "PromocionDescuentoArticuloCantidad"
     *
     * @param articuloAComprar El articulo que se debe comprar para tener acceso a la promocion
     * @param cantidad La cantidad a comprar del articulo para tener acceso a la promocion
     * @param descuento El porcentaje de descuento de la promocion
     * @param inicio Fecha de inicio de validez de la promocion
     * @param fin Fecha de fin de validez de la promocion
     * @return Una promocion de tipo PromocionDescuentoArticuloCantidad
     */
    @Override
    public PromocionDescuentoArticuloCantidad crearPromocionTipo1(Articulo articuloAComprar, int cantidad, double descuento, Date inicio, Date fin) {
        PromocionDescuentoArticuloCantidad promo;
        promo = new PromocionDescuentoArticuloCantidad(articuloAComprar, cantidad, descuento);
        promo.setFechaInicio(inicio);
        promo.setFechaFinal(fin);
        repositoryT1.save(promo);
        return promo;
    }

     /**
     * Metodo que crea y almacena en la base de datos una promocion de tipo 2 "PromocionArticuloRegaloPorCompras"
     *
     * @param articulosCompraPromocion Los articulos a comprar para tener acceso a la promocion
     * @param articuloDeRegalo El articulo que se regalara en la promocion
     * @param inicio Fecha de inicio de validez de la promocion
     * @param fin Fecha de fin de validez de la promocion
     * @return Una promocion de tipo PromocionArticuloRegaloPorCompras
     */
    @Override
    public PromocionArticuloRegaloPorCompras crearPromocionTipo2(List<PromocionArticuloCompra> articulosCompraPromocion, Articulo articuloDeRegalo, Date inicio, Date fin) {
        PromocionArticuloRegaloPorCompras promo = new PromocionArticuloRegaloPorCompras();
        articulosCompraPromocion = asociarArticulosConLaPromocion(promo, articulosCompraPromocion);        
        
        promo.setArticuloDeRegalo(articuloDeRegalo);
        promo.setArticulosCompraPromocion(articulosCompraPromocion);
        promo.setFechaInicio(inicio);
        promo.setFechaFinal(fin);       
        repositoryT2.save(promo);
        return promo;
    }

     /**
     * Metodo que crea y almacena en la base de datos una promocion de tipo 3 "PromocionDescuentoArticuloPorCompras"
     *
     * @param descuento El porcentaje de descuento de la promocion
     * @param articulosCompraPromocion Los articulos a comprar para tener acceso a la promocion
     * @param inicio Fecha de inicio de validez de la promocion
     * @param fin Fecha de fin de validez de la promocion
     * @return Una promocion de tipo PromocionDescuentoArticuloPorCompras
     */
    @Override
    public PromocionDescuentoArticuloPorCompras crearPromocionTipo3(Double descuento, List<PromocionArticuloCompra> articulosCompraPromocion, Date inicio, Date fin) {
        PromocionDescuentoArticuloPorCompras promo = new PromocionDescuentoArticuloPorCompras();
        articulosCompraPromocion = asociarArticulosConLaPromocion(promo, articulosCompraPromocion);
        
        promo.setDescuento(descuento);
        promo.setArticulosCompraPromocion(articulosCompraPromocion);
        promo.setFechaInicio(inicio);
        promo.setFechaFinal(fin);
        repositoryT3.save(promo);    
        return promo;
    }

     /**
     * Metodo que crea y almacena en la base de datos una promocion de tipo 4 "PromocionGeneralArticulos"
     *
     * @param descuento El porcentaje de descuento de la promocion
     * @param inicio Fecha de inicio de validez de la promocion
     * @param fin Fecha de fin de validez de la promocion
     * @return Una promocion de tipo PromocionGeneralArticulos
     */
    @Override
    public PromocionGeneralArticulos crearPromocionTipo4(double descuento, Date inicio, Date fin) {
        PromocionGeneralArticulos promo = new PromocionGeneralArticulos(descuento);
        promo.setFechaInicio(inicio);
        promo.setFechaFinal(fin);
        repositoryT4.save(promo);
        return promo;        
    }

     /**
     * Metodo que permite obtener todas las promociones de todos los tipos 
     * almacenadas en la base de datos
     *
     * @return Una lista con todas las promociones en la base de datos
     */
    @Override
    public List<Promocion> obtenerPromociones() {
        Iterable<PromocionDescuentoArticuloCantidad> promocionesT1 = repositoryT1.findAll();
        Iterable<PromocionArticuloRegaloPorCompras> promocionesT2 = repositoryT2.findAll();
        Iterable<PromocionDescuentoArticuloPorCompras> promocionesT3 = repositoryT3.findAll();
        Iterable<PromocionGeneralArticulos> promocionesT4 = repositoryT4.findAll();
        List<Promocion> promociones = new ArrayList();
        
        promocionesT1.forEach(promoT1 -> {
            promociones.add(promoT1);
        });
        promocionesT2.forEach(promoT2 -> {
            promociones.add(promoT2);
        });
        promocionesT3.forEach(promoT3 -> {
            promociones.add(promoT3);
        });
        promocionesT4.forEach(promoT4 -> {
            promociones.add(promoT4);
        });
        
        return promociones;
    }

     /**
     * Metodo que permite asignar una promocion a un cliente y actualizar
     * la promocion en la base de datos
     *
     * @param promocion La promocion a asignar a un cliente
     * @param cliente El cliente a asignarle la promocion
     * @return La promocion actualizada con el cliente asignado
     */    
    @Override
    public Promocion asignarPromocionCliente(Promocion promocion, Cliente cliente) {
        if(promocion instanceof PromocionDescuentoArticuloCantidad) {
            PromocionDescuentoArticuloCantidad promoAlmacenada;
            promoAlmacenada = repositoryT1.findById(promocion.getId()).get();
            promoAlmacenada.addCliente(cliente);
            repositoryT1.update(promoAlmacenada);
        }
        else if(promocion instanceof PromocionArticuloRegaloPorCompras) {
            repositoryT2.update((PromocionArticuloRegaloPorCompras)promocion);

        }
        else if(promocion instanceof PromocionDescuentoArticuloPorCompras) {
            repositoryT3.update((PromocionDescuentoArticuloPorCompras)promocion);
        }
        else {
            repositoryT4.update((PromocionGeneralArticulos)promocion);
        }
        return promocion;
    }

     /**
     * Metodo que permite borrar una promocion de la base de datos
     *
     * @param promocion La promocion a borrar
     */
    @Override
    public void eliminarPromocion(Promocion promocion) {
        if(promocion instanceof PromocionDescuentoArticuloCantidad) {
            repositoryT1.delete((PromocionDescuentoArticuloCantidad)promocion);
        }
        else if(promocion instanceof PromocionArticuloRegaloPorCompras) {
            repositoryT2.delete((PromocionArticuloRegaloPorCompras)promocion);

        }
        else if(promocion instanceof PromocionDescuentoArticuloPorCompras) {
            repositoryT3.delete((PromocionDescuentoArticuloPorCompras)promocion);
        }
        else {
            repositoryT4.delete((PromocionGeneralArticulos)promocion);
        }  
    }

     /**
     * Metodo que permite enviar una promocion por correo electronico
     * a un cliente
     *
     * @param promocion La promocion a enviar al cliente
     * @param cliente El cliente al que se le enviara la promocion
     */    
    @Override
    public void enviarPromocionPorCorreo(Promocion promocion, Cliente cliente) {
        System.out.println("Enviando por correo:");
        System.out.println("Promocion: " + promocion.toString());
        System.out.println("Cliente: " + cliente.getCorreo());
    }
    
     /**
     * Metodo que permite asociar una lista de articulos con una promocion
     * 
     * @param promo La promocion a la que se asociara los articulos
     * @param articulos La lista de articulos a asociar a la promocion
     * @return La lista de articulos actualizada asociada a la promocion
     */       
    private List<PromocionArticuloCompra> asociarArticulosConLaPromocion(Promocion promo, List<PromocionArticuloCompra> articulos) {
        articulos.forEach(articulo -> {
            articulo.setPromocion(promo);
        });        
        return articulos;
    }
    
}
