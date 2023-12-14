package com.tonyra.springboot.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tonyra.springboot.backend.apirest.models.dao.models.services.IEntidadService;
import com.tonyra.springboot.backend.apirest.models.entity.Entidad;
import com.tonyra.springboot.backend.apirest.models.entity.TipoContribuyente;
import com.tonyra.springboot.backend.apirest.models.entity.TipoDocumento;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EntidadRestController {

	@Autowired
	private IEntidadService entidadService; 
	
	@GetMapping("/entidades")
	public List<Entidad> index(){
		return entidadService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/entidades/{id_entidad}")
	public ResponseEntity<?> show(@PathVariable Integer id_entidad) {
		
		Entidad entidad = null;
		Map<String,Object> response=new HashMap<>();
		try {
			entidad = entidadService.finById(id_entidad);
			
		} catch(DataAccessException e) {
			response.put("mensaje","Error al realizar la consulta");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		if(entidad==null) {
		response.put("mensaje","La Entidad con ID:".concat(id_entidad.toString().concat("  no existe en la base de datos")));
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Entidad>(entidad,HttpStatus.OK);
		
	}
	
	

	/*@Secured({"ROLE_ADMIN"})*/
	@PostMapping("/entidades")
	public ResponseEntity<?> create(@Valid @RequestBody Entidad entidad,BindingResult result) {
		
		Entidad entidadNew = null;
		Map<String,Object> response=new HashMap<>();
		
		if(result.hasErrors()) {
		
		
		List<String> errors = result.getFieldErrors()
				.stream()
				.map(err -> "El campo  '"+err.getField()+"' "+err.getDefaultMessage() )
				.collect(Collectors.toList());
		
		response.put("errors",errors);
	     return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			entidadNew=	entidadService.save(entidad);
		} catch(DataAccessException e) {
			response.put("mensaje","Error al crear la entidad");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Entidad fue creado con exito!");
		response.put("Entidad",entidadNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/entidades/{id_entidad}")
	
	public ResponseEntity<?> update(@Valid @RequestBody Entidad entidad,BindingResult result, @PathVariable Integer id_entidad) {
		
	Entidad entidadActual = entidadService.finById(id_entidad);
	Entidad entidadUpdate = null;
	Map<String,Object> response=new HashMap<>();
	
	
	if(result.hasErrors()) {
	
	
	List<String> errors = result.getFieldErrors()
			.stream()
			.map(err -> "El campo  '"+err.getField()+"' "+err.getDefaultMessage() )
			.collect(Collectors.toList());
	
	response.put("errors",errors);
     return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	if(entidadActual==null) {
		response.put("mensaje","Error: No se pudo editar la entidad con ID:".concat(id_entidad.toString().concat("  no existe en la base de datos")));
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
	try {
		
	entidadActual.setId_tipo_documento(entidad.getId_tipo_documento());	
	entidadActual.setNro_documento(entidad.getNro_documento());
	entidadActual.setRazon_social(entidad.getRazon_social());
	entidadActual.setNombre_comercial(entidad.getNombre_comercial());
	entidadActual.setId_tipo_contribuyente(entidad.getId_tipo_contribuyente());
	
	entidadActual.setDireccion(entidad.getDireccion());
	entidadActual.setTelefono(entidad.getTelefono());
	
	entidadUpdate = entidadService.save(entidadActual);
	} catch(DataAccessException e) {
		response.put("mensaje","Error al actualizar en la BASE DE DATOS");
		response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	response.put("mensaje", "La entidad fue actualizado con exito!");
	response.put("Entidad",entidadUpdate);
	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

	}
	

	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/entidades/{id_entidad}")
	
	
	public ResponseEntity<?> delete(@PathVariable Integer id_entidad) {
		
		Map<String,Object> response=new HashMap<>();
		try {
		entidadService.delete(id_entidad);
		} catch(DataAccessException e) {
			response.put("mensaje","Error al eliminar en la BASE DE DATOS");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Entidad fue eliminada con exito!");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/entidades/tipo_documentos")
	public List<TipoDocumento> listarDocumentos(){
		return entidadService.findAllDocumentos();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/entidades/tipo_contribuyentes")
	public List<TipoContribuyente> listarContribuyentess(){
		return entidadService.findAllContribuyentes();
	}
	
}
