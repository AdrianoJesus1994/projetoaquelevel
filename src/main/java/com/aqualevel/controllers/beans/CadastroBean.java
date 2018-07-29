package com.aqualevel.controllers.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aqualevel.model.Usuario;
import com.aqualevel.model.services.PerfilService;
import com.aqualevel.model.services.UsuarioService;

@Component
public class CadastroBean {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;

	
	public boolean cadastrarUsuario(Usuario user) {
		
		boolean retorno = true;
				
		if (verificaCpf(user)) {
			return false;
		}
		
		if (verificaEmail(user)) {
			return false;
		}
					
		user.setPerfil(getPerfilService().getOneName("Cliente"));
		retorno = getUsuarioService().setOne(user);
			
		return retorno;
	}
	
	public boolean verificaCpf(Usuario user) {
		List<Usuario> users = new ArrayList<Usuario>();
		users = getUsuarioService().getUsuarios();
		
		for (Usuario usuario : users) {
			if (usuario.getCPF().equals(user.getCPF())) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean verificaEmail(Usuario user) {
		List<Usuario> users = new ArrayList<Usuario>();
		users = getUsuarioService().getUsuarios();	
		
		for(Usuario usuario : users) {
			if (usuario.getEmail().equals(user.getEmail())) {
				return true;
			}
		}
		
		return false;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public PerfilService getPerfilService() {
		return perfilService;
	}

	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}
	
	
	
	
	

}
