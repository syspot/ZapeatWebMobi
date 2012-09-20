package br.com.zapeat.site.dao;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.zapeat.site.model.FornecedorModel;
import br.com.zapeat.site.model.UsuarioModel;

public class FornecedorDAO {
	
	public FornecedorModel obter(FornecedorModel model,UsuarioModel usuario) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("fornecedordao.obter",usuario.getToken(),model.getId());
		
		return (FornecedorModel) broker.getObjectBean(FornecedorModel.class,"id","nomeFantasia","latitude", "longitude","cidadeModel.nome","cidadeModel.estadoModel.sigla","logradouro","numero","cep","bairro","telefone","quantidadeIndicacoes","indicado");
		
	}

}
