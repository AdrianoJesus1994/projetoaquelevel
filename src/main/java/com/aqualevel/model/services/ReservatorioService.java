package com.aqualevel.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqualevel.model.Reservatorio;
import com.aqualevel.model.daos.interfaces.CrudPi;

@Service
public class ReservatorioService {

	
	@Autowired
	private CrudPi<Reservatorio> repository;
	
	public List<Reservatorio> getAll() {
		return getRepository().getAll();
	}
	
	public Reservatorio getOneId(Long id) {
		return getRepository().getOneId(id);
	}
	
	public Reservatorio getOneName(String name) {
		return getRepository().getOneName(name);
	}
	
	public boolean setOne(Reservatorio reserv) {
		return getRepository().setOne(reserv);
	}
	
	public boolean setList(ArrayList<Reservatorio> reservatorios) {
		return getRepository().setList(reservatorios);
	}
	
	public Reservatorio updateOne(Reservatorio reserv) {
		return getRepository().updateOne(reserv);
	}
	
	public boolean deleteOne(Reservatorio reserv) {
		return getRepository().deleteOne(reserv);
	}
	
	public boolean deleteAll() {
		return getRepository().deleteAll();
	}

	public CrudPi<Reservatorio> getRepository() {
		return repository;
	}


}
