package br.com.zapeat.site.faces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.topsys.util.TSUtil;
import br.com.zapeat.site.dao.CategoriaDAO;
import br.com.zapeat.site.model.CategoriaModel;
import br.com.zapeat.site.model.UsuarioModel;
import br.com.zapeat.site.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MenuFaces implements Serializable {

	private String nextStyleClass;

	private List<CategoriaModel> menus;

	@PostConstruct
	private void init() {
		this.nextStyleClass = null;
		UsuarioModel usuario = new UsuarioModel();
		usuario.setId(1L);
		this.menus = new ArrayList<CategoriaModel>();

		for (CategoriaModel categoria : new CategoriaDAO().pesquisar(usuario)) {

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
