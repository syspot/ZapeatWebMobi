package br.com.zapeat.site.servlet;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.topsys.util.TSUtil;
import br.com.zapeat.site.dao.PromocaoDAO;
import br.com.zapeat.site.dao.UsuarioDAO;
import br.com.zapeat.site.model.PromocaoModel;
import br.com.zapeat.site.model.UsuarioModel;
import br.com.zapeat.site.util.Constantes;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/autenticarIOS")
public class AuthenticationIOSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static class Promocao {

		Long id;
		String descricao;

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthenticationIOSServlet() {
		super();
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject object = new JSONObject();

		String email = request.getParameter(Constantes.HttpParams.EMAIL);
		
		UsuarioModel usuario = new UsuarioModel(email, null);

		usuario = new UsuarioDAO().obterPorEmail(usuario);

		if (!TSUtil.isEmpty(usuario)) {

			object.put("logged", true);
			
			object.put("key", usuario.getSenha());

			object.put("id", usuario.getId());
			object.put("nome", usuario.getNome());
			object.put("token", usuario.getToken());

			JSONArray array = new JSONArray();
			JSONObject promo = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			NumberFormat formatNumber = NumberFormat.getCurrencyInstance(Locale.getDefault());
			for (PromocaoModel promocao : new PromocaoDAO().pesquisarDisponiveis(usuario)) {

				promo = new JSONObject();

				promo.put("descricao", promocao.getTitulo());

				promo.put("id", promocao.getId());

				promo.put("localidade", promocao.getFornecedorModel().getNomeFantasiaSemApostrofe());

				promo.put("latitude", promocao.getFornecedorModel().getLatitude());

				promo.put("longitude", promocao.getFornecedorModel().getLongitude());

				promo.put("dataFinal", format.format(promocao.getFim()));

				promo.put("dataInicial", format.format(promocao.getInicio()));

				promo.put("precoOriginal", formatNumber.format(promocao.getPrecoOriginal()));

				promo.put("precoPromocional", formatNumber.format(promocao.getPrecoPromocional()));

				array.put(promo);

			}


			object.put("promocoes", array);

		} else {
			object.put("logged", false);
		}

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
