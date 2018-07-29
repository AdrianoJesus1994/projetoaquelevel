package com.aqualevel.controllers;

import java.util.ArrayList;

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
import com.aqualevel.controllers.beans.ReservatorioBean;
import com.aqualevel.model.Reservatorio;
import com.aqualevel.model.services.ReservatorioService;
import com.aqualevel.model.services.TipoReservatorioService;

@Controller
@RequestMapping("/reservatorios")
public class ReservatorioController {
	
	@Autowired
	private ReservatorioBean reservatorioBean;
	
	@Autowired
	private TipoReservatorioService tipoReservatorio;
	
	private ReservatorioService reservatorioRepository;
	

	@GetMapping
	public ModelAndView getListReservatorios() {
		
		if (ApplicationUtil.getInstancia().getUsuarioLogado() != null) {
			
			//System.out.println(ApplicationUtil.getInstancia().getUsuarioLogado());
			
			ModelAndView mav = new ModelAndView("reservatorios/list-reservatorios");
			ArrayList<Reservatorio> lista = getReservatorioBean().listReservatorios();
			mav.addObject("reservatorios", lista);
			return mav.addObject(ApplicationUtil.getInstancia().getUsuarioLogado());
		}
		return new ModelAndView("redirect:/login");
		
	}
	
	@GetMapping("/add")
	public ModelAndView getAddReservatorio() {
		if (ApplicationUtil.getInstancia().getUsuarioLogado() != null) {
			
			//System.out.println(ApplicationUtil.getInstancia().getUsuarioLogado());
			
			ModelAndView mav = new ModelAndView("reservatorios/add-reservatorio");
			mav.addObject("reservatorio", new Reservatorio());
			mav.addObject("tipos", getTipoReservatorio().getAll());
			return mav.addObject(ApplicationUtil.getInstancia().getUsuarioLogado());
		}
		return new ModelAndView("redirect:/login");
	}
	
	@PostMapping("/add")
	public ModelAndView setAddReservatorio(Reservatorio reservatorio, RedirectAttributes redirectAttributes) {
		System.out.println(reservatorio.toString());
		
		if(getReservatorioBean().setReservatorio(reservatorio)) {
			redirectAttributes.addFlashAttribute("msgSuccess", "Seu cadastro foi realizado com sucesso");
			return new ModelAndView("redirect:/reservatorios");
		}
		
		ModelAndView add = new ModelAndView("reservatorios/add-reservatorio");
		add.addObject("reservatorio", reservatorio);
		add.addObject("msgError", "Erro ao cadastrar o reservatorio");	
		return add;
	}
	
	@PostMapping("/edit")
	public ModelAndView setEditReservatorio(Reservatorio reservatorio, RedirectAttributes redirectAttributes) {
	
		if(getReservatorioBean().setUpdateReservatorio(reservatorio)) {
			redirectAttributes.addFlashAttribute("msgSuccess", "Seu cadastro foi realizado com sucesso");
			return new ModelAndView("redirect:/reservatorios");
		}
		
		ModelAndView add = new ModelAndView("reservatorios/edit-reservatorio");
		add.addObject("reservatorio", reservatorio);
		add.addObject("tipos", getTipoReservatorio().getAll());
		add.addObject("msgError", "Erro ao cadastrar o reservatorio");	
		return add.addObject(ApplicationUtil.getInstancia().getUsuarioLogado());
	}
		
	@DeleteMapping("/remover/{id}")
	public ModelAndView setConfirmDelete(@PathVariable Long id, RedirectAttributes attributes) {
		if(getReservatorioBean().deleteReservatorio(id)) {
			attributes.addFlashAttribute("msgDelete", "Reservatório removido com sucesso.");
			return new ModelAndView("redirect:/reservatorios");
		}
		
		attributes.addFlashAttribute("msgDeleteErro", "Erro ao remover reservatório.");
		return new ModelAndView("redirect:/reservatorios");		
	}
	
	@PutMapping("/alterar/{id}")
	public ModelAndView setEditReservatorio(@PathVariable Long id, RedirectAttributes attributes) {
		
		ModelAndView mav = new ModelAndView("reservatorios/edit-reservatorio");
		mav.addObject("reservatorio", getReservatorioBean().getReservatorio(id));
		mav.addObject("tipos", getTipoReservatorio().getAll());
		return mav.addObject(ApplicationUtil.getInstancia().getUsuarioLogado());
		
	}
	
	@GetMapping("detalhes/{id}")
	public ModelAndView getDetalhesReservatorio(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("reservatorios/details-reservatorio");
		mav.addObject("reservatorio", getReservatorioBean().getReservatorio(id));
		return mav.addObject(ApplicationUtil.getInstancia().getUsuarioLogado());
	}
	
	
	public ReservatorioService getReservatorioRepository() {
		return reservatorioRepository;
	}

	public ReservatorioBean getReservatorioBean() {
		return reservatorioBean;
	}
	
	public TipoReservatorioService getTipoReservatorio() {
		return tipoReservatorio;
	}

	

}
