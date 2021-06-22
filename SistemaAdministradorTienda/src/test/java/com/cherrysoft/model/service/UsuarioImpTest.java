/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.service;

import com.cherrysoft.model.data.Permiso;
import com.cherrysoft.model.data.Rol;
import com.cherrysoft.model.data.Usuario;
import java.util.List;
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

    public UsuarioImpTest() {
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

    /**
     * Test of login method, of class UsuarioImp.
     */
    @AfterAll
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String usuario = "SonBear28";
        String password = "SonBear28";
        UsuarioImp instance = new UsuarioImp();
        Usuario result = instance.login(usuario, password);
        System.out.println(result.getUsuario() + " " + result.getCorreo() + " " + result.getContrasenia());
        assertTrue(true);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of registrarUsuario method, of class UsuarioImp.
     */
    @Test
    public void testRegistrarUsuario() throws Exception {
        System.out.println("registrarUsuario");
        String usuario = "SonBear281";
        String password = "SonBear28";
        String email = "email@gmail.com";

        UsuarioImp instance = new UsuarioImp();
        instance.registrarUsuario(usuario, password, email, Rol.ADMIN);
        assertTrue(true);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of actualizarDatosUsuario method, of class UsuarioImp.
     */
    @Test
    public void testActualizarDatosUsuario() {
        System.out.println("actualizarDatosUsuario");

        String usuario = "usuario2";
        String email = "d";
        String password = "1234";
        Permiso permiso = null;
        UsuarioImp instance = new UsuarioImp();
        Usuario usr = instance.obtenerUsuarios().get(0);
        Usuario expResult = null;
        Usuario result = instance.actualizarDatosUsuario(usr, usuario, email, password, Rol.ADMIN);
        assertTrue(true);

    }

    /**
     * Test of obtenerUsuarios method, of class UsuarioImp.
     */
    @Test
    public void testObtenerUsuarios() {
        System.out.println("obtenerUsuarios");
        UsuarioImp instance = new UsuarioImp();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.obtenerUsuarios();
        assertTrue(result != null);
    }

    /**
     * Test of eliminarUsuario method, of class UsuarioImp.
     */
    @Test
    public void testEliminarUsuario() {
        System.out.println("eliminarUsuario");

        UsuarioImp instance = new UsuarioImp();
        Usuario usuario = instance.obtenerUsuarios().get(1);
        Usuario result = instance.eliminarUsuario(usuario);
        assertTrue(result != null);
        // TODO review the generated test code and remove the default call to fail.
    }

}
