package com.cherrysoft.interfaces;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.ArticuloProveedor;
import com.cherrysoft.model.data.Proveedor;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Eusebio Ajas
 */
public interface ServicioProveedor {

    Iterable<Proveedor> obtenerProveedores();
    
    ArticuloProveedor obtenerProveedorMenorPrecioArticulo(Integer articuloId); 
    
    List<Articulo> obtenerListaArticulosCompraProveedor(Integer proveedorId, Integer usuarioId);

    BigDecimal obtenerMontoPagoProveedor(Integer proveedorId, Integer usuarioId); 
}
