package com.aqualevel.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqualevel.model.Sensor;
import com.aqualevel.model.daos.interfaces.CrudPi;

@Service
public class SensorService {
	
	@Autowired
	private CrudPi<Sensor> repository;
	
	public List<Sensor> getAll() {
		return getRepository().getAll();
	}
	
	public Sensor getOneId(Long id) {
		return getRepository().getOneId(id);
	}
	
	public Sensor getOneName(String name) {
		return getRepository().getOneName(name);
	}
	
	public boolean setOne(Sensor sensor) {
		return getRepository().setOne(sensor);
	}
	
	public boolean setList(ArrayList<Sensor> sensores) {
		return getRepository().setList(sensores);
	}
	
	public Sensor updateOne(Sensor sensor) {
		return getRepository().updateOne(sensor);
	}
	
	public boolean deleteOne(Sensor sensor) {
		return getRepository().deleteOne(sensor);
	}
	
	public boolean deleteAll() {
		return getRepository().deleteAll();
	}

	public CrudPi<Sensor> getRepository() {
		return repository;
	}

}
