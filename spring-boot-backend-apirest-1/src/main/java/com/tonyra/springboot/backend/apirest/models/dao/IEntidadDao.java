package com.tonyra.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tonyra.springboot.backend.apirest.models.entity.Entidad;
import com.tonyra.springboot.backend.apirest.models.entity.TipoContribuyente;
import com.tonyra.springboot.backend.apirest.models.entity.TipoDocumento;

public interface IEntidadDao extends CrudRepository<Entidad,Integer>{

	@Query("from TipoDocumento")
	public List<TipoDocumento> findAllDocumentos();
	
	
	@Query("from TipoContribuyente")
	public List<TipoContribuyente> findAllContribuyentes();
}
