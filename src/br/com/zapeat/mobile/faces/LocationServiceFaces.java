package br.com.zapeat.mobile.faces;

import java.io.Serializable;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.mobile.dao.UsuarioDAO;
import br.com.zapeat.mobile.model.LocalizacaoModel;
import br.com.zapeat.mobile.model.UsuarioModel;
import br.com.zapeat.mobile.util.Constantes;
import br.com.zapeat.mobile.util.Utilitarios;

@SuppressWarnings("serial")
public class LocationServiceFaces implements Serializable {

	public LocationServiceFaces() {
		String location = TSFacesUtil.getRequestParameter(Constantes.HttpParams.LOCATION);

		if (!TSUtil.isEmpty(location)) {
			LocalizacaoModel localizacao = new LocalizacaoModel(location);
			TSFacesUtil.addObjectInSession(Constantes.HttpParams.LOCALIZACAO, localizacao);
		}

		if (!isLogado()) {

			String token = TSFacesUtil.getRequestParameter("token");

			if (!TSUtil.isEmpty(token)) {

				UsuarioModel model = new UsuarioModel(token);

				model = new UsuarioDAO().obterPorToken(model);

				if (!TSUtil.isEmpty(model)) {

					TSFacesUtil.setManagedBeanInSession(Constantes.USUARIO_LOGADO, model);

				}

			}

		}

	}

	protected LocalizacaoModel getLocalizacaoAtual() {
		return (LocalizacaoModel) TSFacesUtil.getObjectInSession(Constantes.HttpParams.LOCALIZACAO);
	}

	public boolean isLogado() {
		return !TSUtil.isEmpty(Utilitarios.getUsuarioLogado());
	}

	public UsuarioModel getUsuarioLogado() {
		return Utilitarios.getUsuarioLogado();
	}
}
