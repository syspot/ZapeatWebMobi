package br.com.zapeat.mobile.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.zapeat.mobile.model.FormaPagamentoModel;
import br.com.zapeat.mobile.model.FornecedorModel;

public class FormaPagamentoDAO {
	
	@SuppressWarnings("unchecked")
	public List<FormaPagamentoModel> pesquisar(FornecedorModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("formapagamentodao.pesquisar",model.getId());

		return broker.getCollectionBean(FormaPagamentoModel.class, "id","imagem");

	}

}
