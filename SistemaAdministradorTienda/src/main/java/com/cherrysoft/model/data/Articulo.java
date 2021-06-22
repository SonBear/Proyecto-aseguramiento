package com.cherrysoft.model.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Emmanuel Chable
 */
@Entity
@Table(name = "inventario")
public class Articulo implements Serializable {

    //revisar esto, por si trae consecuencias,dejar solo la tabla producto o inventario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer cantidad;

    @Column
    private BigDecimal precio;

    @Column
    private String descripcion;

    @Column
    private String nombre;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<PromocionArticuloCompra> promocionesArticuloCompra;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<ArticuloProveedor> articulosProveedor;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<CompraCliente> comprasClientes;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<VentaArticulo> ventasArticulos;

    public Articulo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PromocionArticuloCompra> getPromocionesArticuloCompra() {
        return promocionesArticuloCompra;
    }

    public void setPromocionesArticuloCompra(List<PromocionArticuloCompra> promocionesArticuloCompra) {
        this.promocionesArticuloCompra = promocionesArticuloCompra;
    }

    public List<ArticuloProveedor> getArticulosProveedor() {
        return articulosProveedor;
    }

    public void setArticulosProveedor(List<ArticuloProveedor> articulosProveedor) {
        this.articulosProveedor = articulosProveedor;
    }

    public List<CompraCliente> getComprasClientes() {
        return comprasClientes;
    }

    public void setComprasClientes(List<CompraCliente> comprasClientes) {
        this.comprasClientes = comprasClientes;
    }

    public List<VentaArticulo> getVentasArticulos() {
        return ventasArticulos;
    }

    public void setVentasArticulos(List<VentaArticulo> ventasArticulos) {
        this.ventasArticulos = ventasArticulos;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

}
