/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.interfaces;

import com.cherrysoft.model.data.Cliente;
import java.util.List;



/**
 *
 * @author VictorEnrique
 */
public interface ClientesService {
    public void registrarCliente(String cliente, String email) throws Exception;
    
    public List<Cliente> obtenerClientes();
    
    public Cliente eliminarCliente(Cliente cliente);
    
    public Cliente actualizarDatosCliente(Cliente cliente, String nombreCliente, String email);
    
    public Cliente obtenerClientePorId(Integer id) throws Exception;
}
