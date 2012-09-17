package br.com.zapeat.site.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CategoriaModel implements Serializable {

	private Long id;

	private String descricao;

	private Boolean flagAtivo;

	private String imagem;

	private String styleClass;
	
	//Filtro
	
	private Integer distanciaMaxima;

	public CategoriaModel() {
	
	}
	
	public CategoriaModel(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaModel other = (CategoriaModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getDistanciaMaxima() {
		return distanciaMaxima;
	}

	public void setDistanciaMaxima(Integer distanciaMaxima) {
		this.distanciaMaxima = distanciaMaxima;
	}
	
	

}
