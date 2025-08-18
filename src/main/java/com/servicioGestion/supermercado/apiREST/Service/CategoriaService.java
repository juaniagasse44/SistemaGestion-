package com.servicioGestion.supermercado.apiREST.Service;


import java.util.List;
import java.util.Optional;

import com.servicioGestion.supermercado.apiREST.Dto.CategoriaDTO;

public interface CategoriaService {
    List<CategoriaDTO> getAllCategorias();
    Optional<CategoriaDTO> getCategoriaById(Integer id);// es una forma moderna en Java de manejar casos donde un resultado puede no existir (por ejemplo, si se busca una categoría con un ID que no está en la base de datos).
    CategoriaDTO createCategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO updateCategoria(Integer id, CategoriaDTO categoriaDTO);
    void deleteCategoria(Integer id);
}
