package br.com.zapeat.mobile.dao;

import br.com.topsys.constant.TSConstant;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.zapeat.mobile.model.UsuarioModel;
import br.com.zapeat.mobile.util.Constantes;

public class UsuarioDAO {

	public UsuarioModel obterAcesso(UsuarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.obteracesso", model.getEmail(), model.getSenha());

		return (UsuarioModel) broker.getObjectBean(UsuarioModel.class, "id", "nome", "token", "flagAtivo");

	}

	public UsuarioModel obterPorEmail(UsuarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.obterporemail", model.getEmail());

		return (UsuarioModel) broker.getObjectBean(UsuarioModel.class, "id", "nome", "token", "senha");

	}

	public UsuarioModel obterPorToken(UsuarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.obterportoken", model.getToken());

		return (UsuarioModel) broker.getObjectBean(UsuarioModel.class, "id", "nome", "token");

	}

	public UsuarioModel obter(UsuarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.obter", model.getEmail());

		return (UsuarioModel) broker.getObjectBean(UsuarioModel.class, "id", "nome", "email", "senha", "flagAtivo", "imagem", "flagAceitouTermo","token");

	}

	public UsuarioModel obterById(UsuarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.obterbyid", model.getId());

		return (UsuarioModel) broker.getObjectBean(UsuarioModel.class, "id", "nome", "email", "senha", "flagAtivo", "imagem", "flagAceitouTermo","token");

	}

	public UsuarioModel inserir(UsuarioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue(Constantes.SEQUENCE_USUARIO_SITE));

		model.setToken(TSCryptoUtil.gerarHash(model.getId().toString() + model.getNome(), TSConstant.CRIPTOGRAFIA_MD5));

		broker.setPropertySQL("usuariodao.inserir", model.getId(), model.getNome(), model.getEmail().toLowerCase(), model.getSenha(), model.getFlagAtivo(), model.getImagem(), model.getToken(), model.getFlagFacebook(), model.getFlagAceitouTermo());

		broker.execute();

		return model;

	}

	public void alterar(UsuarioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.alterar", model.getNome(), model.getId());

		broker.execute();

	}

	public void aceitarTermoUso(UsuarioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.aceitartermouso", model.getId());

		broker.execute();
	}

}
