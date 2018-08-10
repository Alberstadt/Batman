package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Stock;

public interface IDaoStock
{
	Stock getStockById(Integer id_prod_stock);

	List<Stock> getStockByUserId(Integer id_user);

	Double getQuantiteById(Integer id_prod_stock, Integer id_user);

	List<Stock> getAll();

	String recupererProduit(Integer id_prod);

	List<Stock> listeStockTotalByIdUtilisateur(int id_user);

	Stock ajouterProduit(Stock s) throws Exception;
}
