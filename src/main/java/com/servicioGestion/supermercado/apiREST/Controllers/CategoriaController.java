package com.servicioGestion.supermercado.apiREST.Controllers;

import com.servicioGestion.supermercado.apiREST.Dto.CategoriaDTO;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicioGestion.supermercado.apiREST.Service.CategoriaService;

import java.util.List;

@RestController // 1. Indica que esta clase es un controlador REST.
@RequestMapping("/api/categorias") // 2. Define la URL base para todos los métodos de este controlador.
public class CategoriaController {
//esto es un comentario de prueba para mostarle a juani
    @Autowired // 3. Inyecta nuestro servicio de categorías.
    private CategoriaService categoriaService;

    @GetMapping // 4. Mapea este método a las peticiones HTTP GET en "/api/categorias".
    public ResponseEntity<?> getAllCategorias() {
        try {
            // 5. Llama al servicio para obtener los datos.
            List<CategoriaDTO> categorias = categoriaService.getAllCategorias();
            // 6. Devuelve una respuesta exitosa (HTTP 200 OK) con la lista de categorías en el cuerpo.
            return ResponseEntity.ok(categorias);
        } catch (Exception e) {
            // 7. Si algo sale mal en el servicio, captura el error.
            // Devuelve una respuesta de error (HTTP 500) con un mensaje.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener las categorías");
        }
    }

    // 1. GET /api/categorias/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoriaById(@PathVariable Integer id) {
        Optional<CategoriaDTO> categoriaDTO = categoriaService.getCategoriaById(id);
        // Si se encuentra, devuelve 200 OK. Si no, devuelve 404 Not Found.
        return categoriaDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 2. POST /api/categorias
    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO createdCategoria = categoriaService.createCategoria(categoriaDTO);
        // Devuelve 201 Created con la nueva categoría en el cuerpo.
        return new ResponseEntity<>(createdCategoria, HttpStatus.CREATED);
    }

    // 3. PUT /api/categorias/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO updatedCategoria = categoriaService.updateCategoria(id, categoriaDTO);
        if (updatedCategoria != null) {
            // Si se actualizó, devuelve 200 OK.
            return ResponseEntity.ok(updatedCategoria);
        } else {
            // Si no se encontró, devuelve 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }

    // 4. DELETE /api/categorias/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        try {
            categoriaService.deleteCategoria(id);
             // Si tiene éxito, devuelve 204 No Content.
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Si el ID no existe, el repo arroja una excepción. Lo capturamos como 404.
            return ResponseEntity.notFound().build();
        }
    }
}
