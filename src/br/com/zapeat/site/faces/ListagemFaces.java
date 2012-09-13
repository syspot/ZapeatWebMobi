package br.com.zapeat.site.faces;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.dao.PromocaoDAO;
import br.com.zapeat.site.model.CategoriaModel;
import br.com.zapeat.site.model.PromocaoModel;
import br.com.zapeat.site.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean
public class ListagemFaces implements Serializable {

	private List<PromocaoModel> promocoes;

	public ListagemFaces() {
		String categoriaId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.CATEGORIA_ID);

		if (TSUtil.isNumeric(categoriaId)) {
			this.promocoes = new PromocaoDAO().pesquisar(new CategoriaModel(Long.valueOf(categoriaId)));
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
