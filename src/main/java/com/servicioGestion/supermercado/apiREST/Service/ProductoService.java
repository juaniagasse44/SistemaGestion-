package com.servicioGestion.supermercado.apiREST.Service;

import java.util.List;
import java.util.Optional;

import com.servicioGestion.supermercado.apiREST.Dto.ProductoDTO;

public interface ProductoService {
    List<ProductoDTO> getAllProductosActivos();
    Optional<ProductoDTO> getProductoById(Integer id);
    ProductoDTO createProducto(ProductoDTO productoDTO); 
    ProductoDTO updateProducto(Integer id, ProductoDTO productoDTO);
    void softDeleteProducto(Integer id);
}