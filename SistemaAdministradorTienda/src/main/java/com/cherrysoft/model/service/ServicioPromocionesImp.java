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
import java.util.List;

/**
 *
 * @author Equipo1
 */
public class ServicioPromocionesImp implements ServicioPromociones {
    PromocionDescuentoArticuloCantidadRepository repositoryT1 = new PromocionDescuentoArticuloCantidadRepository();
    PromocionArticuloRegaloPorComprasRepository repositoryT2 = new PromocionArticuloRegaloPorComprasRepository();
    PromocionDescuentoArticuloPorComprasRepository repositoryT3 = new PromocionDescuentoArticuloPorComprasRepository();
    PromocionGeneralArticulosRepository repositoryT4 = new PromocionGeneralArticulosRepository();

    @Override
    public PromocionDescuentoArticuloCantidad crearPromocionTipo1(Articulo articuloAComprar, int cantidad, double descuento) {
        PromocionDescuentoArticuloCantidad promo = new PromocionDescuentoArticuloCantidad(articuloAComprar, cantidad, descuento);
        repositoryT1.save(promo);
        return promo;
    }

    @Override
    public PromocionArticuloRegaloPorCompras crearPromocionTipo2(List<PromocionArticuloCompra> articulosCompraPromocion, Articulo articuloDeRegalo) {
        PromocionArticuloRegaloPorCompras promo = new PromocionArticuloRegaloPorCompras(articulosCompraPromocion, articuloDeRegalo);
        repositoryT2.save(promo);
        return promo;
    }

    @Override
    public PromocionDescuentoArticuloPorCompras crearPromocionTipo3(Double descuento, List<PromocionArticuloCompra> articulosCompraPromocion) {
        PromocionDescuentoArticuloPorCompras promo = new PromocionDescuentoArticuloPorCompras(descuento, articulosCompraPromocion);
        repositoryT3.save(promo);    
        return promo;        
    }

    @Override
    public PromocionGeneralArticulos crearPromocionTipo4(double descuento) {
        PromocionGeneralArticulos promo = new PromocionGeneralArticulos(descuento);
        repositoryT4.save(promo);        
        return promo;        
    }

    @Override
    public List<Promocion> obtenerPromociones() {
        Iterable<PromocionDescuentoArticuloCantidad> promocionesT1 = repositoryT1.findAll();
        Iterable<PromocionArticuloRegaloPorCompras> promocionesT2 = repositoryT2.findAll();
        Iterable<PromocionDescuentoArticuloPorCompras> promocionesT3 = repositoryT3.findAll();
        Iterable<PromocionGeneralArticulos> promocionesT4 = repositoryT4.findAll();
        List<Promocion> promociones = new ArrayList<Promocion>();
        
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

    @Override
    public Promocion asignarPromocionCliente(Promocion promocion, Cliente cliente) {
        promocion.addCliente(cliente);
        if(promocion instanceof PromocionDescuentoArticuloCantidad) {
            repositoryT1.update((PromocionDescuentoArticuloCantidad)promocion);
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

    @Override
    public void enviarPromocionPorCorreo(Promocion promocion, Cliente cliente) {
        System.out.println("Enviando por correo:");
        System.out.println("Promocion: " + promocion.toString());
        System.out.println("Cliente: " + cliente.getCorreo());
    }
}
