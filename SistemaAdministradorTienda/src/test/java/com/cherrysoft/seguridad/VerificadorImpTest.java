/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.seguridad;

import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.repository.UsuarioRepository;
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
public class VerificadorImpTest {

    public VerificadorImpTest() {
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
     * Test of guardarAccionUsuario method, of class VerificadorImp.
     */
    @Test
    public void testGuardarAccionUsuario() {
        System.out.println("guardarAccionUsuario");
        Usuario usuario = new UsuarioRepository().findById(1).get();
        String accion = "registrar usuario";
        boolean estaPermitido = true;
        VerificadorImp instance = new VerificadorImp();
        instance.guardarAccionUsuario(usuario, accion);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of bloquearASoloAdmin method, of class VerificadorImp.
     */
    @Test
    public void testBloquearASoloAdmin() throws Exception {
        System.out.println("bloquearASoloAdmin");
        Usuario usuario = new UsuarioRepository().findById(3).get();
        VerificadorImp instance = new VerificadorImp();
        assertTrue(instance.esUsuarioAdmin(usuario));
        // TODO review the generated test code and remove the default call to fail.
    }

}
