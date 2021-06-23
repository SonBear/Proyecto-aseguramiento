/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.model.service;

import com.cherrysoft.interfaces.ClientesService;
import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.repository.ClienteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author VictorEnrique
 */
public class ClientesImp implements ClientesService{
    
    private final ClienteRepository clienteRepository = new ClienteRepository();

    @Override
    public void registrarCliente(String cliente, String email) throws Exception {
        Cliente clnte = clienteRepository.obtenerClientePor(cliente);
        if (!Objects.isNull(clnte)) {
            throw new Exception("El usuario ya se encuentra registrado");
        }
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setCorreo(email);
        nuevoCliente.setNombre(cliente);

        clienteRepository.save(nuevoCliente);
    }

    @Override
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clienteRepository.findAll().iterator().forEachRemaining(clientes::add);
        return clientes;
    }

    @Override
    public Cliente eliminarCliente(Cliente cliente) {
        return clienteRepository.delete(cliente);
    }

    @Override
    public Cliente actualizarDatosCliente(Cliente cliente, String nombreCliente, String email) {
        cliente.setCorreo(email);
        cliente.setNombre(nombreCliente);
        clienteRepository.save(cliente);
        return cliente;
    }

    @Override
    public Cliente obtenerClientePorId(Integer id) throws Exception {
        return clienteRepository.findById(id).orElseThrow(() -> new Exception("No existe el usuario con el id: " + id));
    }
    
}
