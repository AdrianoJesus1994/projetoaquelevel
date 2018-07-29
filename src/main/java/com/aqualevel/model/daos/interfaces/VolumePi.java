package com.aqualevel.model.daos.interfaces;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.aqualevel.model.Reservatorio;
import com.aqualevel.model.Volume;

@Component
public interface VolumePi {
	public Volume getVolume(Reservatorio reserv);
	
	public boolean setVolume(Volume vol);
	
	public ArrayList<Volume> getHistoricoVolume(Reservatorio reserv, int mes, int mesfim);
}
