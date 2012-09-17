package br.com.zapeat.site.faces;

import javax.faces.bean.ManagedBean;

import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.dao.CategoriaDAO;
import br.com.zapeat.site.dao.FornecedorDAO;
import br.com.zapeat.site.model.CategoriaModel;
import br.com.zapeat.site.model.FornecedorModel;
import br.com.zapeat.site.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean
public class EstabelecimentoFaces extends LocationServiceFaces {

	private FornecedorModel fornecedorModel;
	private CategoriaModel categoriaModel;
	private String promocaoId;

	public EstabelecimentoFaces() {

		this.fornecedorModel = new FornecedorModel();

		fornecedorModel.setId(Long.valueOf(TSFacesUtil.getRequestParameter(Constantes.HttpParams.FORNECEDOR_ID)));

		fornecedorModel = new FornecedorDAO().obter(fornecedorModel);

		if (fornecedorModel == null) {
			fornecedorModel = new FornecedorModel();
		}

		String categoriaId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.CATEGORIA_ID);

		this.categoriaModel = new CategoriaModel(Long.valueOf(categoriaId));

		this.categoriaModel = new CategoriaDAO().obter(this.categoriaModel);

		this.promocaoId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.PROMOCAO_ID);

	}

	public FornecedorModel getFornecedorModel() {
		return fornecedorModel;
	}

	public void setFornecedorModel(FornecedorModel fornecedorModel) {
		this.fornecedorModel = fornecedorModel;
	}

	public CategoriaModel getCategoriaModel() {
		return categoriaModel;
	}

	public void setCategoriaModel(CategoriaModel categoriaModel) {
		this.categoriaModel = categoriaModel;
	}

	public String getPromocaoId() {
		return promocaoId;
	}

	public void setPromocaoId(String promocaoId) {
		this.promocaoId = promocaoId;
	}

}
