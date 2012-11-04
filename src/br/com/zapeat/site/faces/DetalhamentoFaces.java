package br.com.zapeat.site.faces;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.dao.FornecedorDAO;
import br.com.zapeat.site.dao.IndicacaoDAO;
import br.com.zapeat.site.dao.PromocaoDAO;
import br.com.zapeat.site.model.FornecedorModel;
import br.com.zapeat.site.model.IndicacaoModel;
import br.com.zapeat.site.model.PromocaoModel;
import br.com.zapeat.site.util.Constantes;
import br.com.zapeat.site.util.Utilitarios;

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
		}

		if (TSUtil.isEmpty(this.promocaoModel)) {

			this.promocaoModel = new PromocaoModel();
			this.promocaoModel.setFornecedorModel(new FornecedorModel());

		} else {
			this.promocaoModel.setFornecedorModel(new FornecedorDAO().obter(this.promocaoModel.getFornecedorModel(), Utilitarios.getUsuarioLogado()));
		}

		TSFacesUtil.getRequest().setAttribute(Constantes.HttpParams.CATEGORIA_ID, this.categoriaId);

	}

	public String indicar() {

		IndicacaoModel comentario = new IndicacaoModel();
		comentario.setUsuarioModel(Utilitarios.getUsuarioLogado());
		comentario.setDataCadastro(new Date());
		comentario.setFornecedorModel(this.promocaoModel.getFornecedorModel());
		comentario.setFlagIndica(Boolean.TRUE);

		try {
			new IndicacaoDAO().inserir(comentario);
			this.promocaoModel.setIndicada(Boolean.TRUE);
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

}
