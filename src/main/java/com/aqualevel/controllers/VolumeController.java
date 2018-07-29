package com.aqualevel.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aqualevel.controllers.beans.ApplicationUtil;
import com.aqualevel.model.Reservatorio;
import com.aqualevel.model.Sensor;
import com.aqualevel.model.Usuario;
import com.aqualevel.model.Volume;
import com.aqualevel.model.services.ReservatorioService;
import com.aqualevel.model.services.SensorService;
import com.aqualevel.model.services.VolumeService;

@Controller
@RequestMapping("/ver-volume")
public class VolumeController {

	@Autowired
	private ReservatorioService reservatorioService;
	
	@Autowired
	private SensorService sensorService;
	
	@Autowired
	private VolumeService volumeService;

	@GetMapping
	public ModelAndView getViewVolume() {
		if (getUsuarioLogado() != null) {
			List<Sensor> sensores = getSensorService().getAll();
			List<Reservatorio> reservatorios = new ArrayList<Reservatorio>();
			for (Sensor sensor : sensores) {
				reservatorios.add(sensor.getIdReservatorio());
			}
			
			ModelAndView mav = new ModelAndView("volume/select-reservatorio");
			mav.addObject("reservatorios", reservatorios);
			return mav.addObject(ApplicationUtil.getInstancia().getUsuarioLogado());
		}
		
		return new ModelAndView("redirect:/login");
	}
	
	@GetMapping("vol/{id}")
	public ModelAndView verVolume(@PathVariable long id, RedirectAttributes attributes) {
		Reservatorio reserv = getReservatorioService().getOneId(id);
		Volume vol = getVolumeService().getVolume(reserv);
		System.out.println("Volume: " + vol.toString());
		
		float capacidade = 0;
		
		switch (reserv.getTipo().getTipo()) {
		case "Cubico":
			capacidade = calculaCapacidadeCubico(reserv);
			break;
		case "Cilindrico":
			capacidade = calculaCapacidadeCilindrico(reserv);
			break;
		case "Tronco de Cone":
			capacidade = calculaCapacidadeTronDeCone(reserv);
			 break;

		default:
			break;
		}
		
		
		
		ModelAndView mav = new ModelAndView("volume/volume");
		mav.addObject("reservatorio", reserv);
		mav.addObject("capacidade", capacidade);
		mav.addObject("volume", vol);
		
		float faixas = capacidade / 3;
		if (vol.getVolume() <= faixas) {
			mav.addObject("faixaVermelho", "vermelho");
		}else if (vol.getVolume() > faixas && vol.getVolume() <= faixas*2) {
			mav.addObject("faixaAmarelo", "amarelo");
		}else {
			mav.addObject("faixaAzul", "azul");
		}
		
		return mav.addObject(getUsuarioLogado());
		
	}
	
		
	private float calculaCapacidadeCubico(Reservatorio reserv) {
		return (reserv.getAltura()*reserv.getLargura()*reserv.getProfundidade())/1000;
	}
	
	private float calculaCapacidadeCilindrico(Reservatorio reserv) {
		return (float) (Math.PI*(reserv.getRaio()*reserv.getRaio())*reserv.getAltura());
	}
	
	private float calculaCapacidadeTronDeCone(Reservatorio reserv) {
		System.out.println(Math.PI);
		return (float) (( ((Math.PI*reserv.getAltura()) / 3 ) * (Math.pow(reserv.getRaio(), 2)+(reserv.getRaio()*reserv.getRaioMenor())+( Math.pow(reserv.getRaioMenor(), 2) ) ) ) );
	}
	
	public VolumeService getVolumeService() {
		return volumeService;
	}

	public ReservatorioService getReservatorioService() {
		return reservatorioService;
	}

	public SensorService getSensorService() {
		return sensorService;
	}
	
	public Usuario getUsuarioLogado() {
		return ApplicationUtil.getInstancia().getUsuarioLogado();
	}
	
	
}
