package com.tonyra.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tipo_contribuyente")
public class TipoContribuyente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11, nullable = false)
	private Integer id_tipo_contribuyente;

	@Column(length = 50, nullable = true)
	private String nombre;

	@Column(nullable = false)
	private Boolean estado = true;

	public Integer getId_tipo_contribuyente() {
		return id_tipo_contribuyente;
	}

	public void setId_tipo_contribuyente(Integer id_tipo_contribuyente) {
		this.id_tipo_contribuyente = id_tipo_contribuyente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
