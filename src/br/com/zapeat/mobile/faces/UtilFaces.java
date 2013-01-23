package br.com.zapeat.mobile.faces;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.mobile.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean(name="utilFaces")
@ViewScoped
public class UtilFaces implements Serializable {
	
	public boolean isFirstTime() {
		return TSUtil.isEmpty(TSFacesUtil.getObjectInSession(Constantes.HttpParams.LOCALIZACAO));
	}

}
