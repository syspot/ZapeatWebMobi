package br.com.zapeat.site.faces;

import javax.faces.bean.ManagedBean;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.dao.FornecedorDAO;
import br.com.zapeat.site.model.FornecedorModel;
import br.com.zapeat.site.util.Constantes;
import br.com.zapeat.site.util.Utilitarios;

@SuppressWarnings("serial")
@ManagedBean
public class MapaFaces  extends LocationServiceFaces  {

	private FornecedorModel fornecedorModel;
	private String categoriaId;
	private String promocaoId;

	public MapaFaces() {

		String id = TSFacesUtil.getRequestParameter(Constantes.HttpParams.FORNECEDOR_ID);

		if (TSUtil.isNumeric(id)) {
			FornecedorModel model = new FornecedorModel(Long.valueOf(id));

			this.fornecedorModel = new FornecedorDAO().obter(model,Utilitarios.getUsuarioLogado());

		}

		if (TSUtil.isEmpty(this.fornecedorModel)) {
			this.fornecedorModel = new FornecedorModel();
		}

		this.categoriaId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.CATEGORIA_ID);
		this.promocaoId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.PROMOCAO_ID);
	}

	public FornecedorModel getFornecedorModel() {
		return fornecedorModel;
	}

	public void setFornecedorModel(FornecedorModel fornecedorModel) {
		this.fornecedorModel = fornecedorModel;
	}

	public String getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getPromocaoId() {
		return promocaoId;
	}

	public void setPromocaoId(String promocaoId) {
		this.promocaoId = promocaoId;
	}

}
