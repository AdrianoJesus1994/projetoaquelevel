package com.aqualevel.model;

import java.sql.Date;

public class Volume{

	private long id;
	
	private Reservatorio reserv;

	private float volume;

	private Date data;
	
	public Volume() {
		super();
		this.reserv = new Reservatorio();
		this.volume = 0F;
		this.data = new Date(new java.util.Date().getTime());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Reservatorio getReserv() {
		return reserv;
	}

	public void setReserv(Reservatorio reserv) {
		this.reserv = reserv;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Volume [id=" + id + ", reserv=" + reserv + ", volume=" + volume + ", data=" + data + "]";
	}
	
	
	
}
