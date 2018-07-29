package com.aqualevel.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aqualevel.model.Perfil;
import com.aqualevel.model.Usuario;
import com.aqualevel.model.services.PerfilService;
import com.aqualevel.model.services.UsuarioService;

@Controller
public class IndexController {

	@Autowired
	PerfilService perfilService;

	@Autowired
	UsuarioService service;
	
	@RequestMapping("/")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping("/contato")
	public String getContato() {
		return "contato";
	}
	
	@RequestMapping("/sobre")
	public String getSobre() {
		return "sobre";
	}
	
	@RequestMapping("/comofunciona")
	public String getComoFunciona() {
		return "comofunciona";
	}
	
	@GetMapping(value="/perfil", produces="application/json")
	public @ResponseBody ArrayList<Perfil> getPerfil() {
		ArrayList<Perfil> perfis = new ArrayList<>();
		List<Perfil> perfil = perfilService.getAll();
		
		for (Perfil perf : perfil) {
			perfis.add(perf);
		}
		return perfis;
	}
	
	@PostMapping(value="/teste")
	public String setPerfil(@RequestBody String nomePerfil) {
		Perfil perf = new Perfil();
		perf.setNomePerfil(nomePerfil);
		if(perfilService.setOne(perf)) {
			return "redirect:/";
		}		
		return "erro";
	}
	
	@GetMapping(value="perfil_nome", produces="application/json")
	public @ResponseBody Perfil getPerfilName() {
		return perfilService.getOneName("Cliente");
	}
			
	@GetMapping(value="/user", produces="application/json")
	public @ResponseBody ArrayList<Usuario> getUsers() {
		List<Usuario> users = service.getUsuarios();
		ArrayList<Usuario> usuarios = new ArrayList<>();
		for (Usuario usuario : users) {
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	@GetMapping(value="/user_one", produces="application/json")
	public @ResponseBody ArrayList<Perfil> getOneUsers(@RequestParam("id")long id) {
		
		ArrayList<Perfil> perfil = new ArrayList<>();
		perfil.add(perfilService.getOneId(id));
		
		return perfil;
	}
	
	@RequestMapping(value="user/{perfil}", method=RequestMethod.POST)
	public String setUsuario(@PathVariable("perfil") long id, Usuario u) {
		Usuario usuario = new Usuario();
		usuario.setNome(u.getNome());
		usuario.setCPF(u.getCPF());
		usuario.setDataNasc(u.getDataNasc());
		usuario.setEmail(u.getEmail());
		usuario.setSobreNome(u.getSobreNome());
		usuario.setSenha(u.getSenha());
		usuario.setPerfil(perfilService.getOneId(id));
		
		if(service.setOne(usuario)) {
			return "redirect:/users";
		}
		return "/";
		
	}

	
}
