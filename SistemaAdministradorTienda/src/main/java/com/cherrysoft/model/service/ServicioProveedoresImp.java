package com.cherrysoft.model.service;

import com.cherrysoft.interfaces.ServicioProveedor;
import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.ArticuloProveedor;
import com.cherrysoft.model.data.CompraProveedor;
import com.cherrysoft.model.data.Proveedor;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.repository.ProveedorRepository;
import com.cherrysoft.model.repository.UsuarioRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author Eusebio Ajas
 */
public class ServicioProveedoresImp implements ServicioProveedor {

    private static ProveedorRepository proveedorRepository;
    private static UsuarioRepository usuarioRepository;

    public ServicioProveedoresImp() {
        proveedorRepository = new ProveedorRepository();
        usuarioRepository = new UsuarioRepository();
    }

    @Override
    public Iterable<Proveedor> obtenerProveedores() {
        return proveedorRepository.findAll();
    }
    
    public void save(Proveedor proveedor){
        proveedorRepository.save(proveedor);
    }
    
    public void saveUser(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    
    @Override
    public ArticuloProveedor obtenerProveedorMenorPrecioArticulo(Integer articuloId) {
        
        List<Proveedor> proveedores = new ArrayList<>();
        proveedorRepository.findAll().iterator().forEachRemaining(proveedores::add);

        List<ArticuloProveedor> articulos = new ArrayList<>();
        
        proveedores.forEach(proveedor -> {
            proveedor.getProveedorArticulos().stream()
                    .filter(articuloProveedor -> (articuloId.equals(articuloProveedor.getArticulo().getId())))
                    .forEachOrdered(actualArticuloProveedor -> {articulos.add(actualArticuloProveedor); }
            );
        });
        
        int tamanio = articulos.size();
        if(tamanio > 0){
            ArticuloProveedor articuloProveedorMenor = articulos.get(0);
            for (var i = 1; i < tamanio; i++) {
                if (articulos.get(i).getPrecio().compareTo(articuloProveedorMenor.getPrecio()) < 0) {
                    articuloProveedorMenor = articulos.get(i);
                }
            }
            
            return articuloProveedorMenor;
            
        } else  {
            return null;
        }
    }
    
    public ProveedorRepository getProveedorRepository() {
        return proveedorRepository;
    }

    @Override
    public List<Articulo> obtenerListaArticulosCompraProveedor(Integer proveedorId, Integer usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        List<CompraProveedor> compras = usuario.get().getComprasProveedor();
        List<Articulo> articulos = new ArrayList<>();
        
        for(CompraProveedor ca : compras){
            if(ca.getArticuloProveedor().getProveedor().getId().equals(proveedorId)){
                articulos.add(ca.getArticuloProveedor().getArticulo());
            }
        }
        
        return articulos;
    }

    @Override
    public BigDecimal obtenerMontoPagoProveedor(Integer proveedorId, Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        List<CompraProveedor> compras = usuario.getComprasProveedor();
        
        BigDecimal montoTotal = BigDecimal.ZERO;
        for(CompraProveedor cp: compras){
            if(cp.getArticuloProveedor().getProveedor().getId().equals(proveedorId)){
                montoTotal = montoTotal.add(cp.getCostoTotal());
            }
        }
        
        return montoTotal;
    }
}
