package com.aqualevel.model;

public class Sensor{

	private Long id;
	
	private String nome;

	private Long codigo;

	private Reservatorio idReservatorio;	
	
	public Sensor() {
		this.idReservatorio = new Reservatorio();
		this.codigo = (long) 0;
		this.nome = "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Reservatorio getIdReservatorio() {
		return idReservatorio;
	}

	public void setIdReservatorio(Reservatorio idReservatorio) {
		this.idReservatorio = idReservatorio;
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", nome=" + nome + ", codigo=" + codigo + ", idReservatorio=" + idReservatorio
				+ "]";
	}
	
	
	
}
