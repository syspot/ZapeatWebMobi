package br.com.zapeat.mobile.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSParseUtil;
import br.com.zapeat.mobile.model.ComentarioModel;
import br.com.zapeat.mobile.model.PromocaoModel;

public class ComentarioDAO {

	@SuppressWarnings("unchecked")
	public List<ComentarioModel> pesquisar(PromocaoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("comentariodao.pesquisarpromocao", model.getId());

		return broker.getCollectionBean(ComentarioModel.class, "id", "descricao", "promocaoModel.id", "usuarioModel.nome","dataCadastro");

	}
	
	public void inserir(ComentarioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("comentariodao.inserir", model.getUsuarioModel().getId(),model.getDescricao(),TSParseUtil.dateToTimeStamp(model.getDataCadastro()),model.getPromocaoModel().getId());
		
		broker.execute();
		
	}

}
