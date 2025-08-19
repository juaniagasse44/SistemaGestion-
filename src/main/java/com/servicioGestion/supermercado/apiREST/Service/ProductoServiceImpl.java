package com.servicioGestion.supermercado.apiREST.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicioGestion.supermercado.apiREST.Dto.CategoriaDTO;
import com.servicioGestion.supermercado.apiREST.Dto.ProductoDTO;
import com.servicioGestion.supermercado.apiREST.Enums.TipoMovimiento;
import com.servicioGestion.supermercado.apiREST.Models.Categoria;
import com.servicioGestion.supermercado.apiREST.Models.HistorialMovimiento;
import com.servicioGestion.supermercado.apiREST.Models.Producto;
import com.servicioGestion.supermercado.apiREST.Repositories.CategoriaRepository;
import com.servicioGestion.supermercado.apiREST.Repositories.HistoriaMovimientoRepository;
import com.servicioGestion.supermercado.apiREST.Repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private HistoriaMovimientoRepository historialMovimientoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> getAllProductosActivos() {
        return productoRepository.findByActivoTrue().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoDTO> getProductoById(Integer id) {
        return productoRepository.findById(id).map(this::convertToDto);
    }

    @Override
    @Transactional
    public ProductoDTO createProducto(ProductoDTO productoDTO) {
        Categoria categoria = categoriaRepository.findById(productoDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + productoDTO.getIdCategoria()));

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setImagenUrl(productoDTO.getImagenUrl());
        producto.setCategoria(categoria);
        producto.setActivo(true);

        Producto nuevoProducto = productoRepository.save(producto);

        HistorialMovimiento historial = new HistorialMovimiento();
        historial.setProducto(nuevoProducto);
        historial.setUsuario(null); // Aquí iría la lógica del usuario autenticado
        historial.setTipoMovimiento(TipoMovimiento.ENTRADA);
        historial.setCantidadAfectada(nuevoProducto.getStock());
        historialMovimientoRepository.save(historial);

        return convertToDto(nuevoProducto);
    }

    @Override
    @Transactional
    public ProductoDTO updateProducto(Integer id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        Categoria categoria = categoriaRepository.findById(productoDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + productoDTO.getIdCategoria()));
        
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setImagenUrl(productoDTO.getImagenUrl());
        producto.setCategoria(categoria);

        Producto productoActualizado = productoRepository.save(producto);
        
        return convertToDto(productoActualizado);
    }

    @Override
    @Transactional
    public void softDeleteProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        
        producto.setActivo(false);
        
        productoRepository.save(producto);
    }
    
    //  Método de Ayuda para convertir  a dto y que no sea repetitivo
    private ProductoDTO convertToDto(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setImagenUrl(producto.getImagenUrl());
        dto.setActivo(producto.getActivo());

        if (producto.getCategoria() != null) {
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setId(producto.getCategoria().getId());
            categoriaDTO.setNombre(producto.getCategoria().getNombre());
            dto.setCategoria(categoriaDTO);
        }
        return dto;
    }
}

