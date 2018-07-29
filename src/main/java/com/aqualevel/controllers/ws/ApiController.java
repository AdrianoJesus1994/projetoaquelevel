package com.aqualevel.controllers.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private ApiBean apiBean;
	
	@GetMapping(value="/arduino")
	public @ResponseBody String setArduino(@RequestParam("sensor") long sensor, @RequestParam("valor") float valor){
		System.out.println("Recebendo a chama: " + sensor + ", valor: " + valor);
		getApiBean().setVolume(sensor, valor);
		return "0";
	}

	public ApiBean getApiBean() {
		return apiBean;
	}

	
	

}
