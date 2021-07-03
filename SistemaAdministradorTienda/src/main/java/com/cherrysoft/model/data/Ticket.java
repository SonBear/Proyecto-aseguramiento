package com.cherrysoft.model.data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jdk.jfr.Timestamp;

/**
 *
 * @author Emmanuel Chable
 */
@Entity
@Table(name = "tickets_generados")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private BigDecimal costoTotal;

    @Column
    @Timestamp
    private Date fechaVenta;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<VentaArticulo> ventasArticulos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public List<VentaArticulo> getVentasArticulos() {
        return ventasArticulos;
    }

    public void setVentasArticulos(List<VentaArticulo> ventasArticulos) {
        this.ventasArticulos = ventasArticulos;
    }

}
