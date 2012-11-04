package br.com.zapeat.site.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;
import br.com.zapeat.site.model.LocalizacaoModel;
import br.com.zapeat.site.model.PromocaoModel;
import br.com.zapeat.site.model.UsuarioModel;
import br.com.zapeat.site.util.Utilitarios;

public class PromocaoDAO {

	@SuppressWarnings("unchecked")
	public List<PromocaoModel> pesquisar(PromocaoModel model, LocalizacaoModel localizacao, UsuarioModel usuario) {

		StringBuilder sql = new StringBuilder("SELECT P.ID,P.DESCRICAO,T.ID,T.DESCRICAO,F.ID,F.NOME_FANTASIA,F.CEP,F.LOGRADOURO,F.NUMERO,F.BAIRRO,C.NOME,P.INICIO,P.FIM,P.PRECO_ORIGINAL,P.PRECO_PROMOCIONAL,P.TITULO,P.IMAGEM_THUMB,CAT.DESCRICAO,(SELECT COALESCE(COUNT(*),0) FROM INDICACOES COM WHERE COM.FORNECEDOR_ID = F.ID AND COM.FLAG_INDICA),EXISTS(SELECT 1 FROM INDICACOES COM,USUARIOS_SITE US WHERE US.ID = COM.USUARIO_ID AND COM.FORNECEDOR_ID = F.ID AND COM.FLAG_INDICA  AND US.TOKEN = ?) ");

		boolean existeLatitudeLongitude = !TSUtil.isEmpty(localizacao) && !TSUtil.isEmpty(localizacao.getLatitude()) && !TSUtil.isEmpty(localizacao.getLongitude());

		boolean filtrarDistancia = !TSUtil.isEmpty(localizacao) && !TSUtil.isEmpty(localizacao.getLatitude()) && !TSUtil.isEmpty(localizacao.getLongitude()) && !TSUtil.isEmpty(localizacao.getDistanciaMaxima());

		if (existeLatitudeLongitude) {

			sql.append(" ,distanceInKm(F.LATITUDE,F.LONGITUDE,?,?) as DISTANCIA ");

		} else {

			sql.append(" ,null ");

		}

		sql.append(" FROM PROMOCOES P,TIPOS_PROMOCOES T,FORNECEDORES F,FORNECEDORES_CATEGORIAS FC,CIDADES C,CATEGORIAS CAT WHERE F.ID = FC.FORNECEDOR_ID AND F.CIDADE_ID = C.ID AND P.TIPO_PROMOCAO_ID = T.ID AND FC.ID = P.FORNECEDOR_CATEGORIA_ID AND FC.CATEGORIA_ID = CAT.ID AND FC.ID = P.FORNECEDOR_CATEGORIA_ID AND (INICIO IS NULL OR INICIO <= CURRENT_DATE) AND (FIM IS NULL OR  FIM >= CURRENT_DATE) ");

		if (!TSUtil.isEmpty(model.getDescricao())) {

			sql.append(" AND (SEM_ACENTOS(P.DESCRICAO) ILIKE ? OR SEM_ACENTOS(F.NOME_FANTASIA) ILIKE ? OR SEM_ACENTOS(P.TITULO) ILIKE ? OR SEM_ACENTOS(C.NOME) ILIKE ? OR SEM_ACENTOS(F.BAIRRO) ILIKE ?)");

		}

		if (filtrarDistancia) {

			sql.append(" AND distanceInKm(F.LATITUDE,F.LONGITUDE,?,?) <= ? ");

		}

		if (!TSUtil.isEmpty(model.getCategoriaModel()) && !TSUtil.isEmpty(Utilitarios.tratarLong(model.getCategoriaModel().getId()))) {

			sql.append(" AND FC.CATEGORIA_ID = ? ");

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

		}

		if (!TSUtil.isEmpty(model.getDescricao())) {

			String filtro = "%" + model.getDescricao() + "%";

			broker.set(filtro);
			broker.set(filtro);
			broker.set(filtro);
			broker.set(filtro);
			broker.set(filtro);

		}

		if (filtrarDistancia) {

			broker.set(localizacao.getLatitude());
			broker.set(localizacao.getLongitude());
			broker.set(Double.valueOf(localizacao.getDistanciaMaxima().toString()));
		}

		if (!TSUtil.isEmpty(model.getCategoriaModel()) && !TSUtil.isEmpty(Utilitarios.tratarLong(model.getCategoriaModel().getId()))) {

			broker.set(model.getCategoriaModel().getId());

		}

		return broker.getCollectionBean(PromocaoModel.class, "id", "descricao", "tipoPromocaoModel.id", "tipoPromocaoModel.descricao", "fornecedorModel.id", "fornecedorModel.nomeFantasia", "fornecedorModel.cep", "fornecedorModel.logradouro", "fornecedorModel.numero", "fornecedorModel.bairro", "fornecedorModel.cidadeModel.nome", "inicio", "fim", "precoOriginal", "precoPromocional", "titulo", "imagemThumb", "categoriaModel.descricao", "fornecedorModel.quantidadeIndicacoes", "fornecedorModel.indicado", "distanciaCalculada");

	}

	public PromocaoModel obter(PromocaoModel model, UsuarioModel usuario) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		String token = null;

		if (!TSUtil.isEmpty(usuario)) {
			token = usuario.getToken();
		}

		broker.setPropertySQL("promocaodao.obter", token, model.getId());

		return (PromocaoModel) broker.getObjectBean(PromocaoModel.class, "id", "tipoPromocaoModel.id", "tipoPromocaoModel.descricao", "fornecedorModel.id", "fornecedorModel.nomeFantasia", "descricao", "inicio", "fim", "precoOriginal", "precoPromocional", "titulo", "fornecedorModel.longitude", "fornecedorModel.latitude", "fornecedorModel.cidadeModel.nome", "fornecedorModel.cidadeModel.estadoModel.sigla", "fornecedorModel.logradouro", "fornecedorModel.numero", "fornecedorModel.cep", "fornecedorModel.bairro", "fornecedorModel.telefone", "fornecedorModel.quantidadeIndicacoes", "indicada", "porcentagemDesconto");

	}

	@SuppressWarnings("unchecked")
	public List<PromocaoModel> pesquisarDisponiveis() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("promocaodao.usuario.pesquisar");

		return broker.getCollectionBean(PromocaoModel.class, "id", "descricao", "fornecedorModel.nomeFantasia", "titulo", "precoOriginal", "precoPromocional", "inicio", "fim", "fornecedorModel.latitude", "fornecedorModel.longitude");

	}

}
