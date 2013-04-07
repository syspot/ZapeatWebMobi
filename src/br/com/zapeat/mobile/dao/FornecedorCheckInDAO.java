package br.com.zapeat.mobile.dao;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.zapeat.mobile.model.FornecedorCheckinModel;

public class FornecedorCheckInDAO {

	public FornecedorCheckinModel inserir(FornecedorCheckinModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("fornecedores_checkin_id_seq"));
		
		broker.setPropertySQL("fornecedorcheckindao.inserir",model.getId(), model.getFornecedorModel().getId(), model.getUsuarioModel().getId(), model.getTexto(), model.getImagem());

		broker.execute();
		
		return model;

	}

}
