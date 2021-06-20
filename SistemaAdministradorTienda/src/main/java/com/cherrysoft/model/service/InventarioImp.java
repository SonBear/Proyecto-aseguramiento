package com.cherrysoft.model.service;

import com.cherrysoft.interfaces.InventarioService;
import com.cherrysoft.model.data.Articulo;
import com.cherrysoft.model.repository.ArticuloRepository;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Emmanuel Chable
 */
public class InventarioImp implements InventarioService {

    private final ArticuloRepository articuloRepository = new ArticuloRepository();

    private enum CabecerasCsv {
        id, cantidad, precio, nombre, descripcion
    }

    @Override
    public List<Articulo> obtenerTodosLosArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        articuloRepository.findAll().iterator().forEachRemaining(articulos::add);
        return articulos;
    }

    @Override
    public Articulo eliminarArticulo(Articulo articulo) {
        return articuloRepository.delete(articulo);
    }

    @Override
    public Articulo actualizarArticulo(Articulo articulo) {
        return articuloRepository.update(articulo);
    }

    @Override
    public List<Articulo> obtenerArticulosDisponibles() {
        List<Articulo> articulos = new ArrayList<>();
        articuloRepository.findAll().iterator().forEachRemaining(articulos::add);
        List articulosDisponibles = articulos.stream().filter(
                (articulo) -> articulo.getCantidad() > 0).collect(Collectors.toList()
                );
        return articulosDisponibles;

    }

    @Override
    public List<Articulo> obtenerArticulosPorRangoCantidad(Integer inferior, Integer superior) {
        List<Articulo> articulos = new ArrayList<>();
        articuloRepository.findAll().iterator().forEachRemaining(articulos::add);
        List<Articulo> articulosFiltrados = articulos.stream()
                .filter(
                        (articulo)
                        -> articulo.getCantidad() >= inferior && articulo.getCantidad() <= superior
                )
                .collect(Collectors.toList());

        return articulosFiltrados;
    }

    /**
     * Header id, cantidad, precio, nombre, descripcion
     *
     * @param rutaArchivo
     * @return
     * @throws java.io.FileNotFoundException
     */
    @Override
    public List<Articulo> registrarArticulosPorCsv(String rutaArchivo) throws FileNotFoundException, IOException, NumberFormatException {
        Reader in = new FileReader(rutaArchivo);
        Iterable<CSVRecord> registros = CSVFormat.DEFAULT.withHeader(CabecerasCsv.class).withFirstRecordAsHeader().parse(in);
        List<Articulo> articulos = new ArrayList<>();
        registros.forEach((CSVRecord registro) -> {
            Integer id = parseInteger(registro.get(CabecerasCsv.id));
            Integer cantidad = parseInteger(registro.get(CabecerasCsv.cantidad));
            String descripcion = registro.get(CabecerasCsv.descripcion);
            String nombre = registro.get(CabecerasCsv.nombre);
            BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(registro.get(CabecerasCsv.precio)));

            Articulo articulo;

            try {
                articulo = obtenerArticulo(id);
                articulo.setCantidad(articulo.getCantidad() + cantidad);
                articulo.setDescripcion(descripcion);
                articulo.setNombre(nombre);
                articulo.setPrecio(precio);
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "agregando al inventario");
                articulo = new Articulo();
                articulo.setCantidad(cantidad);
                articulo.setDescripcion(descripcion);
                articulo.setNombre(nombre);
                articulo.setPrecio(precio);
            }
            articuloRepository.save(articulo);
            articulos.add(articulo);
        });
        return articulos;
    }

    private Integer parseInteger(String string) throws NumberFormatException {
        return Integer.parseInt(string);
    }

    private Articulo obtenerArticulo(Integer articuloId) throws Exception {
        return articuloRepository.findById(articuloId).orElseThrow(()
                -> new Exception("No se ha encontrado el articulo con el id:" + articuloId));
    }

    @Override
    public Articulo actualizarArticulo(Integer articuloId, Integer cantidad) throws Exception {
        Articulo articulo = obtenerArticulo(articuloId);
        articulo.setCantidad(cantidad);
        articuloRepository.update(articulo);
        return articulo;
    }

}
