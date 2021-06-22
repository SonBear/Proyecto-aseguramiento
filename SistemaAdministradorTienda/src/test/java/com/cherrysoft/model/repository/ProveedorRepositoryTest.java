package com.cherrysoft.model.repository;

import com.cherrysoft.model.data.Proveedor;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Eusebio Ajas
 */

public class ProveedorRepositoryTest {

    private static ProveedorRepository proveedorRepository;
    private final Faker faker = new Faker();

    @BeforeAll
    public static void setup() {
        proveedorRepository = new ProveedorRepository();
        System.out.println("Proveedor repository creado");
    }

    @AfterAll
    public static void tearDown() {
        if (proveedorRepository != null) {
            proveedorRepository.getEntityManager().close();
        }
        System.out.println("Proveedor repository cerrado");
    }

    @Test
    public void testFindAll() {
        System.out.println("Corriendo prueba listar proveedores...");

        List<Proveedor> proveedoresEsperados = new ArrayList<>();
        proveedoresEsperados.add(crearProveedor());
        proveedoresEsperados.add(crearProveedor());
        proveedoresEsperados.add(crearProveedor());
        proveedoresEsperados.add(crearProveedor());

        proveedoresEsperados.forEach(proveedor -> proveedorRepository.save(proveedor));

        Iterable<Proveedor> proveedoresActuales = (List<Proveedor>) proveedorRepository.findAll();
        assertIterableEquals(proveedoresEsperados, proveedoresActuales);
    }

    @Test
    public void testfindById() {
        System.out.println("Corriendo prueba buscar proveedor por Id...");

        int idProveedor = 6;
        Proveedor proveedorEsperado = crearProveedor();
        proveedorEsperado.setNombre("John Doe");
        proveedorRepository.save(proveedorEsperado);

        Optional<Proveedor> proveedorActual = proveedorRepository.findById(idProveedor);

        assertEquals(proveedorEsperado.getNombre(), proveedorActual.get().getNombre());
    }

    @Test
    public void testSave() {
        System.out.println("Corriendo prueba registrar un proveedor...");

        int idProveedor = 1;
        Proveedor proveedor = crearProveedor();
        proveedorRepository.save(proveedor);

        assertTrue(idProveedor > 0);
    }

    @Test
    public void testDelete() {
        System.out.println("Corriendo prueba eliminar del registro un proveedor...");

        Integer idProveedor = 6;
        Proveedor proveedorEncontrado = proveedorRepository.findById(idProveedor).get();
        proveedorRepository.delete(proveedorEncontrado);
        Optional<Proveedor> proveedorEliminado = proveedorRepository.findById(idProveedor);
        assertFalse(proveedorEliminado.isPresent());
    }

    @Test
    public void testDelteById() {
        System.out.println("Corriendo prueba eliminar proveedor por Id...");

        int idProveedor = 5;
        proveedorRepository.deleteById(idProveedor);
        Optional<Proveedor> proveedorEliminado = proveedorRepository.findById(idProveedor);
        assertFalse(proveedorEliminado.isPresent());
    }

    @Test
    public void testUpdate() {
        System.out.println("Corriendo prueba actualizar un proveedor...");

        int idProveedor = 1;
        String telefonoActualizado = "99-98-56-47-58"; 
        
        Proveedor proveedor = new Proveedor();
        proveedor.setId(idProveedor);
        proveedor.setTelefono(telefonoActualizado);
        proveedorRepository.update(proveedor);
        Proveedor proveedorActualizado = proveedorRepository.findById(idProveedor).get();

        assertEquals(telefonoActualizado, proveedorActualizado.getTelefono());
    }

    private Proveedor crearProveedor() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(faker.artist().name());
        proveedor.setDireccion(faker.address().fullAddress());
        proveedor.setTelefono(faker.phoneNumber().cellPhone());
        proveedor.setEmail(faker.internet().emailAddress());

        return proveedor;
    }

}
