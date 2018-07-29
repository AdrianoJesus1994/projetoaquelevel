package com.aqualevel.controllers.beans;

import org.springframework.stereotype.Component;

import com.aqualevel.model.Usuario;

@Component
public class ApplicationUtil {
	
	private static ApplicationUtil instancia = null;
	
	Usuario usuarioLogado;
	
	public static ApplicationUtil getInstancia() {
        if (instancia == null) {
            instancia = new ApplicationUtil();
        }
        return instancia;
    }

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public void setUsuarioVazio() {
        this.usuarioLogado = null;
    }
	
}
