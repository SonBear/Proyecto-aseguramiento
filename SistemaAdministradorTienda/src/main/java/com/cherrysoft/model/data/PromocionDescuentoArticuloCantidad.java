/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Emmanuel Chable
 */
@Entity
@Table(name = "promocion_tipo_1")
public class PromocionDescuentoArticuloCantidad extends Promocion {

    @Column
    private Integer cantidad;

    @Column
    private Double descuento;

    @OneToOne
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;

    public PromocionDescuentoArticuloCantidad(Articulo articulo, Integer cantidad, Double descuento) {
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

}
