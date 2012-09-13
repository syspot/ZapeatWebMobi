package br.com.zapeat.site.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.zapeat.site.model.CategoriaModel;
import br.com.zapeat.site.model.PromocaoModel;

public class PromocaoDAO {

	@SuppressWarnings("unchecked")
	public List<PromocaoModel> pesquisar(CategoriaModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("promocaodao.pesquisar",model.getId());
		
		return broker.getCollectionBean(PromocaoModel.class,"id","descricao","tipoPromocaoModel.id","tipoPromocaoModel.descricao",
										"fornecedorModel.id","fornecedorModel.nomeFantasia","fornecedorModel.cep",
										"fornecedorModel.logradouro","fornecedorModel.numero","fornecedorModel.bairro","fornecedorModel.cidadeModel.nome",
										"inicio","fim","precoOriginal","precoPromocional","titulo","imagemThumb","categoriaModel.descricao");
		
	}
	
	public PromocaoModel obter(PromocaoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("promocaodao.obter", model.getId());

		return (PromocaoModel) broker.getObjectBean(PromocaoModel.class, "id", "tipoPromocaoModel.id", "tipoPromocaoModel.descricao", "fornecedorModel.id", "fornecedorModel.nomeFantasia", "descricao", "inicio", "fim", "precoOriginal", "precoPromocional", "titulo", "fornecedorModel.longitude", "fornecedorModel.latitude","fornecedorModel.cidadeModel.nome","fornecedorModel.cidadeModel.estadoModel.sigla","fornecedorModel.logradouro","fornecedorModel.numero","fornecedorModel.cep","fornecedorModel.bairro","fornecedorModel.telefone");

	}
	
}
