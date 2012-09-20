package br.com.zapeat.site.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ComentarioModel implements Serializable {

	private Long id;

	private UsuarioModel usuarioModel;

	private FornecedorModel fornecedorModel;

	private String descricao;

	private Date dataCadastro;

	private Boolean flagIndicaAtendimento;

	private Boolean flagIndicaPromocao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getFlagIndicaAtendimento() {
		return flagIndicaAtendimento;
	}

	public void setFlagIndicaAtendimento(Boolean flagIndicaAtendimento) {
		this.flagIndicaAtendimento = flagIndicaAtendimento;
	}

	public Boolean getFlagIndicaPromocao() {
		return flagIndicaPromocao;
	}

	public void setFlagIndicaPromocao(Boolean flagIndicaPromocao) {
		this.flagIndicaPromocao = flagIndicaPromocao;
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
		ComentarioModel other = (ComentarioModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public FornecedorModel getFornecedorModel() {
		return fornecedorModel;
	}

	public void setFornecedorModel(FornecedorModel fornecedorModel) {
		this.fornecedorModel = fornecedorModel;
	}

}
