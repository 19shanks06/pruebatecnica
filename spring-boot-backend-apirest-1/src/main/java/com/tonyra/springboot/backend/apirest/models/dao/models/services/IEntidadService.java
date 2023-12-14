package com.tonyra.springboot.backend.apirest.models.dao.models.services;

import java.util.List;

import com.tonyra.springboot.backend.apirest.models.entity.Entidad;
import com.tonyra.springboot.backend.apirest.models.entity.TipoContribuyente;
import com.tonyra.springboot.backend.apirest.models.entity.TipoDocumento;

public interface IEntidadService {
	
	public List<Entidad> findAll();
	
	
	public Entidad finById(Integer id_entidad);
	
	public Entidad save(Entidad entidad);
	
	public void delete(Integer id_entidad);
	
	public List<TipoDocumento> findAllDocumentos();
	
	public List<TipoContribuyente> findAllContribuyentes();

}
