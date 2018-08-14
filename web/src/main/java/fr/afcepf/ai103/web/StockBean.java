package fr.afcepf.ai103.web;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import fr.afcepf.ai103.data.Stock;
import fr.afcepf.ai103.service.IStockService;
import fr.afcepf.ai103.service.IUtilisateurService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import fr.afcepf.ai103.dao.IDaoStock;
import fr.afcepf.ai103.dao.IDaoUtilisateur;
import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Utilisateur;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.DragDropEvent;

import fr.afcepf.ai103.data.Produit;
import fr.afcepf.ai103.data.SousCategorie;
import fr.afcepf.ai103.data.Categorie;
import fr.afcepf.ai103.data.Conservation;
import fr.afcepf.ai103.data.Consommation;

@ManagedBean
@SessionScoped
public class StockBean implements Serializable
{


	@EJB
	private IStockService stockService;
	
	@EJB
	private IDaoStock daoStock;
	
	@EJB
	private IUtilisateurService utilisateurService;
	
	@ManagedProperty(value="#{session}")
	private SessionBean session;
	
	private int id_prod;
	private String id_prod_stock;
	private Produit produit;
	private Produit prodConsomme;
	private Stock stk;
	private Consommation cons;
	private List<Produit> prodConsommes;
	private List<Stock> stocks;
	//private List<Stock> stocksConsommer;
	private List<Stock> stockDrop = new ArrayList<Stock>();
//	private List<Consommation> consommations = new ArrayList<Consommation>();
	private List<Consommation> consoDrop = new ArrayList<Consommation>();
	private String titre;
	private String description;
	private Utilisateur user;
	private Stock stock;
	private String quantite;
	private List<Categorie> categories;
	private List<SousCategorie> sous_categories;
	private List<Produit> produits;
	private List<Conservation> conservations;
	private Date date_peremption;
	private Integer duree_ext_stock ;
	private Double prix;
	private Double qte_initiale;		 
	private int id_cat;
	private String libelle_cat;
	private int id_sous_cat;
	private String libelle_sous_cat;
	private Utilisateur utilisateur;
	private Conservation conservation;
	private int id_conserv;
	private String label;
	
	

	public StockBean ()
	{
		
	}
	
	
	
	@PostConstruct
	public void init()
	{
		categories = stockService.getAllCategorie();
		stocks = stockService.listerProdDispo(session.getUser().getId_user());
	}
	
	
	public void onCarDrop(DragDropEvent ddEvent) 
	{
        cons = ((Consommation) ddEvent.getData());
  
        consoDrop.add(cons);
       
    }
	
	//cette méthode permet de déplacer un produit de mon stock dans la liste stockDrop (jeter produit)
	public void deplacerProduit(DragDropEvent ddEvent) 
	{
         stk = ((Stock) ddEvent.getData());
  
        stockDrop.add(stk);
        stocks.remove(stk);
        consommerProduit(stk.getId_prod_stock());       
    }
	
	//methode permet d'ajouter un produit dans la table consommation
	public void consommerProduit(Integer id_prod_stock)
	{
		stockService.consommerProduitStock(id_prod_stock, 2, new Date(), 1.0, session.getUser().getId_user());
	}
	
public void ajouterProduit()  
	
	{
		 Stock stock = new Stock();
		 conservation = stockService.GetConservationByIDConservation(id_conserv);
		 stock.setConservation(conservation);
		 utilisateur = utilisateurService.getUserById(session.getUser().getId_user());
		 stock.setUtilisateur(utilisateur);
		 produit = stockService.GetProduitbyIDProduit(id_prod);
		 stock.setProduit(produit);
		 stock.setDate_peremption(date_peremption);
		 stock.setDuree_ext_stock(duree_ext_stock);
		 stock.setPrix(prix);
		 stock.setQte_initiale(qte_initiale);
		 stock.setDate_ajout(new Date()); 
		 
		 stockService.ajouterProduit(stock);
		 		 					 
	}
	
	public void chargementSousCategories (ValueChangeEvent e)
	
	{
		
		id_cat = (int) e.getNewValue();
		sous_categories = stockService.getSousCategoriebyIDCategorie(id_cat);
		
		if (produits !=null)
			
		{
			produits.clear();
		}
		
		
		if (conservations !=null)
			
		{
			conservations.clear();
		}
		
	}
	
	public void chargementLibelle (ValueChangeEvent e)
	
	{
	
		id_sous_cat = (int) e.getNewValue();
		produits = stockService.GetProduitbyIDSousCategorie(id_sous_cat);
		
		if (conservations !=null)
			
		{
			conservations.clear();
		}
	
	}
	
	
	public void chargementTypeConservation (ValueChangeEvent e)
	
	{
	 
		id_conserv = (int) e.getNewValue();
		conservations = stockService.getAllConservation();

	}
	
	public Stock recupDonneesProduit()
	{
		stock = stockService.getStockById(1);
		return stock;
	}
	
	public String recupQuantiteRestante()
	{
		quantite = stockService.quantiteRestante(1, 1).toString();
		return quantite;
	}

	public IStockService getStockService() {
		return stockService;
	}

	public void setStockService(IStockService stockService) {
		this.stockService = stockService;
	}

	public void mangerProduit()
	{
		Date date = new Date();
		stockService.consommerProduitStock(1, 1, date , 50.00, session.getUser().getId_user());
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getQuantite() {
		return quantite;
	}

	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public String getId_prod_stock() {
		return id_prod_stock;
	}

	public void setId_prod_stock(String id_prod_stock) {
		this.id_prod_stock = id_prod_stock;
	}
	
	public IStockService getServicestock() {
		return stockService;
	}

	public void setServicestock(IStockService stockService) {
		this.stockService = stockService;
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public List<SousCategorie> getSous_categories() {
		return sous_categories;
	}

	public void setSous_categories(List<SousCategorie> sous_categories) {
		this.sous_categories = sous_categories;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Date getDate_peremption() {
		return date_peremption;
	}

	public void setDate_peremption(Date date_peremption) {
		this.date_peremption = date_peremption;
	}

	public Integer getDuree_ext_stock() {
		return duree_ext_stock;
	}

	public void setDuree_ext_stock(Integer duree_ext_stock) {
		this.duree_ext_stock = duree_ext_stock;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Double getQte_initiale() {
		return qte_initiale;
	}

	public void setQte_initiale(Double qte_initiale) {
		this.qte_initiale = qte_initiale;
	}

	public int getId_cat() {
		return id_cat;
	}

	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}

	public String getLibelle_cat() {
		return libelle_cat;
	}

	public void setLibelle_cat(String libelle_cat) {
		this.libelle_cat = libelle_cat;
	}

	public int getId_sous_cat() {
		return id_sous_cat;
	}

	public void setId_sous_cat(int id_sous_cat) {
		this.id_sous_cat = id_sous_cat;
	}

	public String getLibelle_sous_cat() {
		return libelle_sous_cat;
	}

	public void setLibelle_sous_cat(String libelle_sous_cat) {
		this.libelle_sous_cat = libelle_sous_cat;
	}

	public int getId_prod() {
		return id_prod;
	}

	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public List<Conservation> getConservations() {
		return conservations;
	}

	public void setConservations(List<Conservation> conservations) {
		this.conservations = conservations;
	}

	public Conservation getConservation() {
		return conservation;
	}

	public void setConservation(Conservation conservation) {
		this.conservation = conservation;
	}

	public int getId_conserv() {
		return id_conserv;
	}

	public void setId_conserv(int id_conserv) {
		this.id_conserv = id_conserv;
	}

	public IStockService getServiceStock() {
		return stockService;
	}

	public void setServiceStock(IStockService serviceStock) {
		this.stockService = serviceStock;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Produit getProdConsomme() {
		return prodConsomme;
	}



	public void setProdConsomme(Produit prodConsomme) {
		this.prodConsomme = prodConsomme;
	}



	public List<Produit> getProdConsommes() {
		return prodConsommes;
	}



	public void setProdConsommes(List<Produit> prodConsommes) {
		this.prodConsommes = prodConsommes;
	}



	public List<Consommation> getConsoDrop() {
		return consoDrop;
	}



	public void setConsoDrop(List<Consommation> consoDrop) {
		this.consoDrop = consoDrop;
	}



	public List<Stock> getStockDrop() {
		return stockDrop;
	}



	public void setStockDrop(List<Stock> stockDrop) {
		this.stockDrop = stockDrop;
	}



	public Stock getStk() {
		return stk;
	}



	public void setStk(Stock stk) {
		this.stk = stk;
	}


	public Consommation getCons() {
		return cons;
	}


	public void setCons(Consommation cons) {
		this.cons = cons;
	}
	
	private static final long serialVersionUID = 1L;
	
	
	public IDaoStock getDaoStock() {
		return daoStock;
	}



	public void setDaoStock(IDaoStock daoStock) {
		this.daoStock = daoStock;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public SessionBean getSession()
	{
		return session;
	}

	public void setSession(SessionBean session)
	{
		this.session = session;
	}
}
