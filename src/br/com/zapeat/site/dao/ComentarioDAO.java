package br.com.zapeat.site.dao;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.zapeat.site.model.ComentarioModel;
import br.com.zapeat.site.util.Constantes;

public class ComentarioDAO {

	public ComentarioModel inserir(ComentarioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		model.setId(broker.getSequenceNextValue(Constantes.Sequences.COMENTARIOS));

		broker.setPropertySQL("comentariodao.inserir",model.getId(),model.getDescricao(),model.getPromocaoModel().getId(),model.getUsuarioModel().getId(),model.getFlagIndicaPromocao(),model.getFlagIndicaAtendimento(),model.getDataCadastro());
		
		broker.execute();

		return (ComentarioModel) broker.getObjectBean(ComentarioModel.class, "id", "descricao", "usuarioModel.nome", "fornecedorModel.id", "fornecedorModel.nomeFantasia", "fornecedorModel.logoMarca");

	}

}
