package br.com.zapeat.mobile.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.zapeat.mobile.model.CategoriaModel;

public class CategoriaDAO {

	@SuppressWarnings("unchecked")
	public List<CategoriaModel> pesquisar() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("categoriadao.pesquisar");

		return broker.getCollectionBean(CategoriaModel.class, "id", "descricao", "imagem","quantidadePromocoes");

	}

	public CategoriaModel obter(CategoriaModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("categoriadao.obter", model.getId());

		return (CategoriaModel) broker.getObjectBean(CategoriaModel.class, "id", "descricao");

	}

}
