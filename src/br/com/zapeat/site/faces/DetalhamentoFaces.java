package br.com.zapeat.site.faces;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.dao.ComentarioDAO;
import br.com.zapeat.site.dao.PromocaoDAO;
import br.com.zapeat.site.model.ComentarioModel;
import br.com.zapeat.site.model.PromocaoModel;
import br.com.zapeat.site.util.Constantes;
import br.com.zapeat.site.util.Utilitarios;

@SuppressWarnings("serial")
@ManagedBean
public class DetalhamentoFaces extends LocationServiceFaces {

	private String categoriaId;
	private PromocaoModel promocaoModel;

	public DetalhamentoFaces() {
		this.categoriaId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.CATEGORIA_ID);

		String promocaoId = TSFacesUtil.getRequestParameter(Constantes.HttpParams.PROMOCAO_ID);

		if (TSUtil.isNumeric(promocaoId)) {
			this.promocaoModel = new PromocaoDAO().obter(new PromocaoModel(Long.valueOf(promocaoId)));
		}

		if (TSUtil.isEmpty(this.promocaoModel)) {
			this.promocaoModel = new PromocaoModel();
		}

		TSFacesUtil.getRequest().setAttribute(Constantes.HttpParams.CATEGORIA_ID, this.categoriaId);
		
	}

	public String indicar() {

		ComentarioModel comentario = new ComentarioModel();
		comentario.setUsuarioModel(Utilitarios.getUsuarioLogado());
		comentario.setDescricao(Constantes.TEXT_INDICACAO_MOBILE);
		comentario.setDataCadastro(new Date());
		comentario.setFlagIndicaPromocao(Boolean.TRUE);
		comentario.setPromocaoModel(this.promocaoModel);
		try {
			new ComentarioDAO().inserir(comentario);
		} catch (Exception ex) {
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

}
