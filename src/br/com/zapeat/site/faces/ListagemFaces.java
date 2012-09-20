package br.com.zapeat.site.faces;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.dao.PromocaoDAO;
import br.com.zapeat.site.model.CategoriaModel;
import br.com.zapeat.site.model.PromocaoModel;
import br.com.zapeat.site.util.Constantes;
import br.com.zapeat.site.util.Utilitarios;

@SuppressWarnings("serial")
@ManagedBean
public class ListagemFaces extends LocationServiceFaces {

	private List<PromocaoModel> promocoes;

	public ListagemFaces() {
		String categoriaId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.CATEGORIA_ID);

		if (TSUtil.isNumeric(categoriaId)) {
			CategoriaModel categoriaModel = new CategoriaModel(Long.valueOf(categoriaId));

			Integer distanciaMaxima = (Integer) TSFacesUtil.getObjectInSession(Constantes.HttpParams.FILTRO_DISTANCIA);

			if (!TSUtil.isEmpty(distanciaMaxima)) {

				categoriaModel.setDistanciaMaxima(distanciaMaxima);

			}

			this.promocoes = new PromocaoDAO().pesquisar(categoriaModel,super.getLocalizacaoAtual(),Utilitarios.getUsuarioLogado());
		}

		TSFacesUtil.getRequest().setAttribute(Constantes.HttpParams.CATEGORIA_ID, categoriaId);

	}

	public List<PromocaoModel> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<PromocaoModel> promocoes) {
		this.promocoes = promocoes;
	}

}
