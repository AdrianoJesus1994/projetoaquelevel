package com.aqualevel.controllers.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aqualevel.model.Usuario;
import com.aqualevel.model.services.UsuarioService;

@Component
public class LoginBean {
	
	@Autowired
	private UsuarioService usuarioService;
	
	

	public boolean validateUser(Usuario user) {
		
		List<Usuario> users = new ArrayList<Usuario>();
		users = getUsuarioService().getUsuarios();
		
		for(Usuario usuario : users) {
			if (usuario.getEmail().equals(user.getEmail()) && usuario.getSenha().equals(user.getSenha())) {
				ApplicationUtil.getInstancia().setUsuarioLogado(usuario);
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
	

}
