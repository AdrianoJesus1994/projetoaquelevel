package com.aqualevel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aqualevel.controllers.beans.ApplicationUtil;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping
	public ModelAndView getHome() {
		if (ApplicationUtil.getInstancia().getUsuarioLogado() != null) {
			//System.out.println(ApplicationUtil.getInstancia().getUsuarioLogado().toString());
			return new ModelAndView("home").addObject(ApplicationUtil.getInstancia().getUsuarioLogado());
		}
		
		return new ModelAndView("redirect:/login");
		
	}

}
