package br.com.zapeat.mobile.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.topsys.util.TSUtil;
import br.com.zapeat.mobile.dao.CategoriaDAO;
import br.com.zapeat.mobile.model.CategoriaModel;
import br.com.zapeat.mobile.model.LocalizacaoModel;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/categorias")
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriaServlet() {
		super();
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject object = new JSONObject();

		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");

		LocalizacaoModel model = new LocalizacaoModel();

		if (!TSUtil.isEmpty(latitude)) {

			model.setLatitude(Double.valueOf(latitude));
			model.setLongitude(Double.valueOf(longitude));

		}

		JSONArray array = new JSONArray();
		JSONObject categoria = null;

		for (CategoriaModel item : new CategoriaDAO().pesquisar(model)) {

			if (item.getQuantidadePromocoes() != null && item.getQuantidadePromocoes().intValue() > 0) {

				categoria = new JSONObject();

				categoria.put("id", item.getId());

				categoria.put("descricao", item.getDescricao());

				categoria.put("quantidade", item.getQuantidadePromocoes());

				array.put(categoria);

			}

		}

		object.put("categorias", array);

		response.setContentType("application/json");
		response.getWriter().write(object.toString());
		response.flushBuffer();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.execute(request, response);
	}

}
