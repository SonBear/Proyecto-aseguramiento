/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.util;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.repository.ArticuloRepository;
import com.cherrysoft.model.repository.ClienteRepository;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Equipo1
 */
public class ComboBoxUtil {

    private final ClienteRepository clientesRepository;
    private final ArticuloRepository articulosRepository;

    public ComboBoxUtil() {
        this.clientesRepository = new ClienteRepository();
        this.articulosRepository = new ArticuloRepository();
    }

    public JComboBox rellenarComboConClientes(JComboBox combo) {
        combo.removeAllItems();
        Iterable<Cliente> listaClientes = clientesRepository.findAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        listaClientes.forEach(cliente -> {
            model.addElement(cliente);
        });
        combo.setModel(model);
        return combo;
    }

    public JComboBox rellenarComboConArticulos(JComboBox combo) {
        combo.removeAllItems();
        Iterable<Articulo> listaArticulos = articulosRepository.findAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        listaArticulos.forEach(articulo -> {
            model.addElement(articulo);
        });
        combo.setModel(model);
        return combo;
    }

}
