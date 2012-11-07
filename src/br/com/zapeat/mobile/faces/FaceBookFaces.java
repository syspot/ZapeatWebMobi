package br.com.zapeat.mobile.faces;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.bean.ManagedBean;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.exception.TSSystemException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.mobile.dao.UsuarioDAO;
import br.com.zapeat.mobile.model.UsuarioModel;
import br.com.zapeat.mobile.util.Constantes;
import br.com.zapeat.mobile.util.FacebookClient;
import br.com.zapeat.mobile.util.UsuarioService;

@SuppressWarnings("serial")
@ManagedBean(name = "faceBookFaces")
public class FaceBookFaces extends DetalhamentoFaces {

	private String url;

	private String logout;

	public FaceBookFaces() {

		this.url = FacebookClient.getLoginRedirectURL();

		this.logout = FacebookClient.getLogoutUrl();

		String code = TSFacesUtil.getRequestParameter("code");

		String erro = TSFacesUtil.getRequestParameter("error");

		if (!TSUtil.isEmpty(code)) {

			String authURL = FacebookClient.getAuthURL(code);

			URL url = null;
			try {
				url = new URL(authURL);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}

			try {

				String result = readURL(url);

				String accessToken = null;

				Integer expires = null;

				String[] pairs = result.split("&");

				for (String pair : pairs) {

					String[] kv = pair.split("=");

					if (kv.length != 2) {

						redirecionar();

					} else {

						if (kv[0].equals("access_token")) {
							accessToken = kv[1];
						}

						if (kv[0].equals("expires")) {
							expires = Integer.valueOf(kv[1]);
						}
					}
				}

				if (accessToken != null && expires != null) {

					UsuarioService us = new UsuarioService();

					UsuarioModel model = us.authFacebookLogin(accessToken, expires);

					if (!TSUtil.isEmpty(model) && !TSUtil.isEmpty(model.getId())) {

						UsuarioModel usuario = new UsuarioDAO().obter(model);

						this.manterUsuario(usuario, model);

						if (!TSUtil.isEmpty(usuario.getFlagAceitouTermo()) && usuario.getFlagAceitouTermo()) {

							TSFacesUtil.setManagedBeanInSession(Constantes.USUARIO_LOGADO, usuario);

							redirecionar();

						} else {

							TSFacesUtil.setManagedBeanInSession(Constantes.ID_USUARIO_TEMO_USO, usuario.getId());

							redirecionarTermoUso();
						}

					} else {

						redirecionar();
					}

				} else {

					redirecionar();
				}

			} catch (IOException e) {

				throw new TSSystemException(e);
			}

		} else if (!TSUtil.isEmpty(TSUtil.tratarString(erro))) {

			redirecionar();
		}

	}

	private void manterUsuario(UsuarioModel usuario, UsuarioModel model) {

		if (TSUtil.isEmpty(usuario)) {

			try {

				model.setFlagAtivo(Boolean.TRUE);

				model.setFlagFacebook(Boolean.TRUE);

				usuario = new UsuarioDAO().inserir(model);

			} catch (TSApplicationException e) {

				e.printStackTrace();
			}

		} else {

			try {

				usuario.setNome(model.getNome());

				new UsuarioDAO().alterar(usuario);

			} catch (TSApplicationException e) {

				e.printStackTrace();
			}
		}
	}

	private void redirecionar() {

		try {

			TSFacesUtil.getResponse().sendRedirect("menu.xhtml");

		} catch (Exception ex) {

			TSFacesUtil.getRequest().setAttribute("msg", "Ocorreu um erro no redirecionamento");

			ex.printStackTrace();

		}

	}

	private void redirecionarTermoUso() {

		try {

			TSFacesUtil.getResponse().sendRedirect("termoUsoAceitar.xhtml");

		} catch (Exception ex) {

			TSFacesUtil.getRequest().setAttribute("msg", "Ocorreu um erro no redirecionamento");

			ex.printStackTrace();

		}

	}

	private String readURL(URL url) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		InputStream is = url.openStream();

		int r;

		while ((r = is.read()) != -1) {

			baos.write(r);
		}

		return new String(baos.toByteArray());
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogout() {
		return logout;
	}

	public void setLogout(String logout) {
		this.logout = logout;
	}

}
