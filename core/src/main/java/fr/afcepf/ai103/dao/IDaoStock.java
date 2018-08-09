package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Stock;

public interface IDaoStock
{
	Stock getStockById(Integer id_prod_stock);

	List<Stock> getStockByUserId(Integer id_user);
}
