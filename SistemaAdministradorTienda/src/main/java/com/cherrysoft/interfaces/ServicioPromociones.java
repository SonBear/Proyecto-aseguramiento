package com.cherrysoft.interfaces;

import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.data.Promocion;
import com.cherrysoft.model.data.PromocionArticuloCompra;
import com.cherrysoft.model.data.PromocionArticuloRegaloPorCompras;
import com.cherrysoft.model.data.PromocionDescuentoArticuloCantidad;
import com.cherrysoft.model.data.PromocionDescuentoArticuloPorCompras;
import com.cherrysoft.model.data.PromocionGeneralArticulos;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Equipo1
 */
public interface ServicioPromociones {

    public PromocionDescuentoArticuloCantidad crearPromocionTipo1(Articulo articuloAComprar, int cantidad, double descuento, Date inicio, Date fin);

    public PromocionArticuloRegaloPorCompras crearPromocionTipo2(List<PromocionArticuloCompra> articulosCompraPromocion, Articulo articuloDeRegalo, Date inicio, Date fin);

    public PromocionDescuentoArticuloPorCompras crearPromocionTipo3(Double descuento, List<PromocionArticuloCompra> articulosCompraPromocion, Date inicio, Date fin);

    public PromocionGeneralArticulos crearPromocionTipo4(double descuento, Date inicio, Date fin);

    public Iterable<Promocion> obtenerPromociones();

    public Promocion asignarPromocionCliente(Promocion promocion, Cliente cliente);

    public void eliminarPromocion(Promocion promocion);

    public void enviarPromocionPorCorreo(Promocion promocion, Cliente cliente);
}
