package br.com.zapeat.mobile.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.mobile.dao.CategoriaDAO;
import br.com.zapeat.mobile.model.CategoriaModel;
import br.com.zapeat.mobile.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean
public class MenuFaces extends LocationServiceFaces {

	private List<CategoriaModel> menus;

	public MenuFaces() {

		if (!TSUtil.isEmpty(TSFacesUtil.getObjectInSession(Constantes.HttpParams.PROMOCAO_ID))) {

			redirecionarPromocao();

		} else {

			this.menus = new ArrayList<CategoriaModel>();

			for (CategoriaModel categoria : new CategoriaDAO().pesquisar(super.getLocalizacaoAtual())) {

				if (!TSUtil.isEmpty(categoria.getQuantidadePromocoes()) && Long.valueOf(0).compareTo(categoria.getQuantidadePromocoes()) < 0) {

					this.menus.add(categoria);

				}

			}

		}
	}

	private void redirecionarPromocao() {
		String promocaoId = (String) TSFacesUtil.getObjectInSession(Constantes.HttpParams.PROMOCAO_ID);

		String categoriaId = (String) TSFacesUtil.getObjectInSession(Constantes.HttpParams.CATEGORIA_ID);

		String filtro = (String) TSFacesUtil.getObjectInSession(Constantes.HttpParams.FILTRO);

		try {

			TSFacesUtil.getResponse().sendRedirect("detalhamento.xhtml?promocaoId=" + promocaoId + "&categoriaId=" + categoriaId + "&filtro=" + filtro);

			TSFacesUtil.removeObjectInSession(Constantes.HttpParams.PROMOCAO_ID);

			TSFacesUtil.removeObjectInSession(Constantes.HttpParams.CATEGORIA_ID);

			TSFacesUtil.removeObjectInSession(Constantes.HttpParams.FILTRO);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public List<CategoriaModel> getMenus() {
		return menus;
	}

	public void setMenus(List<CategoriaModel> menus) {
		this.menus = menus;
	}

}
