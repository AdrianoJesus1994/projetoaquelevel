package com.aqualevel.model.daos.interfaces;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public interface CrudPi<T> {
	
	public ArrayList<T> getAll();
	
	public T getOneId(Long id);
	
	public T getOneName(String name);
	
	public boolean setOne(T element); 
	
	public boolean setList(ArrayList<T> lista);
	
	public T updateOne(T element);
	
	public boolean deleteOne(T element);
	
	public boolean deleteAll();
	
}
