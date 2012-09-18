package br.com.zapeat.site.dao;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.zapeat.site.model.UsuarioModel;

public class UsuarioDAO {
	
	public UsuarioModel obterAcesso(UsuarioModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("usuariodao.obteracesso",model.getEmail(),model.getSenha());
		
		return (UsuarioModel) broker.getObjectBean(UsuarioModel.class, "id","nome","token");
		
	}
	
	public UsuarioModel obterPorEmail(UsuarioModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("usuariodao.obterporemail",model.getEmail());
		
		return (UsuarioModel) broker.getObjectBean(UsuarioModel.class, "id","nome","token","senha");
		
	}
	
	public UsuarioModel obterPorToken(UsuarioModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("usuariodao.obterportoken",model.getToken());
		
		return (UsuarioModel) broker.getObjectBean(UsuarioModel.class, "id","nome","token");
		
	}

}
