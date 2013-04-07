package br.com.zapeat.mobile.util;

import br.com.topsys.web.util.TSFacesUtil;

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
		String LOCALIZACAO_ANTERIOR = "localizacaoAnterior";
		String USUARIO_ID= "usuarioId";
		String EMAIL= "email";
		String SENHA = "senha";
		String LOGIN = "login";
		String NOME = "nome";
		String NAO_INDICA="naoIndica";
	}

	public interface Sequences {
		String INDICACOES = "indicacoes_id_seq";
	}

	public static final String USUARIO_LOGADO = "usuarioLogado";
	public static final String ID_USUARIO_TEMO_USO = "idUsuarioTermoUso";

	public interface StyleClassMenu {

		String BLOCK_A = "ui-block-a";
		String BLOCK_B = "ui-block-b";
		String BLOCK_C = "ui-block-c";

	}
	
	public static final Long PROMOCAO_DA_HORA=1L;
	public static final Long PROMOCAO_DO_DIA=2L;
	public static final Long PROMOCAO_DA_SEMANA=3L;
	
	public static final String PASTA_DOWNLOAD_TEMP = "http://"+TSFacesUtil.getRequest().getServerName() + ":" + TSFacesUtil.getRequest().getServerPort() + "/img_zapeat/";
	public static final String PASTA_DOWNLOAD = "http://"+TSFacesUtil.getRequest().getServerName() + ":" + TSFacesUtil.getRequest().getServerPort() + "/img_zapeat/";
	public static final String PASTA_DOWNLOAD_FORNECEDOR = "http://"+TSFacesUtil.getRequest().getServerName() + ":" + TSFacesUtil.getRequest().getServerPort() + "/img_zapeat/fornecedor/";
	
	public static final String PASTA_UPLOAD = "/arquivos/zapeat/img_zapeat/";
	
	public static final String PREFIXO_IMAGEM_CATEGORIA = "20x20_";
	public static final String PREFIXO_IMAGEM_FORMA_PAGAMENTO = "25x18_";
	public static final String PREFIXO_IMAGEM_PROMOCAO_FULL = "590x260_";
	public static final String PREFIXO_IMAGEM_PROMOCAO_THUMB = "180x79_";
	public static final String PREFIXO_PROMOCAO_THUMB = "80x80_";
	public static final String PREFIXO_IMAGEM_FORNECEDOR_FULL = "590x260_";
	public static final String PREFIXO_IMAGEM_FORNECEDOR_THUMB = "180x79_";
	public static final String PREFIXO_IMAGEM_CARRO_CHEFE_FULL = "590x260_";
	public static final String PREFIXO_IMAGEM_CARRO_CHEFE_THUMB = "180x79_";
	public static final String PREFIXO_IMAGEM_FORNECEDOR_LOGOMARCA = "80x80_";
	public static final String PREFIXO_IMAGEM_BANNER_SUPERIOR_GRANDE = "728x90_";
	public static final String PREFIXO_IMAGEM_BANNER_LATERAL = "170x260_";
	public static final String PREFIXO_IMAGEM_BANNER_SUPERIOR_PEQUENO = "210x110_";
	public static final String URL_APLICACAO = "http://www.zapeat.com/demo/";
	public static final String SMTP_GMAIL = "smtp.gmail.com";
	public static final String PORTAL_GMAIL = "465";
	public static final String ZAPEAT_GMAIL = "cadastro@zapeat.com";
	public static final String ZAPEAT_SENHA_GMAIL = "enviar!1";
	public static final String SEQUENCE_USUARIO_SITE = "usuarios_id_seq";
}
