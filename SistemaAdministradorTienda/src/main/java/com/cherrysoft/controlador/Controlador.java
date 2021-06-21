/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.controlador;

import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.seguridad.VerificadorImp;
import com.cherrysoft.seguridad.VerificadorService;

/**
 *
 * @author Emmanuel Chable
 */
public abstract class Controlador {

    private Usuario usuario;
    private Controlador controladorAnterior;
    private final VerificadorService verificador = new VerificadorImp();

    public abstract void abrirVentana();

    public abstract void cerrarVentana();

    public abstract void configurarControlador();

    public abstract void regresarControladorAnterior();
}
