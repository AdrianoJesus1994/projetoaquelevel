package com.aqualevel.model;


public class Perfil{

	private Long id;

	private String nomePerfil;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", nomePerfil=" + nomePerfil + "]";
	}
	
}
