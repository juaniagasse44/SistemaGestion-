package com.servicioGestion.supermercado.apiREST.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servicioGestion.supermercado.apiREST.Models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

     // Spring Data JPA entiende el nombre de este método y crea la consulta automáticamente:
    // "SELECT * FROM productos WHERE activo = true"
    List<Producto> findByActivoTrue();
}
