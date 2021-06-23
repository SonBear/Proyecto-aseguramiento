package com.cherrysoft.controlador;

import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.seguridad.VerificadorImp;
import com.cherrysoft.seguridad.VerificadorService;

/**
 * Resguarda funcionalidades comunes de los controladores
 *
 * @author Emmanuel Chable
 */
public abstract class Controlador {

    protected Usuario usuario;
    protected Controlador controladorAnterior;
    protected final VerificadorService verificador;

    public Controlador() {
        verificador = new VerificadorImp();
    }

    public Controlador(Usuario usuario, Controlador controladorAnterior) {
        this.usuario = usuario;
        this.controladorAnterior = controladorAnterior;
        verificador = new VerificadorImp();
    }

    public abstract void abrirVentana();

    public abstract void cerrarVentana();

    public abstract void configurarControlador();

    public void regresarControladorAnterior() {
        this.cerrarVentana();
        controladorAnterior.abrirVentana();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Controlador getControladorAnterior() {
        return controladorAnterior;
    }

    public void setControladorAnterior(Controlador controladorAnterior) {
        this.controladorAnterior = controladorAnterior;
    }

}
