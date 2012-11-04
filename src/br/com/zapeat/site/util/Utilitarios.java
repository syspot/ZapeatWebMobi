package br.com.zapeat.site.util;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.model.UsuarioModel;

public final class Utilitarios {

	private Utilitarios() {

	}

	public static UsuarioModel getUsuarioLogado() {
		return (UsuarioModel) TSFacesUtil.getObjectInSession(Constantes.USUARIO_LOGADO);
	}
	
	public static Long tratarLong(Long valor) {

		if (!TSUtil.isEmpty(valor) && valor.equals(0L)) {

			valor = null;

		}

		return valor;

	}


}
