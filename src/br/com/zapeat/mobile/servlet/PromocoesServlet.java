package br.com.zapeat.mobile.servlet;

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

import br.com.zapeat.mobile.dao.PromocaoDAO;
import br.com.zapeat.mobile.model.PromocaoModel;

@SuppressWarnings("serial")
@WebServlet("/promocoes")
public class PromocoesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}

	private void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject promo = null;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		NumberFormat formatNumber = NumberFormat.getCurrencyInstance(Locale.getDefault());
		for (PromocaoModel promocao : new PromocaoDAO().pesquisarDisponiveis()) {

			promo = new JSONObject();

			promo.put("descricao", promocao.getTitulo());

			promo.put("id", promocao.getId());

			promo.put("localidade", promocao.getFornecedorModel().getNomeFantasia());

			promo.put("latitude", promocao.getFornecedorModel().getLatitude());

			promo.put("longitude", promocao.getFornecedorModel().getLongitude());

			promo.put("dataFinal", format.format(promocao.getFim()));

			promo.put("dataInicial", format.format(promocao.getInicio()));

			promo.put("precoOriginal", formatNumber.format(promocao.getPrecoOriginal()));

			promo.put("precoPromocional", formatNumber.format(promocao.getPrecoPromocional()));

			array.put(promo);

		}

		object.put("promocoes", array);

		resp.setContentType("application/json");
		resp.getWriter().write(object.toString());
		resp.flushBuffer();

	}

}
