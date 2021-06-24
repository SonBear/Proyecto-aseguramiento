/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.service;

import com.cherrysoft.model.data.Cliente;
import com.github.javafaker.Faker;
import java.util.List;
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
public class ClientesImpTest {

    private Faker faker = new Faker();

    public ClientesImpTest() {

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
     * Test of registrarCliente method, of class ClientesImp.
     */
    @Test
    public void testRegistrarCliente() throws Exception {
        System.out.println("REGISTRARCLIENTE");
        String cliente = faker.name().lastName();
        String email = "correoprueba" + faker.name().lastName() + "@gmail.com";

        ClientesImp instance = new ClientesImp();
        instance.registrarCliente(cliente, email);
        assertTrue(true);
    }

    /**
     * Test of obtenerClientes method, of class ClientesImp.
     */
    @Test
    public void testObtenerClientes() {
        System.out.println("OBTENERCLIENTES");
        ClientesImp instance = new ClientesImp();
        List<Cliente> result = instance.obtenerClientes();
        assertTrue(result.size() > 0);
    }

    /**
     * Test of eliminarCliente method, of class ClientesImp.
     */
    @Test
    public void testEliminarCliente() throws Exception {
        System.out.println("ELIMINARCLIENTES");
        ClientesImp instance = new ClientesImp();
        List<Cliente> clientes = instance.obtenerClientes();
        Cliente clienteEliminar = clientes.get(clientes.size()-1);
        Cliente result = instance.eliminarCliente(clienteEliminar);
        assertTrue(result != null);
    }

    /**
     * Test of actualizarDatosCliente method, of class ClientesImp.
     */
    @Test
    public void testActualizarDatosCliente() throws Exception {
        System.out.println("ACTUALIZARDATOSCLIENTE");
        String nombreCliente = "NombreActualizado";
        String email = "CorreoAct@gmail.com";
        ClientesImp instance = new ClientesImp();
        Cliente cliente = instance.obtenerClientePorId(2);
        Cliente result = instance.actualizarDatosCliente(cliente, nombreCliente, email);
        assertTrue(result.getCorreo().equals(email));
    }
}
