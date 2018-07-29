package com.aqualevel.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqualevel.model.Usuario;
import com.aqualevel.model.daos.interfaces.CrudPi;

@Service
public class UsuarioService {
	
	@Autowired
	private CrudPi<Usuario> repository;
	
	public List<Usuario> getUsuarios() {
		return getRepository().getAll();
	}
	
	public Usuario getOneId(long id) {
		return getRepository().getOneId(id);
	}
		
	public Usuario getOneName(String name) {
		return getRepository().getOneName(name);
	}
	
	public boolean setOne(Usuario user) {
		return getRepository().setOne(user);
	}
	
	public boolean setList(ArrayList<Usuario> users) {
		return getRepository().setList(users);
	}
	
	public Usuario updateOne(Usuario user) {
		return getRepository().updateOne(user);
	}
	
	public boolean deleteOne(Usuario user) {
		return getRepository().deleteOne(user);
	}
	
	public boolean deleteAll() {
		return getRepository().deleteAll();
	}

	public CrudPi<Usuario> getRepository() {
		return repository;
	}


}
