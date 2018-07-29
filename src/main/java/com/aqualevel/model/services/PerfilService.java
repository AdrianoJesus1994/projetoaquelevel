package com.aqualevel.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqualevel.model.Perfil;
import com.aqualevel.model.daos.interfaces.CrudPi;

@Service	
public class PerfilService {
	
	@Autowired
	private CrudPi<Perfil> repository;

	public List<Perfil> getAll() {
		return getRepository().getAll();
	}
	
	public Perfil getOneId(long id) {
		return getRepository().getOneId(id);
	}
	
	public Perfil getOneName(String name) {
		return getRepository().getOneName(name);
	}
	
	public boolean setOne(Perfil p) {
		return getRepository().setOne(p);
	}
	
	public boolean setList(ArrayList<Perfil> perf) {
		return getRepository().setList(perf);
	}
	
	public Perfil updateOne(Perfil perf) {
		return getRepository().updateOne(perf);
	}
	
	public boolean deleteOne(Perfil perf) {
		return getRepository().deleteOne(perf);
	}
	
	public boolean deleteAll() {
		return getRepository().deleteAll();
	}

	public CrudPi<Perfil> getRepository() {
		return repository;
	}

}
