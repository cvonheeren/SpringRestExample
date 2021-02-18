package com.cvh.rest.jpa.pruebaspringtotal.repositorios;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cvh.rest.jpa.pruebaspringtotal.entidades.Factura;

public interface FacturaRepositorio extends PagingAndSortingRepository<Factura, Long>{

}
