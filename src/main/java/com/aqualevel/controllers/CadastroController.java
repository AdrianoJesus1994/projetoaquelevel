package com.aqualevel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aqualevel.controllers.beans.CadastroBean;
import com.aqualevel.model.Usuario;
import com.aqualevel.model.services.PerfilService;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {
	
	@Autowired
	PerfilService perfilService;
	
	@Autowired
	CadastroBean cadastro;
	
	@GetMapping
	public ModelAndView getCadastro() {
		ModelAndView cadastro = new ModelAndView("cadastro");		
		cadastro.addObject("usuario", new Usuario());
		return cadastro;
	}
			
	@PostMapping
	public ModelAndView setCadastro(Usuario usuario, RedirectAttributes attributes) {
			
		if(cadastro.cadastrarUsuario(usuario)) {
			attributes.addFlashAttribute("msgSuccess", "Seu cadastro foi realizado com sucesso");
			return new ModelAndView("redirect:/login");
		}
			
		ModelAndView cadastro = new ModelAndView("cadastro");
		cadastro.addObject("usuario", usuario);
		cadastro.addObject("msgError", "Este usuario já se encotra cadastrado no nosso sistema.");
		attributes.addAttribute("msgError", "Erro ao relizar o cadastro");
		return cadastro;		
		
	}

}
