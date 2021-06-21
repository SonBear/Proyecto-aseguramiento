package com.cherrysoft.model.data;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Emmanuel Chable
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String correo;

    @Column
    private String contrasenia;

    @Column(name = "usuario")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "rol_id")
    private Permiso permiso;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<CompraProveedor> comprasProveedor;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Historial> bitacora;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Ticket> ticketsGenerados;

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CompraProveedor> getComprasProveedor() {
        return comprasProveedor;
    }

    public void setComprasProveedor(List<CompraProveedor> comprasProveedor) {
        this.comprasProveedor = comprasProveedor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public List<Historial> getBitacora() {
        return bitacora;
    }

    public void setBitacora(List<Historial> bitacora) {
        this.bitacora = bitacora;
    }

    public List<Ticket> getTicketsGenerados() {
        return ticketsGenerados;
    }

    public void setTicketsGenerados(List<Ticket> ticketsGenerados) {
        this.ticketsGenerados = ticketsGenerados;
    }

}
