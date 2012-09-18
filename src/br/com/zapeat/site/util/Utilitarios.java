package br.com.zapeat.site.util;

import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.model.UsuarioModel;

public class Utilitarios {

	private Utilitarios() {

	}

	public static UsuarioModel getUsuarioLogado() {
		return (UsuarioModel) TSFacesUtil.getObjectInSession(Constantes.USUARIO_LOGADO);
	}

}
