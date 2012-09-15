package br.com.zapeat.site.dao;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.zapeat.site.model.FornecedorModel;

public class FornecedorDAO {
	
	public FornecedorModel obter(FornecedorModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("fornecedordao.obter",model.getId());
		
		return (FornecedorModel) broker.getObjectBean(FornecedorModel.class,"id","nomeFantasia","latitude","longitude");
		
	}

}
