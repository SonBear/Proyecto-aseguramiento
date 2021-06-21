/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.seguridad;

import com.cherrysoft.model.data.Historial;
import com.cherrysoft.model.data.Rol;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Emmanuel Chable
 */
public class VerificadorImp implements VerificadorService {

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    @Override
    public void guardarAccionUsuario(Usuario usuario, String accion, boolean estaPermitido) {
        List<Historial> bitacora = usuario.getBitacora();
        if (Objects.isNull(bitacora)) {
            bitacora = new ArrayList<>();
        }
        Historial historial = new Historial();
        historial.setAccion(accion);
        historial.setFuePermitido(estaPermitido);
        historial.setUsuario(usuario);
        bitacora.add(historial);
        usuario.setBitacora(bitacora);
        usuarioRepository.save(usuario);
    }

    @Override
    public void bloquearASoloAdmin(Usuario usuario) throws Exception {
        if (usuario.getPermiso().getRol() != Rol.ADMIN) {
            throw new Exception("El usuario no tiene permitido esa accion");
        }
    }

}
