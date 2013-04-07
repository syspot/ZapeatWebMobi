package br.com.zapeat.mobile.faces;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.zapeat.mobile.dao.FornecedorDAO;
import br.com.zapeat.mobile.model.FornecedorModel;
import br.com.zapeat.mobile.util.Utilitarios;

@SuppressWarnings("serial")
@ManagedBean
public class CheckinFaces extends LocationServiceFaces {

	private List<FornecedorModel> fornecedores;

	public CheckinFaces() {

		this.fornecedores = new FornecedorDAO().pesquisarCheckIn(super.getLocalizacaoAtual(), Utilitarios.getUsuarioLogado());

	}

	public List<FornecedorModel> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<FornecedorModel> fornecedores) {
		this.fornecedores = fornecedores;
	}

}
