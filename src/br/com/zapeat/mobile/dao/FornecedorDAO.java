package br.com.zapeat.mobile.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;
import br.com.zapeat.mobile.model.FornecedorModel;
import br.com.zapeat.mobile.model.LocalizacaoModel;
import br.com.zapeat.mobile.model.UsuarioModel;

public class FornecedorDAO {

	public FornecedorModel obter(FornecedorModel model, UsuarioModel usuario) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		String token = null;

		if (!TSUtil.isEmpty(usuario)) {
			token = usuario.getToken();
		}

		broker.setPropertySQL("fornecedordao.obter", token, token, model.getId());

		return (FornecedorModel) broker.getObjectBean(FornecedorModel.class, "id", "nomeFantasia", "latitude", "longitude", "cidadeModel.nome", "cidadeModel.estadoModel.sigla", "logradouro", "numero", "cep", "bairro", "telefone", "quantidadeIndicacoes", "indicado", "naoIndicado", "imagemThumb");

	}

	public List<FornecedorModel> pesquisarCheckIn(LocalizacaoModel localizacao, UsuarioModel usuario) {

		StringBuilder sql = new StringBuilder("SELECT F.ID,F.NOME_FANTASIA,F.CEP,F.LOGRADOURO,")
				.append(" F.NUMERO,F.LOGOMARCA,F.BAIRRO,")
				.append(" (SELECT COALESCE(COUNT(*),0) FROM INDICACOES COM WHERE COM.FORNECEDOR_ID = F.ID AND COM.FLAG_INDICA),")
				.append(" EXISTS(SELECT 1 FROM INDICACOES COM,USUARIOS_SITE US WHERE US.ID = COM.USUARIO_ID AND COM.FORNECEDOR_ID = F.ID AND COM.FLAG_INDICA  AND US.TOKEN = ?) ");

		boolean existeLatitudeLongitude = !TSUtil.isEmpty(localizacao) && !TSUtil.isEmpty(localizacao.getLatitude()) && !TSUtil.isEmpty(localizacao.getLongitude());

		if (existeLatitudeLongitude) {

			sql.append(" ,distanceInKm(F.LATITUDE,F.LONGITUDE,?,?) as DISTANCIA ");

		} else {

			sql.append(" ,null ");

		}

		sql.append(" FROM FORNECEDORES F WHERE F.FLAG_ATIVO ");

		if (existeLatitudeLongitude) {

			sql.append(" AND distanceInKm(F.LATITUDE,F.LONGITUDE,?,?) <= 10 ");

		}

		if (existeLatitudeLongitude) {

			sql.append("ORDER BY DISTANCIA");

		}

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL(sql.toString());

		String token = null;

		if (!TSUtil.isEmpty(usuario)) {
			token = usuario.getToken();
		}

		broker.set(token);

		if (existeLatitudeLongitude) {

			broker.set(localizacao.getLatitude());
			broker.set(localizacao.getLongitude());
			broker.set(localizacao.getLatitude());
			broker.set(localizacao.getLongitude());

		}

		return broker.getCollectionBean(FornecedorModel.class, "id", "nomeFantasia", "cep",
										"logradouro", "numero", "imagemThumb", 
										"bairro","quantidadeIndicacoes", "indicado", "distancia");

	}

}
