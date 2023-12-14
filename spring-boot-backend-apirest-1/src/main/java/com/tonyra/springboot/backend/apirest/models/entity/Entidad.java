package com.tonyra.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_entidad")
public class Entidad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11, nullable = false)
	private Integer id_entidad;
   
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_documento")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private TipoDocumento id_tipo_documento;

	@NotEmpty
	@Size(max=25)
	@Column(length = 25, unique = true)
	private String nro_documento;
	
	@NotEmpty
	@Size(max=100)
	@Column(length = 100)
	private String razon_social;
	
	@Column(length = 100, nullable = true)
	private String nombre_comercial;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_contribuyente")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private TipoContribuyente id_tipo_contribuyente;

	@Size(max=250)
	@Column(length = 250, nullable = true)
	private String direccion;
	
	@Size(min=7,max=50)
	@Column(length = 50, nullable = true)
	private String telefono;
	@Column(nullable = false)
	private Boolean estado = true;

	public Integer getId_entidad() {
		return id_entidad;
	}

	public void setId_entidad(Integer id_entidad) {
		this.id_entidad = id_entidad;
	}

	public TipoDocumento getId_tipo_documento() {
		return id_tipo_documento;
	}

	public void setId_tipo_documento(TipoDocumento id_tipo_documento) {
		this.id_tipo_documento = id_tipo_documento;
	}

	public TipoContribuyente getId_tipo_contribuyente() {
		return id_tipo_contribuyente;
	}

	public void setId_tipo_contribuyente(TipoContribuyente id_tipo_contribuyente) {
		this.id_tipo_contribuyente = id_tipo_contribuyente;
	}

	public String getNro_documento() {
		return nro_documento;
	}

	public void setNro_documento(String nro_documento) {
		this.nro_documento = nro_documento;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getNombre_comercial() {
		return nombre_comercial;
	}

	public void setNombre_comercial(String nombre_comercial) {
		this.nombre_comercial = nombre_comercial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
