package br.com.zapeat.mobile.faces;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.mobile.model.LocalizacaoModel;
import br.com.zapeat.mobile.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean
public class SairFaces implements Serializable {

	public SairFaces() {

		LocalizacaoModel localizacao = (LocalizacaoModel) TSFacesUtil.getObjectInSession(Constantes.HttpParams.LOCALIZACAO);

		TSFacesUtil.getRequest().getSession().invalidate();

		TSFacesUtil.getRequest().getSession(true);

		try {
			String caminho = "menu.xhtml";
			if (!TSUtil.isEmpty(localizacao)) {
				caminho=caminho+"?location=("+localizacao.getLatitude()+","+localizacao.getLongitude()+")";
			}
				TSFacesUtil.getResponse().sendRedirect(caminho);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
