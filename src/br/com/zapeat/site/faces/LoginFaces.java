package br.com.zapeat.site.faces;

import javax.faces.bean.ManagedBean;

import br.com.topsys.constant.TSConstant;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.site.dao.UsuarioDAO;
import br.com.zapeat.site.model.UsuarioModel;
import br.com.zapeat.site.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean
public class LoginFaces extends DetalhamentoFaces {

	private UsuarioModel usuarioModel;
	
	public LoginFaces() {

		this.usuarioModel = new UsuarioModel();

		String login = TSFacesUtil.getRequestParameter("login");

		String senha = TSFacesUtil.getRequestParameter("senha");

		if (!TSUtil.isEmpty(login) && !TSUtil.isEmpty(senha)) {

			this.usuarioModel.setEmail(login);

			this.usuarioModel.setSenha(senha);

			this.logarIndicar();

		}

	}

	public String logarIndicar() {

		if (TSUtil.isEmpty(this.usuarioModel.getEmail()) || TSUtil.isEmpty(this.usuarioModel.getSenha())) {
			TSFacesUtil.getRequest().setAttribute("msg", "Preencha o login e senha corretamente!");
			return null;
		}

		this.usuarioModel.setSenha(TSCryptoUtil.gerarHash(this.usuarioModel.getSenha(), TSConstant.CRIPTOGRAFIA_MD5));

		UsuarioModel usuario = new UsuarioDAO().obterAcesso(this.usuarioModel);

		if (!TSUtil.isEmpty(usuario)) {

			TSFacesUtil.setManagedBeanInSession(Constantes.USUARIO_LOGADO, usuario);
			this.indicar();

			try {

				TSFacesUtil.getResponse().sendRedirect("detalhamento.xhtml?promocaoId=" + this.promocaoModel.getId() + "&categoriaId=" + this.categoriaId + "&filtro="+this.filtro);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} else {
			TSFacesUtil.getRequest().setAttribute("promocaoId", this.promocaoModel.getId());
			TSFacesUtil.getRequest().setAttribute("categoriaId",this.categoriaId);
			TSFacesUtil.getRequest().setAttribute("msg", "Login e/ou senha incorretos!");
		}

		return null;

	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

}
