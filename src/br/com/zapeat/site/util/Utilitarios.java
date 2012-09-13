package br.com.zapeat.site.util;

import br.com.zapeat.site.model.UsuarioModel;

public class Utilitarios {
	
	private static final UsuarioModel model;
	
	private Utilitarios() {
	
	}
	
	static {
		model= new UsuarioModel();
		model.setId(1L);
	}
	
	public static UsuarioModel getUsuarioLogado() {
		return model;
		//return (UsuarioModel) TSFacesUtil.getObjectInSession(Constantes.USUARIO_LOGADO);
	}

}
