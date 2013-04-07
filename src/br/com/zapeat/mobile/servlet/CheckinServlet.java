package br.com.zapeat.mobile.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.file.TSFile;
import br.com.topsys.util.TSUtil;
import br.com.zapeat.mobile.dao.FornecedorCheckInDAO;
import br.com.zapeat.mobile.dao.UsuarioDAO;
import br.com.zapeat.mobile.model.FornecedorCheckinModel;
import br.com.zapeat.mobile.model.FornecedorModel;
import br.com.zapeat.mobile.model.UsuarioModel;
import br.com.zapeat.mobile.util.Constantes;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/checkin")
public class CheckinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckinServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String token = request.getParameter("usuario");

		String fornecedorId = request.getParameter("fornecedor_id");

		String texto = request.getParameter("texto");

		String postarFacebook = request.getParameter("postar_facebook");

		String temImagem = request.getParameter("tem_imagem");

		if (!TSUtil.isEmpty(token) && !TSUtil.isEmpty(fornecedorId)) {

			FornecedorCheckinModel fornecedorCheckinModel = new FornecedorCheckinModel();

			fornecedorCheckinModel.setFornecedorModel(new FornecedorModel(Long.valueOf(fornecedorId)));

			fornecedorCheckinModel.setUsuarioModel(new UsuarioDAO().obterPorToken(new UsuarioModel(token)));

			if (!TSUtil.isEmpty(fornecedorCheckinModel.getUsuarioModel())) {

				try {

					fornecedorCheckinModel.setTexto(texto);

					if (!TSUtil.isEmpty(temImagem)) {

						this.saveFile(request, fornecedorCheckinModel);

					}

					fornecedorCheckinModel = new FornecedorCheckInDAO().inserir(fornecedorCheckinModel);

					if (!TSUtil.isEmpty(postarFacebook)) {

						this.postarFacebook(fornecedorCheckinModel);

					}

				} catch (TSApplicationException e) {

					e.printStackTrace();

				}

			}

		}

	}

	private void saveFile(HttpServletRequest request, FornecedorCheckinModel model) {

		DiskFileItemFactory diskFileItem = new DiskFileItemFactory();

		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItem);

		List<FileItem> list = null;

		try {

			list = fileUpload.parseRequest(request);

			if (!TSUtil.isEmpty(list)) {

				for (FileItem file : list) {

					if ("file".equals(file.getFieldName())) {

						model.setImagem(file.getName());

						TSFile.inputStreamToFile(file.getInputStream(), Constantes.PASTA_UPLOAD + model.getImagem());

						break;

					}

				}

			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	private void postarFacebook(FornecedorCheckinModel model) {

	}

}
