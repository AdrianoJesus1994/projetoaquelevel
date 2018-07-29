package com.aqualevel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aqualevel.controllers.beans.ApplicationUtil;
import com.aqualevel.controllers.beans.LoginBean;
import com.aqualevel.model.Usuario;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginBean loginBean;
	
	@GetMapping
	public ModelAndView getLogin() {
		ModelAndView view = new ModelAndView("login");
		view.addObject("usuario", new Usuario());
		return view;
	}
	
	@GetMapping("/logout")
	public ModelAndView setLogout() {
		ApplicationUtil.getInstancia().setUsuarioVazio();
		return new ModelAndView("redirect:/login");
	}
	
	@PostMapping
	public ModelAndView setLogin(Usuario user, RedirectAttributes attributes) {
		
		if (loginBean.validateUser(user)) {
			return new ModelAndView("redirect:/home");
		}
		attributes.addFlashAttribute("msgError", "erro");
		return new ModelAndView("redirect:/login");
	}
	
	

}
