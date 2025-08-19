package com.servicioGestion.supermercado.apiREST.Controllers;

import com.servicioGestion.supermercado.apiREST.Dto.ProductoDTO;
import com.servicioGestion.supermercado.apiREST.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos") // URL base para los productos
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	// Endpoint para GET /api/productos (Obtener todos los productos activos)
	@GetMapping
	public ResponseEntity<List<ProductoDTO>> getAllProductosActivos() {
		List<ProductoDTO> productos = productoService.getAllProductosActivos();
		return ResponseEntity.ok(productos);
	}

	// Endpoint para GET /api/productos/{id} (Obtener un producto por su ID)
	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Integer id) {
		return productoService.getProductoById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Endpoint para POST /api/productos (Crear un nuevo producto)
	@PostMapping
	public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
		try {
			ProductoDTO nuevoProducto = productoService.createProducto(productoDTO);
			return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	// Endpoint para PUT /api/productos/{id} (Actualizar un producto existente)
	@PutMapping("/{id}")
	public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoDTO) {
		try {
			ProductoDTO productoActualizado = productoService.updateProducto(id, productoDTO);
			if (productoActualizado != null) {
				return ResponseEntity.ok(productoActualizado);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// Endpoint para DELETE /api/productos/{id} (Desactivar un producto - Borrado Lógico)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
		try {
			productoService.softDeleteProducto(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
