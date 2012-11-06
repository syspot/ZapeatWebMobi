package br.com.zapeat.site.faces;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import br.com.topsys.constant.TSConstant;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.dao.IndicacaoDAO;
import br.com.zapeat.site.dao.UsuarioDAO;
import br.com.zapeat.site.model.IndicacaoModel;
import br.com.zapeat.site.model.UsuarioModel;
import br.com.zapeat.site.util.Constantes;
import br.com.zapeat.site.util.Utilitarios;

@SuppressWarnings("serial")
@ManagedBean
public class IndicacaoFaces extends DetalhamentoFaces {

	private UsuarioModel usuarioModel;
	private boolean naoIndica;

	public IndicacaoFaces() {

		this.usuarioModel = new UsuarioModel();

		String login = TSFacesUtil.getRequestParameter(Constantes.HttpParams.LOGIN);

		String senha = TSFacesUtil.getRequestParameter(Constantes.HttpParams.SENHA);

		Object paramNaoIndica = TSFacesUtil.getRequestParameter(Constantes.HttpParams.NAO_INDICA);

		if (!TSUtil.isEmpty(paramNaoIndica)) {

			this.naoIndica = new Boolean(paramNaoIndica.toString());

		} else {

			this.naoIndica = false;

		}

		if (!TSUtil.isEmpty(login) && !TSUtil.isEmpty(senha)) {

			this.usuarioModel.setEmail(login);

			this.usuarioModel.setSenha(senha);

			this.logarIndicar();

		} else if (!TSUtil.isEmpty(TSFacesUtil.getRequestParameter("submitLogado")) 
				|| !TSUtil.isEmpty(TSFacesUtil.getRequestParameter("indica"))) {

			this.indicar();

		}

	}

	public String logarIndicar() {

		this.usuarioModel.setSenha(TSCryptoUtil.gerarHash(this.usuarioModel.getSenha(), TSConstant.CRIPTOGRAFIA_MD5));

		UsuarioModel usuario = new UsuarioDAO().obterAcesso(this.usuarioModel);

		if (!TSUtil.isEmpty(usuario)) {

			TSFacesUtil.setManagedBeanInSession(Constantes.USUARIO_LOGADO, usuario);

			this.indicar();

		} else {
			TSFacesUtil.getRequest().setAttribute(Constantes.HttpParams.PROMOCAO_ID, this.promocaoModel.getId());
			TSFacesUtil.getRequest().setAttribute(Constantes.HttpParams.CATEGORIA_ID, this.categoriaId);
			TSFacesUtil.getRequest().setAttribute("msg", "Login e/ou senha incorretos!");
		}

		return null;

	}

	private void redirecionar() {

		try {

			TSFacesUtil.getResponse().sendRedirect("detalhamento.xhtml?promocaoId=" + this.promocaoModel.getId() + "&categoriaId=" + this.categoriaId + "&filtro=" + this.filtro);

		} catch (Exception ex) {

			TSFacesUtil.getRequest().setAttribute("msg", "Ocorreu um erro no redirecionamento");

			ex.printStackTrace();

		}

	}

	public String indicar() {

		IndicacaoModel comentario = new IndicacaoModel();

		comentario.setUsuarioModel(Utilitarios.getUsuarioLogado());

		comentario.setDataCadastro(new Date());

		comentario.setFornecedorModel(this.promocaoModel.getFornecedorModel());

		comentario.setFlagIndica(!this.naoIndica);

		comentario.setFlagNaoIndica(this.naoIndica);

		comentario.setDescricao(TSFacesUtil.getRequestParameter("comentario"));

		try {

			IndicacaoDAO indicacaoDAO = new IndicacaoDAO();

			IndicacaoModel anterior = indicacaoDAO.obter(comentario);

			if (TSUtil.isEmpty(anterior)) {

				indicacaoDAO.inserir(comentario);

			} else {

				if (anterior.getFlagIndica()) {

					TSFacesUtil.getRequest().setAttribute("msg", "Você já indicou isto.");

				} else {

					TSFacesUtil.getRequest().setAttribute("msg", "Você já não indicou isto.");

				}

			}

		} catch (TSApplicationException ex) {

			TSFacesUtil.getRequest().setAttribute("msg", ex.getMessage());

		}

		this.redirecionar();

		return null;

	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public boolean isNaoIndica() {
		return naoIndica;
	}

	public void setNaoIndica(boolean naoIndica) {
		this.naoIndica = naoIndica;
	}

}
