package com.aqualevel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aqualevel.controllers.beans.ApplicationUtil;
import com.aqualevel.model.Usuario;
import com.aqualevel.model.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ModelAndView getEditUsuario() {
		ModelAndView mav = new ModelAndView("usuario/edit-usuario");
		mav.addObject("usuario", getUsuarioLogado());
		return mav;
	}
	
	@PostMapping("/update")
	public ModelAndView setUpdateUsuario(Usuario user, RedirectAttributes attributes) {
		if (getUsuarioService().updateOne(user) != null) {
			return new ModelAndView("redirect/home");
		}
		ModelAndView mav = new ModelAndView("usuario/edit-usuario");
		attributes.addFlashAttribute("msgError", "erro");
		mav.addObject("usuario", getUsuarioLogado());
		return mav;
	}
	
	private Usuario getUsuarioLogado() {
		return ApplicationUtil.getInstancia().getUsuarioLogado();
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}
	
	

	

}
