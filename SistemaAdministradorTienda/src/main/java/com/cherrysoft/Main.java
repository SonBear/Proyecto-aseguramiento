package com.cherrysoft;

import com.cherrysoft.controlador.InventarioControlador;
import com.cherrysoft.controlador.LoginControlador;
import com.cherrysoft.controlador.UsuariosController;
import com.cherrysoft.model.repository.UsuarioRepository;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.seguridad.ConfiguracionSistema;
import com.cherrysoft.controlador.ControladorPromociones;
import com.cherrysoft.controlador.MenuControlador;
import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.data.ArticuloProveedor;
import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.data.Promocion;
import com.cherrysoft.model.data.PromocionArticuloCompra;
import com.cherrysoft.model.data.Proveedor;
import com.cherrysoft.model.repository.ArticuloRepository;
import com.cherrysoft.model.repository.ClienteRepository;
import com.cherrysoft.model.repository.ProveedorRepository;
import com.cherrysoft.model.service.ServicioPromocionesImp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ConfiguracionSistema.configurarSistemaInicial();
        LoginControlador login = new LoginControlador();
        login.abrirVentana();

    }
}
