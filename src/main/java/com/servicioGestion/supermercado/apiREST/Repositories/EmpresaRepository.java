package com.servicioGestion.supermercado.apiREST.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicioGestion.supermercado.apiREST.Models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}
