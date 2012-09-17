package br.com.zapeat.site.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.topsys.util.TSUtil;
import br.com.zapeat.site.dao.CategoriaDAO;
import br.com.zapeat.site.model.CategoriaModel;
import br.com.zapeat.site.util.Constantes;
import br.com.zapeat.site.util.Utilitarios;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MenuFaces extends LocationServiceFaces  {

	private String nextStyleClass;

	private List<CategoriaModel> menus;

	
	public MenuFaces() {
		
		this.nextStyleClass = null;
		this.menus = new ArrayList<CategoriaModel>();
		
		for (CategoriaModel categoria : new CategoriaDAO().pesquisar(Utilitarios.getUsuarioLogado())) {

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
