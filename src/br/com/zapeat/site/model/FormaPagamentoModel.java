package br.com.zapeat.site.model;

import java.io.Serializable;

import br.com.zapeat.site.util.Constantes;

@SuppressWarnings("serial")
public class FormaPagamentoModel implements Serializable {

	private Long id;
	private String imagem;

	public FormaPagamentoModel() {
	
	}
	
	public FormaPagamentoModel(String imagem) {
		this.imagem = imagem;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String getImagemView() {
		return Constantes.PASTA_DOWNLOAD + Constantes.PREFIXO_IMAGEM_FORMA_PAGAMENTO + imagem;
	}

}
