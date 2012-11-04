package br.com.zapeat.site.faces;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.dao.PromocaoDAO;
import br.com.zapeat.site.model.PromocaoModel;
import br.com.zapeat.site.util.Constantes;
import br.com.zapeat.site.util.Utilitarios;

@SuppressWarnings("serial")
@ManagedBean
public class ListagemGeralFaces extends LocationServiceFaces {

	private List<PromocaoModel> promocoes;

	private String filtro;

	public ListagemGeralFaces() {

		Integer distanciaMaxima = (Integer) TSFacesUtil.getObjectInSession(Constantes.HttpParams.FILTRO_DISTANCIA);

		if (!TSUtil.isEmpty(distanciaMaxima) && !TSUtil.isEmpty(super.getLocalizacaoAtual())) {

			super.getLocalizacaoAtual().setDistanciaMaxima(distanciaMaxima);

		}

		PromocaoModel promocao = new PromocaoModel();

		promocao.setDescricao(TSFacesUtil.getRequestParameter("filtro"));

		this.filtro = promocao.getDescricao();

		this.promocoes = new PromocaoDAO().pesquisar(promocao, super.getLocalizacaoAtual(), Utilitarios.getUsuarioLogado());

	}

	public List<PromocaoModel> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<PromocaoModel> promocoes) {
		this.promocoes = promocoes;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
