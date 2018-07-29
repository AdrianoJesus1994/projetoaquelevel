package com.aqualevel.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aqualevel.controllers.beans.ApplicationUtil;
import com.aqualevel.model.Reservatorio;
import com.aqualevel.model.Usuario;
import com.aqualevel.model.Volume;
import com.aqualevel.model.services.ReservatorioService;
import com.aqualevel.model.services.VolumeService;


@Controller
@RequestMapping("/relatorios")
public class RelatorioController {
	
	@Autowired
	private VolumeService volumeService;
	
	@Autowired
	private ReservatorioService reservatorioService;

	@GetMapping
	public ModelAndView getRealatorios() {
		
		if(getUsuarioLogado() != null) {
			ModelAndView mav = new ModelAndView("relatorios/relatorio-mes");
			List<Reservatorio> reservatorios = getReservatorioService().getAll();
			mav.addObject("reservatorios", reservatorios);
			mav.addObject(getUsuarioLogado());
			return mav;
		}
		return new ModelAndView("redirect:/login");
	}
	
	@GetMapping(value="/historico", produces="application/json")
	public @ResponseBody ArrayList<Volume> getHistorioVolume(@RequestParam("reserv") long id, @RequestParam("ini") int mes, @RequestParam("fim") int mesfim) {
		System.out.println("Id:" + id + "Mes Inicio:" + mes + "Mes Fim: " + mesfim);
		
		return getVolumeService().getHistoricoVolume(getReservatorioService().getOneId(id), mes, mesfim);
	}
	
	

	public ReservatorioService getReservatorioService() {
		return reservatorioService;
	}

	public VolumeService getVolumeService() {
		return volumeService;
	}



	public Usuario getUsuarioLogado() {
		return ApplicationUtil.getInstancia().getUsuarioLogado();
	}
}
