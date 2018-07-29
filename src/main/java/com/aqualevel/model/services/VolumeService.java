package com.aqualevel.model.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqualevel.model.Reservatorio;
import com.aqualevel.model.Volume;
import com.aqualevel.model.daos.interfaces.VolumePi;

@Service
public class VolumeService {
	
	@Autowired
	private VolumePi repository;
	
	public Volume getVolume(Reservatorio reserv) {
		return getRepository().getVolume(reserv);
	}
	
	public boolean setVolume(Volume vol) {
		return getRepository().setVolume(vol);
	}
	
	public ArrayList<Volume> getHistoricoVolume(Reservatorio reserv, int mes, int mesfim) {
		return getRepository().getHistoricoVolume(reserv, mes, mesfim);
	}

	private VolumePi getRepository() {
		return repository;
	}

}
