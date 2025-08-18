package com.servicioGestion.supermercado.apiREST.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicioGestion.supermercado.apiREST.Models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
