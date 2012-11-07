package br.com.zapeat.mobile.faces;

import javax.faces.bean.ManagedBean;

import br.com.topsys.constant.TSConstant;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.mobile.dao.UsuarioDAO;
import br.com.zapeat.mobile.model.UsuarioModel;
import br.com.zapeat.mobile.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean
public class LoginFaces extends DetalhamentoFaces {

	private UsuarioModel usuarioModel;

	public LoginFaces() {

		this.usuarioModel = new UsuarioModel();

		String login = TSFacesUtil.getRequestParameter(Constantes.HttpParams.LOGIN);

		String senha = TSFacesUtil.getRequestParameter(Constantes.HttpParams.SENHA);

		if (!TSUtil.isEmpty(login) && !TSUtil.isEmpty(senha)) {

			this.usuarioModel.setEmail(login);

			this.usuarioModel.setSenha(senha);

			this.logar();

		}
	}

	public String logar() {

		this.usuarioModel.setSenha(TSCryptoUtil.gerarHash(this.usuarioModel.getSenha(), TSConstant.CRIPTOGRAFIA_MD5));

		UsuarioModel usuario = new UsuarioDAO().obterAcesso(this.usuarioModel);

		if (!TSUtil.isEmpty(usuario)) {

			if (TSUtil.isEmpty(usuario.getFlagAtivo()) || !usuario.getFlagAtivo()) {
				
				TSFacesUtil.getRequest().setAttribute("msg", "O usuário está inativo ou não confirmou cadastro até o momento!");

			} else {

				TSFacesUtil.setManagedBeanInSession(Constantes.USUARIO_LOGADO, usuario);

				this.redirecionar();

			}

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

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

}
