/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.data;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Emmanuel Chable
 */
@Entity
@Table(name = "promocion_tipo_3")
public class PromocionDescuentoArticuloPorCompras extends Promocion {

    @Column
    private Double descuento;

    @OneToMany(mappedBy = "promocion", cascade = CascadeType.ALL)
    private List<PromocionArticuloCompra> articulosCompraPromocion;

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public List<PromocionArticuloCompra> getArticulosCompraPromocion() {
        return articulosCompraPromocion;
    }

    public void setArticulosCompraPromocion(List<PromocionArticuloCompra> articulosCompraPromocion) {
        this.articulosCompraPromocion = articulosCompraPromocion;
    }

}
