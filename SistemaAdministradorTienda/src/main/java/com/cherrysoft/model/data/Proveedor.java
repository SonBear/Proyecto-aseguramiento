package com.cherrysoft.model.data;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "proveedores")
public class Proveedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String telefono;

    @Column
    private String email;

    @Column
    private String direccion;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<ArticuloProveedor> proveedorArticulos = new ArrayList();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<ArticuloProveedor> getProveedorArticulos() {
        return proveedorArticulos;
    }

    public void setProveedorArticulos(List<ArticuloProveedor> proveedorArticulos) {
        this.proveedorArticulos = proveedorArticulos;
    }

    public void agregarArticulo(ArticuloProveedor art) {
        this.proveedorArticulos.add(art);
    }

}
