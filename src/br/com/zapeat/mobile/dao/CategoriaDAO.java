package br.com.zapeat.mobile.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;
import br.com.zapeat.mobile.model.CategoriaModel;
import br.com.zapeat.mobile.model.LocalizacaoModel;

public class CategoriaDAO {

	@SuppressWarnings("unchecked")
	public List<CategoriaModel> pesquisar(LocalizacaoModel localizacao) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder("SELECT C.ID,C.DESCRICAO,C.IMAGEM,");

		boolean existeLatitudeLongitude = !TSUtil.isEmpty(localizacao) && !TSUtil.isEmpty(localizacao.getLatitude()) && !TSUtil.isEmpty(localizacao.getLongitude());

		sql.append("(SELECT COUNT(*) FROM PROMOCOES P,FORNECEDORES_CATEGORIAS F,FORNECEDORES FORC WHERE FORC.ID = F.FORNECEDOR_ID AND P.FORNECEDOR_CATEGORIA_ID = F.ID AND CASE P.TIPO_PROMOCAO_ID WHEN 1 THEN ((INICIO IS NULL OR INICIO <=  CURRENT_TIMESTAMP) AND (FIM IS NULL OR  FIM >= CURRENT_TIMESTAMP)) WHEN 2 THEN CAST(INICIO AS DATE) = CURRENT_DATE WHEN 3 THEN ((INICIO IS NULL OR CAST(INICIO AS DATE) <= CURRENT_DATE) 	AND (FIM IS NULL OR  CAST(FIM AS DATE) >= CURRENT_DATE)) END ");

		if (existeLatitudeLongitude) {

			sql.append(" AND distanceInKm(FORC.LATITUDE,FORC.LONGITUDE,?,?) <= 100 ");

		}

		sql.append(" AND C.ID = F.CATEGORIA_ID) AS QNT FROM CATEGORIAS C WHERE C.FLAG_ATIVO ORDER BY C.DESCRICAO");
		
		broker.setSQL(sql.toString());

		if (existeLatitudeLongitude) {

			broker.set(localizacao.getLatitude());
			broker.set(localizacao.getLongitude());

		}

		

		return broker.getCollectionBean(CategoriaModel.class, "id", "descricao", "imagem", "quantidadePromocoes");

	}

	public CategoriaModel obter(CategoriaModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("categoriadao.obter", model.getId());

		return (CategoriaModel) broker.getObjectBean(CategoriaModel.class, "id", "descricao");

	}

}
