/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.seguridad;

import com.cherrysoft.model.data.Usuario;

/**
 *
 * @author Emmanuel Chable
 */
public interface VerificadorService {

    public boolean esUsuarioAdmin(Usuario usuario);

    public void guardarAccionUsuario(Usuario usuario, String accion);
}
