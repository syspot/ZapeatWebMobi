package br.com.zapeat.site.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class PromocaoModel implements Serializable {

	private Long id;
	private TipoPromocaoModel tipoPromocaoModel;
	private FornecedorModel fornecedorModel;
	private String titulo;
	private String descricao;
	private Date inicio;
	private Date fim;
	private Double precoOriginal;
	private Double precoPromocional;
	private Integer indicacoes;
	private String imagemThumb;
	private CategoriaModel categoriaModel;
	private Double distanciaCalculada;
	private Boolean indicada;
	private Boolean naoIndicada;
	private Double porcentagemDesconto;
	private List<ComentarioModel> comentarios;

	public PromocaoModel() {

	}

	public PromocaoModel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPromocaoModel getTipoPromocaoModel() {
		return tipoPromocaoModel;
	}

	public void setTipoPromocaoModel(TipoPromocaoModel tipoPromocaoModel) {
		this.tipoPromocaoModel = tipoPromocaoModel;
	}

	public FornecedorModel getFornecedorModel() {
		return fornecedorModel;
	}

	public void setFornecedorModel(FornecedorModel fornecedorModel) {
		this.fornecedorModel = fornecedorModel;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Double getPrecoOriginal() {
		return precoOriginal;
	}

	public void setPrecoOriginal(Double precoOriginal) {
		this.precoOriginal = precoOriginal;
	}

	public Double getPrecoPromocional() {
		return precoPromocional;
	}

	public void setPrecoPromocional(Double precoPromocional) {
		this.precoPromocional = precoPromocional;
	}

	public Integer getIndicacoes() {
		return indicacoes;
	}

	public void setIndicacoes(Integer indicacoes) {
		this.indicacoes = indicacoes;
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
		PromocaoModel other = (PromocaoModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getImagemThumb() {
		return imagemThumb;
	}

	public void setImagemThumb(String imagemThumb) {
		this.imagemThumb = imagemThumb;
	}

	/**
	 * Campo Somente para exibição em tela, não será persistido
	 * 
	 */
	public CategoriaModel getCategoriaModel() {
		return categoriaModel;
	}

	/**
	 * Campo Somente para exibição em tela, não será persistido
	 * 
	 */

	public void setCategoriaModel(CategoriaModel categoriaModel) {
		this.categoriaModel = categoriaModel;
	}

	public Double getDistanciaCalculada() {
		return distanciaCalculada;
	}

	public void setDistanciaCalculada(Double distanciaCalculada) {
		this.distanciaCalculada = distanciaCalculada;
	}

	public Integer getDistancia() {
		if (TSUtil.isEmpty(this.distanciaCalculada)) {
			return null;
		}

		BigDecimal distancia = new BigDecimal(this.distanciaCalculada).setScale(0, RoundingMode.DOWN);

		return distancia.intValue();

	}

	public Boolean getIndicada() {
		return indicada;
	}

	public void setIndicada(Boolean indicada) {
		this.indicada = indicada;
	}

	public Double getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(Double porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}

	public Boolean getNaoIndicada() {
		return naoIndicada;
	}

	public void setNaoIndicada(Boolean naoIndicada) {
		this.naoIndicada = naoIndicada;
	}

	public List<ComentarioModel> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioModel> comentarios) {
		this.comentarios = comentarios;
	}
	
	

}
