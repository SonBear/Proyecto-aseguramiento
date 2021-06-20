/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.interfaces;

import com.cherrysoft.model.data.Articulo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Emmanuel Chable
 */
public interface InventarioService {

    public List<Articulo> obtenerTodosLosArticulos();

    public Articulo eliminarArticulo(Articulo articulo);

    public Articulo actualizarArticulo(Articulo articulo);

    public Articulo actualizarArticulo(Integer articuloId, Integer cantidad) throws Exception;

    public List<Articulo> obtenerArticulosDisponibles();

    public List<Articulo> obtenerArticulosPorRangoCantidad(Integer inferior, Integer superior);

    public List<Articulo> registrarArticulosPorCsv(String rutaArchivo) throws FileNotFoundException, IOException, NumberFormatException;
}
