package com.servicioGestion.supermercado.apiREST.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicioGestion.supermercado.apiREST.Dto.CategoriaDTO;
import com.servicioGestion.supermercado.apiREST.Models.Categoria;
import com.servicioGestion.supermercado.apiREST.Repositories.CategoriaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Marca esta clase como un Servicio de Spring
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired // Inyecta el repositorio que creamos antes
    private CategoriaRepository categoriaRepository;

    @Override // Implementa el método de la interfaz
    public List<CategoriaDTO> getAllCategorias() {
        // Llama al repositorio para obtener todas las entidades Categoria
        List<Categoria> categorias = categoriaRepository.findAll();

        // Convierte la lista de Entidades a una lista de DTOs
        return categorias.stream().map(categoria -> {
            CategoriaDTO dto = new CategoriaDTO();
            dto.setId(categoria.getId());
            dto.setNombre(categoria.getNombre());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoriaDTO> getCategoriaById(Integer id) {
        // Busca la entidad por ID
        return categoriaRepository.findById(id).map(categoria -> {
            // Si la encuentra, la mapea a DTO
            CategoriaDTO dto = new CategoriaDTO();
            dto.setId(categoria.getId());
            dto.setNombre(categoria.getNombre());
            return dto;
        });
    }

    @Override
    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {
        // Convierte el DTO recibido a una entidad
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());

        // Guarda la nueva entidad en la BD
        Categoria savedCategoria = categoriaRepository.save(categoria);

        // Convierte la entidad guardada (ya con ID) de vuelta a DTO para retornarla
        categoriaDTO.setId(savedCategoria.getId());
        return categoriaDTO;
    }

    @Override
    public CategoriaDTO updateCategoria(Integer id, CategoriaDTO categoriaDTO) {
        // Busca si la categoría a actualizar existe
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            // Actualiza los campos
            categoria.setNombre(categoriaDTO.getNombre());
            // Guarda los cambios en la BD
            Categoria updatedCategoria = categoriaRepository.save(categoria);

            // Mapea y devuelve el resultado
            CategoriaDTO dto = new CategoriaDTO();
            dto.setId(updatedCategoria.getId());
            dto.setNombre(updatedCategoria.getNombre());
            return dto;
        }
        // Si no se encuentra, devuelve null
        return null;
    }

    @Override
    public void deleteCategoria(Integer id) {
        // Borra la categoría por su ID. Si no existe, arrojará una excepción.
        categoriaRepository.deleteById(id);
    }
}
