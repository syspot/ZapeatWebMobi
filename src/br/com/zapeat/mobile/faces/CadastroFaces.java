package br.com.zapeat.mobile.faces;

import javax.faces.bean.ManagedBean;

import br.com.topsys.constant.TSConstant;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.mobile.dao.UsuarioDAO;
import br.com.zapeat.mobile.model.UsuarioModel;
import br.com.zapeat.mobile.util.Constantes;
import br.com.zapeat.mobile.util.EnviarEmail;

@SuppressWarnings("serial")
@ManagedBean
public class CadastroFaces extends DetalhamentoFaces {

	private UsuarioModel usuarioModel;

	public CadastroFaces() {

		this.usuarioModel = new UsuarioModel();

		String nome = TSFacesUtil.getRequestParameter(Constantes.HttpParams.NOME);

		String email = TSFacesUtil.getRequestParameter(Constantes.HttpParams.EMAIL);

		String senha = TSFacesUtil.getRequestParameter(Constantes.HttpParams.SENHA);

		if (!TSUtil.isEmpty(nome) && !TSUtil.isEmpty(email) && !TSUtil.isEmpty(senha)) {

			this.usuarioModel.setNome(nome);

			this.usuarioModel.setEmail(email);

			this.usuarioModel.setSenha(senha);

			try {

				this.inserir();

			} catch (TSApplicationException ex) {
				TSFacesUtil.getRequest().setAttribute("msg", ex.getMessage());
			}
		}

	}

	private void inserir() throws TSApplicationException {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		UsuarioModel model = usuarioDAO.obter(this.usuarioModel);

		if (TSUtil.isEmpty(model)) {

			this.usuarioModel.setSenha(TSCryptoUtil.gerarHash(this.usuarioModel.getSenha(), TSConstant.CRIPTOGRAFIA_MD5));

			this.usuarioModel.setFlagFacebook(Boolean.FALSE);

			this.usuarioModel.setFlagAceitouTermo(Boolean.TRUE);

			model = usuarioDAO.inserir(this.usuarioModel);

			model.setNome(TSStringUtil.formatarNomeProprio(model.getNome()));

			this.enviarEmail(model);

			this.usuarioModel = new UsuarioModel();

			TSFacesUtil.getRequest().setAttribute("msg", "Para efetivar seu cadastro é necessério acessar o e-mail informado e clicar no link de confirmação. Caso não receba o e-mail, mantenha contato conosco.");

		} else {

			TSFacesUtil.getRequest().setAttribute("msg", "Esse e-mail já existe.");

		}

	}

	private void enviarEmail(UsuarioModel model) {

		StringBuilder corpo = new StringBuilder();

		String marca = "http://saudelivre.com.br/zapeatsite/img/marca.png";

		corpo.append("<img src='" + marca + "'/>");

		corpo.append("<br><br><br>");

		corpo.append("Olá " + model.getNome());

		corpo.append("<br>");

		try {
		
		corpo.append("Para confirmar seu cadastro no site ZAPEAT clique <a href=\"")
			 .append(Constantes.URL_APLICACAO + "confirmacao.jsf?token=" + TSCryptoUtil.criptografar(model.getId().toString()))
			 .append("\"> aqui </a>");
			 
			 EnviarEmail.enviar(Constantes.ZAPEAT_GMAIL, model.getEmail(), "Zapeat - Confirmação de Cadastro", corpo.toString());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

}
