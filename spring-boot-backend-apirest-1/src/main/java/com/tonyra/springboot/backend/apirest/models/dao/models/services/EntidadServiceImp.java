package com.tonyra.springboot.backend.apirest.models.dao.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tonyra.springboot.backend.apirest.models.dao.IEntidadDao;
import com.tonyra.springboot.backend.apirest.models.entity.Entidad;
import com.tonyra.springboot.backend.apirest.models.entity.TipoContribuyente;
import com.tonyra.springboot.backend.apirest.models.entity.TipoDocumento;

@Service
public class EntidadServiceImp implements IEntidadService{

	@Autowired
	private IEntidadDao entidadDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Entidad> findAll() {
		return (List<Entidad>) entidadDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Entidad finById(Integer id_entidad) {

		return entidadDao.findById(id_entidad).orElse(null);
	}

	@Override
	@Transactional
	public Entidad save(Entidad entidad) {
		
		return entidadDao.save(entidad);
	}

	@Override
	@Transactional
	public void delete(Integer id_entidad) {
		entidadDao.deleteById(id_entidad);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoDocumento> findAllDocumentos() {
		// TODO Auto-generated method stub
		return entidadDao.findAllDocumentos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoContribuyente> findAllContribuyentes() {
		// TODO Auto-generated method stub
		return entidadDao.findAllContribuyentes();
	}

}
