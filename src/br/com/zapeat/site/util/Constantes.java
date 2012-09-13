package br.com.zapeat.site.util;


public class Constantes {
	
	private Constantes() {
	}

	public static final String TEXT_INDICACAO_MOBILE = "Promoção indicada atrávez do Zapeat Mobile";
	
	public interface HttpParams {
		String CATEGORIA_ID = "categoriaId";
		String PROMOCAO_ID = "promocaoId";
	}
	
	public interface Sequences {
		String COMENTARIOS = "comentarios_id_seq";
	}
	
	public static final String USUARIO_LOGADO = "usuario.logado";
	
	public static final String PASTA_DOWNLOAD = "";
	public static final String PREFIXO_IMAGEM_FORNECEDOR_THUMB="";
	
	public interface StyleClassMenu {
		
		String BLOCK_A = "ui-block-a";
		String BLOCK_B = "ui-block-b";
		String BLOCK_C = "ui-block-c";
		
	}
}
