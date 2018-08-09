package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Stock;

public interface IServiceStock 
{
	Double calculerQteReelle(Integer id_prod_stock);

	List<Stock> listerProdDispo(Integer id_user);
}
