package br.com.zapeat.mobile.faces;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.mobile.dao.PromocaoDAO;
import br.com.zapeat.mobile.model.CategoriaModel;
import br.com.zapeat.mobile.model.PromocaoModel;
import br.com.zapeat.mobile.util.Constantes;
import br.com.zapeat.mobile.util.Utilitarios;

@SuppressWarnings("serial")
@ManagedBean
public class ListagemFaces extends LocationServiceFaces {

	private List<PromocaoModel> promocoes;
	private String filtro;
	private int abaSelecionada;

	public ListagemFaces() {
		String categoriaId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.CATEGORIA_ID);

		if (TSUtil.isNumeric(categoriaId)) {

			CategoriaModel categoriaModel = new CategoriaModel(Long.valueOf(categoriaId));

			Integer distanciaMaxima = (Integer) TSFacesUtil.getObjectInSession(Constantes.HttpParams.FILTRO_DISTANCIA);

			if (!TSUtil.isEmpty(distanciaMaxima) && !TSUtil.isEmpty(super.getLocalizacaoAtual())) {

				super.getLocalizacaoAtual().setDistanciaMaxima(distanciaMaxima);

			}

			PromocaoModel promocao = new PromocaoModel();

			promocao.setCategoriaModel(categoriaModel);

			this.filtro = TSFacesUtil.getRequestParameter(Constantes.HttpParams.FILTRO);

			if (!TSUtil.isEmpty(this.filtro)) {

				promocao.setDescricao(this.filtro);

			}

			this.promocoes = new PromocaoDAO().pesquisar(promocao, super.getLocalizacaoAtual(), Utilitarios.getUsuarioLogado());

			this.abaSelecionada = 0;

			for (PromocaoModel promo : this.promocoes) {

				if (Constantes.PROMOCAO_DA_HORA.equals(promo.getTipoPromocaoModel().getId())) {

					this.abaSelecionada = 1;
					break;

				} else if (Constantes.PROMOCAO_DO_DIA.equals(promo.getTipoPromocaoModel().getId())) {

					this.abaSelecionada = 2;

				} else {

					this.abaSelecionada = 3;

				}

			}
		}

		TSFacesUtil.getRequest().setAttribute(Constantes.HttpParams.CATEGORIA_ID, categoriaId);

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

	public int getAbaSelecionada() {
		return abaSelecionada;
	}

	public void setAbaSelecionada(int abaSelecionada) {
		this.abaSelecionada = abaSelecionada;
	}

}
