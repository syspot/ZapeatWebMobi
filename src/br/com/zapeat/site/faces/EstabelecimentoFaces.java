package br.com.zapeat.site.faces;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.dao.CategoriaDAO;
import br.com.zapeat.site.dao.ComentarioDAO;
import br.com.zapeat.site.dao.FornecedorDAO;
import br.com.zapeat.site.dao.UsuarioDAO;
import br.com.zapeat.site.model.CategoriaModel;
import br.com.zapeat.site.model.ComentarioModel;
import br.com.zapeat.site.model.FornecedorModel;
import br.com.zapeat.site.util.Constantes;
import br.com.zapeat.site.util.Utilitarios;

@SuppressWarnings("serial")
@ManagedBean
public class EstabelecimentoFaces extends LocationServiceFaces {

	private FornecedorModel fornecedorModel;
	private CategoriaModel categoriaModel;
	private String promocaoId;

	public EstabelecimentoFaces() {

		this.fornecedorModel = new FornecedorModel();

		fornecedorModel.setId(Long.valueOf(TSFacesUtil.getRequestParameter(Constantes.HttpParams.FORNECEDOR_ID)));

		fornecedorModel = new FornecedorDAO().obter(fornecedorModel,Utilitarios.getUsuarioLogado());

		if (fornecedorModel == null) {
			fornecedorModel = new FornecedorModel();
		}

		String categoriaId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.CATEGORIA_ID);

		this.categoriaModel = new CategoriaModel(Long.valueOf(categoriaId));

		this.categoriaModel = new CategoriaDAO().obter(this.categoriaModel);

		this.promocaoId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.PROMOCAO_ID);

	}
	
	public String indicar() {

		ComentarioModel comentario = new ComentarioModel();
		comentario.setUsuarioModel(Utilitarios.getUsuarioLogado());
		comentario.setDescricao(Constantes.TEXT_INDICACAO_MOBILE);
		comentario.setDataCadastro(new Date());
		comentario.setFlagIndicaPromocao(Boolean.TRUE);
		comentario.setFlagIndicaAtendimento(Boolean.TRUE);
		comentario.setFornecedorModel(this.fornecedorModel);
		comentario.setUsuarioModel(new UsuarioDAO().obterPorToken(comentario.getUsuarioModel()));
		try {
			new ComentarioDAO().inserir(comentario);
			this.fornecedorModel.setIndicado(Boolean.TRUE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

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
