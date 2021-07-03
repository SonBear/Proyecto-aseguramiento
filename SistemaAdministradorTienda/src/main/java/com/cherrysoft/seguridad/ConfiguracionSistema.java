package com.cherrysoft.seguridad;

import com.cherrysoft.interfaces.UsuarioService;
import com.cherrysoft.model.data.Permiso;
import com.cherrysoft.model.data.Rol;
import com.cherrysoft.model.repository.PermisoRepository;
import com.cherrysoft.model.service.UsuarioImp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emmanuel Chable
 */
public class ConfiguracionSistema {

    private static final UsuarioService usuarioService = new UsuarioImp();
    private static final PermisoRepository permisoRepository = new PermisoRepository();

    public static void configurarSistemaInicial() {
        Permiso admin = new Permiso();
        admin.setDescripcion("Administrador capaz de realizar todas las funcionalidades");
        admin.setRol(Rol.ADMIN);

        Permiso normal = new Permiso();
        normal.setDescripcion("Usuario con capacidades limitadas");
        normal.setRol(Rol.NORMAL);

        if (!estanLosPermisosAgregados()) {
            permisoRepository.save(admin);
            permisoRepository.save(normal);
        }

        try {
            usuarioService.registrarUsuario("admin", "admin", "admin", Rol.ADMIN);
            System.out.println("Usuario: admin" + " Contraseña: admin");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static boolean estanLosPermisosAgregados() {
        List<Permiso> permisos = obtenerPermisos();
        return permisos.size() > 2;
    }

    private static List<Permiso> obtenerPermisos() {
        List<Permiso> permisos = new ArrayList<>();
        permisoRepository.findAll().iterator().forEachRemaining(permisos::add);
        return permisos;
    }
}
