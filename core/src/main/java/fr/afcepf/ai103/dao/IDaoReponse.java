package fr.afcepf.ai103.dao;

import java.util.Date;
import java.util.List;

import fr.afcepf.ai103.data.Reponse;

public interface IDaoReponse
{
	List<Date> getDateTransByStockId(Integer id_prod_stock);

	int getNombreReponseByIdPubli(Integer id_publi);
}