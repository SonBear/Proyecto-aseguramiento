package com.cherrysoft.model.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Emmanuel Chable
 */
@Entity
@Table(name = "promocion_tipo_4")
public class PromocionGeneralArticulos extends Promocion implements Serializable {

    @Column
    private Double descuento;

    public PromocionGeneralArticulos() {
    }

    public PromocionGeneralArticulos(Double descuento) {
        this.descuento = descuento;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

}
