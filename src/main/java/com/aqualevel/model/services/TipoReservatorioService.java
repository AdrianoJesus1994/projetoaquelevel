package com.aqualevel.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqualevel.model.TipoReservatorio;
import com.aqualevel.model.daos.interfaces.CrudPi;

@Service
public class TipoReservatorioService {

	
	@Autowired
	private CrudPi<TipoReservatorio> repository;
	
	public List<TipoReservatorio> getAll() {
		return getRepository().getAll();
	}
	
	public TipoReservatorio getOneId(long id) {
		return getRepository().getOneId(id);
	}
	
	public TipoReservatorio getOneName(String name) {
		return getRepository().getOneName(name);
	}
	
	public boolean setOne(TipoReservatorio type) {
		return getRepository().setOne(type);
	}
	
	public boolean setList(ArrayList<TipoReservatorio> types) {
		return getRepository().setList(types);
	}
	
	public TipoReservatorio updateOne(TipoReservatorio type) {
		return getRepository().updateOne(type);
	}
	
	public boolean deleteOne(TipoReservatorio type) {
		return getRepository().deleteOne(type);
	}
	
	public boolean deleteAll() {
		return getRepository().deleteAll();
	}

	public CrudPi<TipoReservatorio> getRepository() {
		return repository;
	}

	
}
