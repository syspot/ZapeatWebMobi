package br.com.zapeat.site.faces;

import java.io.Serializable;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.model.LocalizacaoModel;
import br.com.zapeat.site.util.Constantes;
import br.com.zapeat.site.util.Utilitarios;

@SuppressWarnings("serial")
public class LocationServiceFaces implements Serializable {

	public LocationServiceFaces() {
		String location = TSFacesUtil.getRequestParameter(Constantes.HttpParams.LOCATION);

		if (!TSUtil.isEmpty(location)) {
			LocalizacaoModel localizacao = new LocalizacaoModel(location);
			TSFacesUtil.addObjectInSession(Constantes.HttpParams.LOCALIZACAO, localizacao);
		}		
	}

	protected LocalizacaoModel getLocalizacaoAtual() {
		return (LocalizacaoModel) TSFacesUtil.getObjectInSession(Constantes.HttpParams.LOCALIZACAO);
	}	
	
	public boolean isLogado() {
		return !TSUtil.isEmpty(Utilitarios.getUsuarioLogado());
	}
}
