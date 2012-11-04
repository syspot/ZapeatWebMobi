package br.com.zapeat.site.util;

public class Constantes {

	private Constantes() {
	}

	public static final String TEXT_INDICACAO_MOBILE = "Promoção indicada atráves do Zapeat Mobile";

	public interface HttpParams {
		String CATEGORIA_ID = "categoriaId";
		String PROMOCAO_ID = "promocaoId";
		String FORNECEDOR_ID = "fornecedorId";
		String LATITUDE = "latitude";
		String LONGITUDE = "longitude";
		String LOCATION = "location";
		String FILTRO = "filtro";
		String FILTRO_DISTANCIA = "filtroDistancia";
		String LOCALIZACAO = "localizacao";
		String USUARIO_ID= "usuarioId";
		String EMAIL= "email";
		String SENHA = "senha";
		String LOGIN = "login";
		String NAO_INDICA="naoIndica";
	}

	public interface Sequences {
		String INDICACOES = "indicacoes_id_seq";
	}

	public static final String USUARIO_LOGADO = "usuarioLogado";

	public static final String PASTA_DOWNLOAD = "";
	public static final String PREFIXO_IMAGEM_FORNECEDOR_THUMB = "";

	public interface StyleClassMenu {

		String BLOCK_A = "ui-block-a";
		String BLOCK_B = "ui-block-b";
		String BLOCK_C = "ui-block-c";

	}
}
