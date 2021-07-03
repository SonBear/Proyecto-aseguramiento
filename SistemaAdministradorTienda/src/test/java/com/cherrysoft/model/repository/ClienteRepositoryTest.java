/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.repository;

import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.service.ClientesImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author VictorEnrique
 */
public class ClienteRepositoryTest {
    
    public ClienteRepositoryTest() {
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
     * Test of obtenerClientePorNombre method, of class ClienteRepository.
     */
    @Test
    public void testObtenerClientePorNombre() throws Exception {
        System.out.println("obtenerClientePor");
        String cliente = "Ullrich";
        ClienteRepository instance = new ClienteRepository();
        ClientesImp instance2 = new ClientesImp();
        Cliente expResult = instance2.obtenerClientePorId(3);
        Cliente result = instance.obtenerClientePorNombre(cliente);
        assertEquals(expResult, result);
    }
    
}
