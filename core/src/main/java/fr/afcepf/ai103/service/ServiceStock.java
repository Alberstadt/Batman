package fr.afcepf.ai103.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoAnnonce;
import fr.afcepf.ai103.dao.IDaoReponse;
import fr.afcepf.ai103.dao.IDaoStock;
import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Consommation;
import fr.afcepf.ai103.data.Reponse;
import fr.afcepf.ai103.data.Stock;

@Stateless
@Local
public class ServiceStock implements IServiceStock
{

	@EJB
	private IDaoAnnonce daoAnnonce;
	
	@EJB
	private IDaoStock daoStock;
	
	@EJB
	private IDaoReponse daoReponse;

	@Override
	public Double calculerQteReelle(Integer id_prod_stock)
	{
		Double qteReelle = 0.0;
		Double qteConso = 0.0;
		Double qteAnnonce = 0.0;
		Stock st = daoStock.getStockById(id_prod_stock);
		List<Consommation> listeConso = st.getConsommations();
		List<Annonce> listeAnnonce = st.getAnnonces();
		
		if(listeConso.size() != 0)
		{
			for (Consommation conso : listeConso)
			{
				qteConso += conso.getQte_conso();
			}
		}
		
		if(listeAnnonce.size() != 0)
		{
			for (Annonce annonce : listeAnnonce)
			{
				if(annonce.getDate_retrait() == null)
				{
					qteAnnonce += annonce.getQte_publi();
				}
			}
		}
		
		qteReelle = st.getQte_initiale() - qteConso - qteAnnonce;
		
		return qteReelle;
	}
	
	@Override
	public List<Stock> listerProdDispo(Integer id_user)
	{
		List <Stock> listeStock = daoStock.getStockByUserId(id_user);
		List <Stock> prodDispo = new ArrayList<Stock>();
		
		for (Stock stock : listeStock)
		{
			Double qteReelle = calculerQteReelle(stock.getId_prod_stock());
			
			if(qteReelle > 0.0)
			{
				prodDispo.add(stock);
			}
			
			else if(qteReelle == 0.0)
			{
				if(daoReponse.getDateTransByStockId(stock.getId_prod_stock()).size() == 0)
				{
					prodDispo.add(stock);
				}
			}
		}
		return prodDispo;
	}
}
