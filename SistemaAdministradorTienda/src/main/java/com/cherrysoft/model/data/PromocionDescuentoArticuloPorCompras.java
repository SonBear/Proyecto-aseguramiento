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

    public PromocionDescuentoArticuloPorCompras(Double descuento, List<PromocionArticuloCompra> articulosCompraPromocion) {
        this.descuento = descuento;
        this.articulosCompraPromocion = articulosCompraPromocion;
    }

    public PromocionDescuentoArticuloPorCompras() {
    }

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
