/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.service;

import com.cherrysoft.model.data.Rol;
import com.cherrysoft.seguridad.ConfiguracionSistema;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.service.UsuarioImp;
import com.github.javafaker.Faker;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Emmanuel Chable
 */
public class UsuarioImpTest {

    private Faker faker = new Faker();

    public UsuarioImpTest() {
        ConfiguracionSistema.configurarSistemaInicial();
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testLogin() throws Exception {

        System.out.println("login");
        String usuario = "admin";
        String password = "admin";
        UsuarioImp instance = new UsuarioImp();
        Usuario result = instance.login(usuario, password);
        assertTrue(!Objects.isNull(result));
    }

    @Test
    public void testRegistrarUsuario() throws Exception {
        System.out.println("registrarUsuario");
        String usuario = faker.name().lastName();
        String password = "123123";
        String email = "email@gmail.com";

        UsuarioImp instance = new UsuarioImp();
        instance.registrarUsuario(usuario, password, email, Rol.ADMIN);
        assertTrue(true);
    }

    @Test
    public void testActualizarDatosUsuario() throws Exception {
        System.out.println("actualizarDatosUsuario");
        String usuario = "admin";
        String email = "amdinl";
        String password = "1234";
        UsuarioImp instance = new UsuarioImp();
        Usuario user = instance.login("admin", "admin");
        Usuario result = instance.actualizarDatosUsuario(user, usuario, email, password, Rol.ADMIN);
        assertTrue(result.getCorreo().equals(email));

    }

    @Test
    public void testObtenerUsuarios() {
        System.out.println("obtenerUsuarios");
        UsuarioImp instance = new UsuarioImp();
        List<Usuario> result = instance.obtenerUsuarios();
        assertTrue(result.size() > 0);
    }

    @Test
    public void testEliminarUsuario() throws Exception {
        System.out.println("eliminarUsuario");
        UsuarioImp instance = new UsuarioImp();
        Usuario usuario = instance.login("admin", "admin");
        Usuario result = instance.eliminarUsuario(usuario);
        assertTrue(result != null);
    }

}
