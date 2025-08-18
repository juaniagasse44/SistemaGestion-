package com.servicioGestion.supermercado.apiREST.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicioGestion.supermercado.apiREST.Models.Categoria;

@Repository // 1. Le indica a Spring que esta es una interfaz de repositorio
public interface  CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
