package com.cvh.rest.jpa.pruebaspringtotal.controladores;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cvh.rest.jpa.pruebaspringtotal.entidades.Factura;
import com.cvh.rest.jpa.pruebaspringtotal.repositorios.FacturaRepositorio;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/api/facturas")
public class FacturaApiController {

	@Autowired
	private FacturaRepositorio repo;
	
	@GetMapping
	public Iterable<Factura> get() {
		return repo.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Factura> get(@PathVariable Long id) {
		Optional<Factura> cliente = repo.findById(id);
		
		if(cliente.isPresent()) {
			return new ResponseEntity<Factura>(cliente.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Factura>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Factura post(@RequestBody Factura factura) {
		// TODO no serializa bien la venta (pedir en crudo el json)
		log.info(factura.getVentas().toString());
		return repo.save(factura);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Factura> put(@PathVariable Long id, @RequestBody Factura factura) {
		if(id == factura.getId()) {
			if(repo.existsById(id)) {
				return new ResponseEntity<Factura>(repo.save(factura), HttpStatus.OK);	
			} else {
				return new ResponseEntity<Factura>(HttpStatus.NOT_FOUND);
			}						
		} else {
			return new ResponseEntity<Factura>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Factura> delete(@PathVariable Long id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return new ResponseEntity<Factura>(HttpStatus.NO_CONTENT);	
		} else {
			return new ResponseEntity<Factura>(HttpStatus.NOT_FOUND);
		}
	}
	
}
