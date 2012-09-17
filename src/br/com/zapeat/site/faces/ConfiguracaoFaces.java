package br.com.zapeat.site.faces;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean
public class ConfiguracaoFaces implements Serializable {

	private Integer filtroDistancia;
	private String categoriaId;

	public ConfiguracaoFaces() {

		this.filtroDistancia = (Integer) TSFacesUtil.getObjectInSession(Constantes.HttpParams.FILTRO_DISTANCIA);

		String filtro = TSFacesUtil.getRequestParameter(Constantes.HttpParams.FILTRO_DISTANCIA);

		if (!TSUtil.isEmpty(filtro)) {
			this.filtroDistancia = Integer.valueOf(filtro);
			if(!TSUtil.isEmpty(this.filtroDistancia) && Integer.valueOf(0).compareTo(this.filtroDistancia)==0) {
				this.filtroDistancia=null;
			}
			TSFacesUtil.addObjectInSession(Constantes.HttpParams.FILTRO_DISTANCIA, this.filtroDistancia);
		}

		this.categoriaId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.CATEGORIA_ID);

	}

	public String getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Integer getFiltroDistancia() {
		return filtroDistancia;
	}

	public void setFiltroDistancia(Integer filtroDistancia) {
		this.filtroDistancia = filtroDistancia;
	}

}
