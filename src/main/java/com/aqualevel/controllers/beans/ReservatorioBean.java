package com.aqualevel.controllers.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aqualevel.model.Reservatorio;
import com.aqualevel.model.services.ReservatorioService;

@Component
public class ReservatorioBean {
	
	@Autowired
	private ReservatorioService reservatorioRepository;
	
	public ArrayList<Reservatorio> listReservatorios() {
		ArrayList<Reservatorio> list = new ArrayList<>();
		List<Reservatorio> reservatorios = getReservatorioRepository().getAll();
		for (Reservatorio reservatorio : reservatorios) {
			list.add(reservatorio);
		}
		return list;
	}
	
	public boolean setReservatorio(Reservatorio reserv) {
		reserv.setUsuario(ApplicationUtil.getInstancia().getUsuarioLogado());
		if(getReservatorioRepository().setOne(reserv)) {
			return true;
		}		
		return false;		
	}
	
	public boolean setUpdateReservatorio(Reservatorio reserv) {
		reserv.setUsuario(ApplicationUtil.getInstancia().getUsuarioLogado());
		if(getReservatorioRepository().updateOne(reserv) != null) {
			return true;
		}		
		return false;		
	}
	
	public boolean deleteReservatorio(Long id) {
		Reservatorio reserv = getReservatorioRepository().getOneId(id);
		System.out.println("Reservatorio: " + reserv.toString());
		//List<Sensor> sensores = getSensorService().getAll();
		
		//verifica se tem um sensor vinculado a este reservatorio
		/*for (Sensor sensor : sensores) {
			if(sensor.getIdReservatorio().getId().equals(reserv.getId())) {
				sensor.setIdReservatorio(new Reservatorio());
				System.out.println("Sensor: " + sensor.toString());
				//getSensorService().updateOne(sensor);
			}
		}*/
		
		if(getReservatorioRepository().deleteOne(reserv)) {
			return true;
		}		
		
		 return false;
	}
	
	public Reservatorio getReservatorio(Long id) {
		Reservatorio reserv = getReservatorioRepository().getOneId(id);
		System.out.println("Reservatorio: " + reserv.toString());
		
		return reserv;
	}

	public ReservatorioService getReservatorioRepository() {
		return reservatorioRepository;
	}


}
