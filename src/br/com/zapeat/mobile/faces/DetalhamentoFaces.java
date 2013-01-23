package br.com.zapeat.mobile.faces;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.mobile.dao.ComentarioDAO;
import br.com.zapeat.mobile.dao.IndicacaoDAO;
import br.com.zapeat.mobile.dao.PromocaoDAO;
import br.com.zapeat.mobile.model.FornecedorModel;
import br.com.zapeat.mobile.model.IndicacaoModel;
import br.com.zapeat.mobile.model.PromocaoModel;
import br.com.zapeat.mobile.util.Constantes;
import br.com.zapeat.mobile.util.Utilitarios;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DetalhamentoFaces extends LocationServiceFaces {

	protected String categoriaId;
	protected PromocaoModel promocaoModel;
	protected String filtro;

	public DetalhamentoFaces() {

		this.categoriaId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.CATEGORIA_ID);

		String promocaoId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.PROMOCAO_ID);

		this.filtro = TSFacesUtil.getRequestParameter(Constantes.HttpParams.FILTRO);

		if (TSUtil.isNumeric(promocaoId)) {

			this.promocaoModel = new PromocaoDAO().obter(new PromocaoModel(Long.valueOf(promocaoId)), Utilitarios.getUsuarioLogado());

			if (!TSUtil.isEmpty(this.promocaoModel)) {
				this.promocaoModel.setComentarios(new ComentarioDAO().pesquisar(this.promocaoModel));
			}

		}

		if (TSUtil.isEmpty(this.promocaoModel)) {

			this.promocaoModel = new PromocaoModel();
			this.promocaoModel.setFornecedorModel(new FornecedorModel());

		}

		TSFacesUtil.getRequest().setAttribute(Constantes.HttpParams.CATEGORIA_ID, this.categoriaId);

	}

	public String indicar() {

		IndicacaoModel comentario = new IndicacaoModel();
		comentario.setUsuarioModel(Utilitarios.getUsuarioLogado());
		comentario.setDataCadastro(new Date());
		comentario.setFornecedorModel(this.promocaoModel.getFornecedorModel());
		comentario.setFlagIndica(Boolean.TRUE);
		comentario.setFlagNaoIndica(Boolean.FALSE);

		try {
			new IndicacaoDAO().inserir(comentario);
			this.promocaoModel.setIndicada(Boolean.TRUE);
			this.promocaoModel.setNaoIndicada(Boolean.FALSE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public String getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}

	public PromocaoModel getPromocaoModel() {
		return promocaoModel;
	}

	public void setPromocaoModel(PromocaoModel promocaoModel) {
		this.promocaoModel = promocaoModel;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Integer getTamanhoComentarios() {
		if (TSUtil.isEmpty(this.promocaoModel) || TSUtil.isEmpty(this.promocaoModel.getComentarios())) {
			return 0;
		}
		return this.promocaoModel.getComentarios().size();
	}

	protected void removerPromocaoSessao() {
		TSFacesUtil.removeObjectInSession(Constantes.HttpParams.PROMOCAO_ID);

		TSFacesUtil.removeObjectInSession(Constantes.HttpParams.CATEGORIA_ID);

		TSFacesUtil.removeObjectInSession(Constantes.HttpParams.FILTRO);
	}

}
