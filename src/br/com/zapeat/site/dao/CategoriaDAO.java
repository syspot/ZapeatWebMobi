package br.com.zapeat.site.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.zapeat.site.model.CategoriaModel;
import br.com.zapeat.site.model.UsuarioModel;

public class CategoriaDAO {
	
	@SuppressWarnings("unchecked")
	public List<CategoriaModel> pesquisar(UsuarioModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("categoriadao.usuario.pesquisar",model.getId());
		
		return broker.getCollectionBean(CategoriaModel.class,"id","descricao","imagem");
		
		
	}

}
