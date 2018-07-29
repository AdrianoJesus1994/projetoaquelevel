package com.aqualevel.controllers.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aqualevel.model.Sensor;
import com.aqualevel.model.services.ReservatorioService;
import com.aqualevel.model.services.SensorService;

@Component
public class SensorBean {
	
	/* Trata as informações que vem do arduino via requisição http */
	
	@Autowired
	private SensorService sensorService;
	
	@Autowired
	private ReservatorioService reservatorioService;
	
	public ArrayList<Sensor> getListSensores(){
		ArrayList<Sensor> list = new ArrayList<>();
		List<Sensor> sensores = getSensorService().getAll();
		for (Sensor sensor : sensores) {
			list.add(sensor);
		}
		return list;
	}
	
	public boolean getValidaSensorCodigo(Sensor sens) {
		List<Sensor> sensorList = getSensorService().getAll();
		for (Sensor sensor : sensorList) {
			if(sensor.getCodigo().equals(sens.getCodigo())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean getValidaSensorReservatorio(Sensor sensor) {
		List<Sensor> sensores = getSensorService().getAll();
			for(Sensor sens : sensores) {
				System.out.println("Reservatório validação: " + sens.toString());
				if(sens.getId() != null) {
					if(sens.getIdReservatorio().getId().equals(sensor.getIdReservatorio().getId())) {
						return false;
					}
				}				
			}
			
		return true;
	}
	
	public boolean deleteSensor(long id) {
		Sensor sensor = getSensorService().getOneId(id);
		if(getSensorService().deleteOne(sensor)) {
			return true;
		}
		return false;
	}
	
	


	public ReservatorioService getReservatorioService() {
		return reservatorioService;
	}

	public SensorService getSensorService() {
		return sensorService;
	}

}
