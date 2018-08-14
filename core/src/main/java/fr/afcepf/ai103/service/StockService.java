package fr.afcepf.ai103.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoAdresse;
import fr.afcepf.ai103.dao.IDaoAnnonce;
import fr.afcepf.ai103.dao.IDaoCategorie;
import fr.afcepf.ai103.dao.IDaoConservation;
import fr.afcepf.ai103.dao.IDaoConsommation;
import fr.afcepf.ai103.dao.IDaoModeConso;
import fr.afcepf.ai103.dao.IDaoProduit;
import fr.afcepf.ai103.dao.IDaoReponse;
import fr.afcepf.ai103.dao.IDaoSousCategorie;
import fr.afcepf.ai103.dao.IDaoStock;
import fr.afcepf.ai103.dao.IDaoUtilisateur;
import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Categorie;
import fr.afcepf.ai103.data.Conservation;
import fr.afcepf.ai103.data.Consommation;
import fr.afcepf.ai103.data.Produit;
import fr.afcepf.ai103.data.SousCategorie;
import fr.afcepf.ai103.data.Stock;
import fr.afcepf.ai103.data.Utilisateur;

@Stateless
@Local
public class StockService implements IStockService
{
	@EJB
	private IDaoAnnonce daoAnnonce;
	@EJB
	private IDaoCategorie daoCategorie;
	@EJB
	private IDaoConservation daoConservation;	
	@EJB
	private IDaoConsommation daoConsommation;
	@EJB
	private IDaoModeConso daoModeConso;
	@EJB
	private IDaoProduit daoProduit;
	@EJB
	private IDaoReponse daoReponse;
	@EJB
	private IDaoSousCategorie daoSousCategorie;
	@EJB
	private IDaoStock daoStock;
	@EJB
	private IDaoUtilisateur daoUtilisateur;
	@EJB
	private IDaoAdresse daoAdresse;

	@Override
	public Adresse recupererAdresseById(int id_adresse)
	{
		return daoAdresse.getAdresseById(id_adresse);
	}
	
	@Override
	public void ajouterProduit(Stock stock)
	{
		 try {
			daoStock.ajouterProduit(stock);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void partagerProduit(Annonce annonce) 
	{
		daoAnnonce.create(annonce);
	}
	
	// méthode manger/jeter produit
	// mode_consommation = 1 pour "manger" ; = 2 pour "jeter"
	@Override
	public void consommerProduitStock(Integer id_prod_stock, Integer id_mode, Date date, Double quantite, Integer id_user)
	{
		Consommation conso = new Consommation();
		conso.setDate_conso(date);
		conso.setStock(daoStock.getStockById(id_prod_stock));
		
		if (id_mode == 1)
		{
			conso.setModeConso(daoModeConso.getModeConsoById(id_mode));
			conso.setQte_conso(quantite);
		}
		else if (id_mode == 2)
		{
			Double quantiteInitiale = daoStock.getQuantiteById(id_prod_stock, id_user);
			conso.setModeConso(daoModeConso.getModeConsoById(id_mode));
			conso.setQte_conso(quantiteInitiale);
		}
		daoConsommation.create(conso);
	}
	
	@Override
	public Stock getStockById(Integer Id_prod_stock)
	{
		
		return daoStock.getStockById(Id_prod_stock);
	}
	
	@Override
	public Double quantiteRestante(Integer id_prod_stock, Integer id_user)
	{
		Double quantiteInitiale = daoStock.getStockById(id_prod_stock).getQte_initiale();
		Double quantiteConsommee = daoConsommation.getQuantiteConsoById(id_prod_stock);
		
		//System.out.println("STOCKBEAN - quantité initiale : " + quantiteInitiale);
		//System.out.println("STOCKBEAN - quantité consommée : " + quantiteConsommee);

		if (quantiteConsommee == null)
		{
			System.out.println(quantiteConsommee);
			return quantiteInitiale;
		}
		else
		{
			Double quantiteRestante = quantiteInitiale - quantiteConsommee;
			
			System.out.println("STOCKBEAN - quantité restante : " + quantiteRestante);
			
			return quantiteRestante;
		}
	}
	
	@Override
	public List<Categorie> getAllCategorie()
	{
		return daoCategorie.getAllCategorie();
									
	}
	
	
	@Override
	public List<SousCategorie> getSousCategoriebyIDCategorie(int id_cat)
	{
		return  daoSousCategorie.getSousCategoriebyIDCategorie(id_cat);
	}

	
	
	@Override
	public List<Produit> GetProduitbyIDSousCategorie (int id_sous_cat)
	{
		
		return daoProduit.GetProduitbyIDSousCategorie(id_sous_cat) ;
		 	
	}
	
	@Override
	public Produit GetProduitbyIDProduit (int id_prod)
	{
		
		return daoProduit.GetProduitbyIDProduit(id_prod);
		 	
	}
	
	@Override
	public Utilisateur getUtilisateurByIdUser(int id_user)
	{
		return daoUtilisateur.getUserById(id_user);
	}
	
	@Override
	public List<Conservation> getAllConservation()
	{
		return daoConservation.getAllConservation();
	}
	
	@Override
	public Conservation GetConservationByIDConservation (int id_conserv)
	{
		return daoConservation.GetConservationByIDConservation(id_conserv);
	}
	
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
