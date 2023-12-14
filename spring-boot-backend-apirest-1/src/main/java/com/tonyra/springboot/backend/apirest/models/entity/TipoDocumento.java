package com.tonyra.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tipo_documento")
public class TipoDocumento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11, nullable = false)
	private Integer id_tipo_documento;
	@Column(length = 20, nullable = false)
	private String codigo;
	@Column(length = 100, nullable = false)
	private String nombre;

	@Column(length = 200, nullable = true)
	private String descripcion;

	@Column(nullable = false)
	private Boolean estado = true;

	public Integer getId_tipo_documento() {
		return id_tipo_documento;
	}

	public void setId_tipo_documento(Integer id_tipo_documento) {
		this.id_tipo_documento = id_tipo_documento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
