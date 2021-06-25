package com.cherrysoft.model.service;

import com.cherrysoft.interfaces.ClientesService;
import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.repository.ClienteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @since 23/06/2021
 * @author VictorEnrique
 * @version 1.0
 */
public class ClientesImp implements ClientesService{
    
    private final ClienteRepository clienteRepository = new ClienteRepository();

    /**
     * 
     * @param cliente El nombre del cliente
     * @param email El email del cliente
     * @throws Exception 
     */
    @Override
    public void registrarCliente(String cliente, String email) throws Exception {
        Cliente clnte = clienteRepository.obtenerClientePorNombre(cliente);
        if (!Objects.isNull(clnte)) {
            throw new Exception("El usuario ya se encuentra registrado");
        }
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setCorreo(email);
        nuevoCliente.setNombre(cliente);

        clienteRepository.save(nuevoCliente);
    }

    /**
     * 
     * @return clientes devuelve lista de clientes registrados
     */
    @Override
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clienteRepository.findAll().iterator().forEachRemaining(clientes::add);
        return clientes;
    }

    /**
     * 
     * @param cliente se usa el objeto cliente para eliminarlo
     * @return Cliente
     */
    @Override
    public Cliente eliminarCliente(Cliente cliente) {
        return clienteRepository.delete(cliente);
    }

    /**
     * 
     * @param cliente Devuelve el objeto cliente
     * @param nombreCliente Nombre actualizado del cliente
     * @param email correo actualizado del cliente
     * @return Cliente
     */
    @Override
    public Cliente actualizarDatosCliente(Cliente cliente, String nombreCliente, String email) {
        cliente.setCorreo(email);
        cliente.setNombre(nombreCliente);
        clienteRepository.save(cliente);
        return cliente;
    }

    /**
     * 
     * @param id para que busque al cliente por su id
     * @return Cliente 
     * @throws Exception 
     */
    @Override
    public Cliente obtenerClientePorId(Integer id) throws Exception {
        return clienteRepository.findById(id).orElseThrow(() -> new Exception("No existe el usuario con el id: " + id));
    }
    
}
