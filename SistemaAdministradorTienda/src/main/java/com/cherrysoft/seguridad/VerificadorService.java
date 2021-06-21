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

    public void bloquearASoloAdmin(Usuario usaurio) throws Exception;

    public void guardarAccionUsuario(Usuario usuario, String accion, boolean estaPermitido);
}
