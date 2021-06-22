package com.cherrysoft.model.service;

import com.cherrysoft.interfaces.UsuarioService;
import com.cherrysoft.model.data.Permiso;
import com.cherrysoft.model.data.Rol;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.repository.PermisoRepository;
import com.cherrysoft.model.repository.UsuarioRepository;
import com.cherrysoft.model.util.Encriptador;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

/**
 *
 * @author Emmanuel Chable
 */
public class UsuarioImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private final PermisoRepository permisoRepository = new PermisoRepository();

    @Override
    public Usuario login(String usuario, String password) throws Exception {
        Usuario usr = usuarioRepository.obtenerUsuarioPor(usuario);
        if (Objects.isNull(usr)) {
            throw new Exception("No existe el usuario proporcionado");
        }
        String pass = Encriptador.encriptarContrasenia(password);
        if (usr.getContrasenia().equals(pass)) {
            return usr;
        }

        throw new Exception("Contraseña o usuario incorrecto");
    }

    @Override
    public void registrarUsuario(String usuario, String password, String email, Rol rol) throws Exception {
        Usuario usr = usuarioRepository.obtenerUsuarioPor(usuario);
        Permiso permiso = getPermisoConRol(rol);
        if (Objects.isNull(permiso)) {
            throw new Exception("El permiso con ese rol no existe");
        }
        if (!Objects.isNull(usr)) {
            throw new Exception("El usuario ya se encuentra registrado");
        }
        Usuario nuevoUsuario = new Usuario();
        String contrasenia = Encriptador.encriptarContrasenia(password);
        nuevoUsuario.setContrasenia(contrasenia);
        nuevoUsuario.setCorreo(email);
        nuevoUsuario.setUsuario(usuario);
        nuevoUsuario.setPermiso(permiso);

        usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public Usuario actualizarDatosUsuario(Usuario usr, String usuario, String email, String password, Rol rol) {
        usr.setCorreo(email);
        Permiso permiso = getPermisoConRol(rol);
        usr.setPermiso(permiso);
        usr.setUsuario(usuario);
        String pass = Encriptador.encriptarContrasenia(password);
        usr.setContrasenia(pass);
        usuarioRepository.save(usr);
        return usr;
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarioRepository.findAll().iterator().forEachRemaining(usuarios::add);
        return usuarios;
    }

    @Override
    public Usuario eliminarUsuario(Usuario usuario) {
        return usuarioRepository.delete(usuario);
    }

    private Permiso getPermisoConRol(Rol rol) {
        return StreamSupport
                .stream(permisoRepository.findAll().spliterator(), true)
                .filter((e) -> e.getRol().equals(rol))
                .findFirst().orElse(null);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) throws Exception {
        return usuarioRepository.findById(id).orElseThrow(() -> new Exception("No existe el usuario con el id: " + id));
    }

}
