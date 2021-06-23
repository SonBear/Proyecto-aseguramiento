package com.cherrysoft.seguridad;

import com.cherrysoft.model.data.Historial;
import com.cherrysoft.model.data.Rol;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Emmanuel Chable
 */
public class VerificadorImp implements VerificadorService {

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    @Override
    public void guardarAccionUsuario(Usuario usuario, String accion) {
        List<Historial> bitacora = usuario.getBitacora();
        if (Objects.isNull(bitacora)) {
            bitacora = new ArrayList<>();
        }
        Historial historial = new Historial();
        historial.setAccion(accion);
        historial.setFuePermitido(true);
        historial.setUsuario(usuario);
        bitacora.add(historial);
        usuario.setBitacora(bitacora);
        usuarioRepository.save(usuario);
    }

    @Override
    public boolean esUsuarioAdmin(Usuario usuario) {
        return usuario.getPermiso().getRol() == Rol.ADMIN;
    }

}
