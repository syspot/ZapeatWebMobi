package br.com.zapeat.mobile.dao;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;
import br.com.zapeat.mobile.model.FornecedorModel;
import br.com.zapeat.mobile.model.UsuarioModel;

public class FornecedorDAO {
	
	public FornecedorModel obter(FornecedorModel model,UsuarioModel usuario) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		String token = null;
		
		if(!TSUtil.isEmpty(usuario)) {
			token = usuario.getToken();
		}

		
		broker.setPropertySQL("fornecedordao.obter",token,token,model.getId());
		
		return (FornecedorModel) broker.getObjectBean(FornecedorModel.class,"id","nomeFantasia","latitude", "longitude","cidadeModel.nome","cidadeModel.estadoModel.sigla","logradouro","numero","cep","bairro","telefone","quantidadeIndicacoes","indicado","naoIndicado","imagemThumb");
		
	}

}
