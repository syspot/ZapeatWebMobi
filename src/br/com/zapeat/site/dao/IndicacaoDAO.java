package br.com.zapeat.site.dao;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.zapeat.site.model.IndicacaoModel;
import br.com.zapeat.site.util.Constantes;

public class IndicacaoDAO {

	public IndicacaoModel inserir(IndicacaoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue(Constantes.Sequences.INDICACOES));

		broker.setPropertySQL("indicacaodao.inserir", model.getId(), model.getFornecedorModel().getId(), model.getUsuarioModel().getId(), model.getFlagIndica(),model.getDataCadastro());

		broker.execute();

		return model;

	}
}
