package fr.afcepf.ai103.service;

import java.util.Date;
import java.util.List;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Categorie;
import fr.afcepf.ai103.data.Conservation;
import fr.afcepf.ai103.data.Produit;
import fr.afcepf.ai103.data.SousCategorie;
import fr.afcepf.ai103.data.Stock;
import fr.afcepf.ai103.data.Utilisateur;

public interface IStockService 
{
	Double calculerQteReelle(Integer id_prod_stock);

	List<Stock> listerProdDispo(Integer id_user);

	void ajouterProduit(Stock stock);

	void partagerProduit(Annonce annonce);

	String recupererLibelle(Integer id_prod_stock);

	Double recupererQuantite(Integer id_prod_stock);

	void consommerProduitStock(Integer id_prod_stock, Integer id_mode, Date date, Double quantite, Integer id_user);

	Double quantiteRestante(Integer id_prod_stock, Integer id_user);

	Conservation GetConservationByIDConservation(int id_conserv);

	List<Conservation> getAllConservation();

	Produit GetProduitbyIDProduit(int id_prod);

	List<Produit> GetProduitbyIDSousCategorie(int id_sous_cat);

	List<SousCategorie> getSousCategoriebyIDCategorie(int id_cat);

	List<Categorie> getAllCategorie();

	Stock getStockById(Integer Id_prod_stock);
}
