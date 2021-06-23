package com.cherrysoft.interfaces;

import com.cherrysoft.model.data.Articulo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Servicio de inventario, administra los articulos registrados del sistema
 *
 * @author Emmanuel Chable
 */
public interface InventarioService {

    /**
     *
     * @return Lista de articulos
     */
    public List<Articulo> obtenerTodosLosArticulos();

    /**
     * @param articulo articulo a eliminar
     * @return Articulo eliminado
     */
    public Articulo eliminarArticulo(Articulo articulo);

    /**
     * @param articulo
     * @return
     */
    public Articulo actualizarArticulo(Articulo articulo);

    /**
     *
     * @param articuloId
     * @param cantidad
     * @return
     * @throws Exception
     */
    public Articulo actualizarArticulo(Integer articuloId, Integer cantidad) throws Exception;

    /**
     *
     * @return
     */
    public List<Articulo> obtenerArticulosDisponibles();

    /**
     *
     * @param inferior cantidad minima para filtrar
     * @param superior cantidad maxima para filtrar
     * @return
     */
    public List<Articulo> obtenerArticulosPorRangoCantidad(Integer inferior, Integer superior);

    /**
     *
     * @param rutaArchivo
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NumberFormatException
     */
    public List<Articulo> registrarArticulosPorCsv(String rutaArchivo) throws FileNotFoundException, IOException, NumberFormatException;

    /**
     *
     * @param id
     * @param nombre
     * @param descripcion
     * @param precio
     * @param catidad
     * @return
     */
    public Articulo registrarArticulo(Integer id, String nombre, String descripcion, BigDecimal precio, Integer catidad);

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Articulo obtenerArticuloPorId(Integer id) throws Exception;
}
