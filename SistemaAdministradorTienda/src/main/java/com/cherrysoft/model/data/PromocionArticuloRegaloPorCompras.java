/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.data;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Emmanuel Chable
 */
@Entity
@Table(name = "promocion_tipo_2")
public class PromocionArticuloRegaloPorCompras extends Promocion {

    @OneToMany(mappedBy = "promocion", cascade = CascadeType.ALL)
    private List<PromocionArticuloCompra> articulosCompraPromocion;

    @OneToOne
    @JoinColumn(name = "articulo_id")
    private Articulo articuloDeRegalo;

    public PromocionArticuloRegaloPorCompras(List<PromocionArticuloCompra> articulosCompraPromocion, Articulo articuloDeRegalo) {
        this.articulosCompraPromocion = articulosCompraPromocion;
        this.articuloDeRegalo = articuloDeRegalo;
    }

    public PromocionArticuloRegaloPorCompras() {
    }

    public List<PromocionArticuloCompra> getArticulosCompraPromocion() {
        return articulosCompraPromocion;
    }

    public void setArticulosCompraPromocion(List<PromocionArticuloCompra> articulosCompraPromocion) {
        this.articulosCompraPromocion = articulosCompraPromocion;
    }

    public Articulo getArticuloDeRegalo() {
        return articuloDeRegalo;
    }

    public void setArticuloDeRegalo(Articulo articuloDeRegalo) {
        this.articuloDeRegalo = articuloDeRegalo;
    }

}
