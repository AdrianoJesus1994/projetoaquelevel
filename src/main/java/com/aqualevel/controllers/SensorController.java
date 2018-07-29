package com.aqualevel.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aqualevel.controllers.beans.ApplicationUtil;
import com.aqualevel.controllers.beans.SensorBean;
import com.aqualevel.model.Reservatorio;
import com.aqualevel.model.Sensor;
import com.aqualevel.model.Usuario;
import com.aqualevel.model.services.ReservatorioService;
import com.aqualevel.model.services.SensorService;

@Controller
@RequestMapping("/sensores")
public class SensorController {
	
	@Autowired
	private SensorBean sensorBean;
	
	@Autowired
	private SensorService sensorService;
	
	@Autowired
	private ReservatorioService reservatorioService;
	
	@GetMapping
	public ModelAndView getSensores() {
		if (ApplicationUtil.getInstancia().getUsuarioLogado() != null) {			
			ModelAndView mav = new ModelAndView("sensores/list-sensores");			
			List<Sensor> list = getSensorService().getAll();
			ArrayList<Sensor> lista = new ArrayList<>();
			for (Sensor sensor : list) {
				if (sensor.getIdReservatorio() != null) {
					
					List<Reservatorio> reservatorios = getReservatorioService().getAll();
					for (Reservatorio reservatorio : reservatorios) {
						if (reservatorio.getId().equals(sensor.getIdReservatorio().getId())) {
							lista.add(sensor);
						}
					}
				}
			}			
			mav.addObject("sensores", lista);
			return mav.addObject(getUsuarioLogado());
		}
		return new ModelAndView("redirect:/login");
	}
	
	@GetMapping("/add")
	public ModelAndView getAddSensor() {
		if (ApplicationUtil.getInstancia().getUsuarioLogado() != null) {
			List<Reservatorio> reservatorios = getReservatorioService().getAll();
			ModelAndView mav = new ModelAndView("sensores/add-sensores");
			mav.addObject("reservatorios", reservatorios);
			mav.addObject("sensor", new Sensor());
			return mav.addObject(getUsuarioLogado());
		}
		return new ModelAndView("redirect:/login");
	}
	
	@PostMapping("/add")
	public ModelAndView setAddSensor(Sensor sensor, RedirectAttributes attributes) {		
		//Carregando o reservatório
		sensor.setIdReservatorio(getReservatorioService().getOneId(sensor.getIdReservatorio().getId()));
			
		if (getSensorBean().getValidaSensorCodigo(sensor)) {
			if (getSensorBean().getValidaSensorReservatorio(sensor)) {
				if(getSensorService().setOne(sensor)) {
					attributes.addFlashAttribute("msgSucess", "Sensor cadastrado com sucesso!");
					return new ModelAndView("redirect:/sensores");
				}
			}
			attributes.addFlashAttribute("msgError", "Já existe um sensor cadastrado para o reservatorio indicado!");
			return new ModelAndView("redirect:/sensores/add");
		}
		attributes.addFlashAttribute("msgError", "Sensor já cadastrado!");
		return new ModelAndView("redirect:/sensores/add");
				
	}
	
	@DeleteMapping("remover/{id}")
	public ModelAndView setDeleteSensor(@PathVariable Long id, RedirectAttributes attributes) {
		if(getSensorBean().deleteSensor(id)) {
			attributes.addFlashAttribute("msgDelete", "Sensor removido com sucesso.");
			return new ModelAndView("redirect:/sensores");
		}
		
		attributes.addFlashAttribute("msgDeleteErro", "Erro ao remover sensor.");
		return new ModelAndView("redirect:/sensores");		
		
	}
	
	@PutMapping("/alterar/{id}")
	public ModelAndView setGetUpdateSensor(@PathVariable long id, RedirectAttributes attributes) {
		ModelAndView mav = new ModelAndView("sensores/edit-sensores");
		mav.addObject("sensor", getSensorService().getOneId(id));
		mav.addObject("reservatorios", getReservatorioService().getAll());
		return mav.addObject(getUsuarioLogado());
	}
	
	@PostMapping("edit")
	public ModelAndView setUpdateSensor(Sensor sensor,  RedirectAttributes redirectAttributes) {
		if(getSensorService().updateOne(sensor) != null) {
			redirectAttributes.addFlashAttribute("msgSuccess", "Seu cadastro foi realizado com sucesso");
			return new ModelAndView("redirect:/sensores");
		}
		
		ModelAndView add = new ModelAndView("sensores/edit-sensores");
		add.addObject("sensor", sensor);
		add.addObject("reservatorios", getReservatorioService().getAll());
		add.addObject("msgError", "Erro ao cadastrar o reservatorio");	
		return add.addObject(getUsuarioLogado());
	}
	


	private Usuario getUsuarioLogado() {
		return ApplicationUtil.getInstancia().getUsuarioLogado();
	}	
	
	public ReservatorioService getReservatorioService() {
		return reservatorioService;
	}

	public SensorService getSensorService() {
		return sensorService;
	}
	
	public SensorBean getSensorBean() {
		return sensorBean;
	}

}
