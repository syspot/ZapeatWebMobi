package br.com.zapeat.mobile.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.topsys.util.TSUtil;
import br.com.zapeat.mobile.dao.CategoriaDAO;
import br.com.zapeat.mobile.model.CategoriaModel;
import br.com.zapeat.mobile.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean
public class MenuFaces extends LocationServiceFaces {

	private String nextStyleClass;

	private List<CategoriaModel> menus;

	public MenuFaces() {

		this.nextStyleClass = null;
		this.menus = new ArrayList<CategoriaModel>();

		for (CategoriaModel categoria : new CategoriaDAO().pesquisar()) {

			categoria.setStyleClass(getNextStyleClass());

			this.menus.add(categoria);

		}
	}

	public List<CategoriaModel> getMenus() {
		return menus;
	}

	public void setMenus(List<CategoriaModel> menus) {
		this.menus = menus;
	}

	private String getNextStyleClass() {

		if (TSUtil.isEmpty(this.nextStyleClass)) {
			this.nextStyleClass = Constantes.StyleClassMenu.BLOCK_A;
		} else {

			if (Constantes.StyleClassMenu.BLOCK_A.equals(this.nextStyleClass)) {
				this.nextStyleClass = Constantes.StyleClassMenu.BLOCK_B;
			} else if (Constantes.StyleClassMenu.BLOCK_B.equals(this.nextStyleClass)) {
				this.nextStyleClass = Constantes.StyleClassMenu.BLOCK_C;
			} else {
				this.nextStyleClass = Constantes.StyleClassMenu.BLOCK_A;
			}

		}

		return this.nextStyleClass;

	}
}
