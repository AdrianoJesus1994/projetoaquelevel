package com.aqualevel.model;


public class TipoReservatorio{


	private Long id;
	
	private String tipo;

	public TipoReservatorio(Long id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	public TipoReservatorio() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoReservatorio [id=" + id + ", tipo=" + tipo + "]";
	}
	
	
	
	
}
