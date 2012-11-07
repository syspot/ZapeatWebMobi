package br.com.zapeat.mobile.faces;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.mobile.dao.ComentarioDAO;
import br.com.zapeat.mobile.model.ComentarioModel;
import br.com.zapeat.mobile.util.Utilitarios;

@SuppressWarnings("serial")
@ManagedBean
public class ComentarioFaces extends DetalhamentoFaces {

	private String comentario;

	public ComentarioFaces() {

		this.comentario = TSFacesUtil.getRequestParameter("comentario");

		if (!TSUtil.isEmpty(comentario)) {

			this.comentar();

		}
	}

	public String comentar() {

		ComentarioModel comment = new ComentarioModel();

		comment.setUsuarioModel(Utilitarios.getUsuarioLogado());

		comment.setDescricao(this.comentario);

		comment.setDataCadastro(new Date());

		comment.setPromocaoModel(this.promocaoModel);

		try {

			new ComentarioDAO().inserir(comment);

		} catch (TSApplicationException ex) {

			TSFacesUtil.getRequest().setAttribute("msg", ex.getMessage());
		}

		this.redirecionar();

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

}
