package com.tonyra.springboot.backend.apirest.models.dao.models.services;

import com.tonyra.springboot.backend.apirest.models.entity.Usuario;


public interface IUsuarioService {
	
	public Usuario findByUsername2(String username);

}
