package br.com.zapeat.site.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;
import br.com.zapeat.site.model.CategoriaModel;
import br.com.zapeat.site.model.LocalizacaoModel;
import br.com.zapeat.site.model.PromocaoModel;
import br.com.zapeat.site.model.UsuarioModel;

public class PromocaoDAO {

	@SuppressWarnings("unchecked")
	public List<PromocaoModel> pesquisar(CategoriaModel model, LocalizacaoModel localizacao) {

		StringBuilder sql = new StringBuilder("SELECT P.ID,P.DESCRICAO,T.ID,T.DESCRICAO,F.ID,F.NOME_FANTASIA,F.CEP,F.LOGRADOURO,F.NUMERO,F.BAIRRO,C.NOME,P.INICIO,P.FIM,P.PRECO_ORIGINAL,P.PRECO_PROMOCIONAL,P.TITULO,P.IMAGEM_THUMB,CAT.DESCRICAO,(SELECT COALESCE(COUNT(*),0) FROM COMENTARIOS COM WHERE COM.FORNECEDOR_ID = F.ID AND COM.FLAG_INDICA_ATENDIMENTO) ");
		
		boolean filtrarDistancia = !TSUtil.isEmpty(localizacao) && !TSUtil.isEmpty(localizacao.getLatitude()) 
				&& !TSUtil.isEmpty(localizacao.getLongitude()) && !TSUtil.isEmpty(model.getDistanciaMaxima());
		
		boolean existeLatitudeLongitude = !TSUtil.isEmpty(localizacao) && !TSUtil.isEmpty(localizacao.getLatitude()) 
				&& !TSUtil.isEmpty(localizacao.getLongitude());
		
		if (existeLatitudeLongitude) {
			
			sql.append(" ,distanceInKm(F.LATITUDE,F.LONGITUDE,?,?) ") ;
			
		} else {
			
			sql.append(" ,null ") ;
			
		}
		
		sql.append(" FROM PROMOCOES P,TIPOS_PROMOCOES T,FORNECEDORES F,FORNECEDORES_CATEGORIAS FC,CIDADES C,CATEGORIAS CAT WHERE F.ID = FC.FORNECEDOR_ID AND F.CIDADE_ID = C.ID AND P.TIPO_PROMOCAO_ID = T.ID AND F.ID = P.FORNECEDOR_ID AND FC.CATEGORIA_ID = CAT.ID AND FC.CATEGORIA_ID = ? AND (INICIO IS NULL OR INICIO <= CURRENT_DATE) AND (FIM IS NULL OR  FIM >= CURRENT_DATE)");
		

		if (filtrarDistancia) {

			sql.append(" AND distanceInKm(F.LATITUDE,F.LONGITUDE,?,?) <= ? ");

		}

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL(sql.toString());
		
		if (existeLatitudeLongitude) {
			
			broker.set(localizacao.getLatitude());
			broker.set(localizacao.getLongitude());
			
		}
		
		broker.set(model.getId());

		if (filtrarDistancia) {
			
			broker.set(localizacao.getLatitude());
			broker.set(localizacao.getLongitude());
			broker.set(Double.valueOf(model.getDistanciaMaxima().toString()));
		}
		
		return broker.getCollectionBean(PromocaoModel.class, "id", "descricao", "tipoPromocaoModel.id", "tipoPromocaoModel.descricao", "fornecedorModel.id", "fornecedorModel.nomeFantasia", "fornecedorModel.cep", "fornecedorModel.logradouro", "fornecedorModel.numero", "fornecedorModel.bairro", "fornecedorModel.cidadeModel.nome", "inicio", "fim", "precoOriginal", "precoPromocional", "titulo", "imagemThumb", "categoriaModel.descricao", "fornecedorModel.quantidadeIndicacoes","distanciaCalculada");

	}

	public PromocaoModel obter(PromocaoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("promocaodao.obter", model.getId());

		return (PromocaoModel) broker.getObjectBean(PromocaoModel.class, "id", "tipoPromocaoModel.id", "tipoPromocaoModel.descricao", "fornecedorModel.id", "fornecedorModel.nomeFantasia", "descricao", "inicio", "fim", "precoOriginal", "precoPromocional", "titulo", "fornecedorModel.longitude", "fornecedorModel.latitude", "fornecedorModel.cidadeModel.nome", "fornecedorModel.cidadeModel.estadoModel.sigla", "fornecedorModel.logradouro", "fornecedorModel.numero", "fornecedorModel.cep", "fornecedorModel.bairro", "fornecedorModel.telefone", "fornecedorModel.quantidadeIndicacoes");

	}
	
	@SuppressWarnings("unchecked")
	public List<PromocaoModel> pesquisarDisponiveis(UsuarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("promocaodao.usuario.pesquisar", model.getId());

		return broker.getCollectionBean(PromocaoModel.class, "id", "descricao", "fornecedorModel.nomeFantasia", "titulo", "precoOriginal", "precoPromocional", "inicio", "fim","fornecedorModel.latitude","fornecedorModel.longitude");

		
	}

}
