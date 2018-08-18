package fr.afcepf.ai103.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;

import fr.afcepf.ai103.data.Stock;
import fr.afcepf.ai103.data.Unite;
import fr.afcepf.ai103.service.IStockService;
import fr.afcepf.ai103.service.IUtilisateurService;

import javax.annotation.PostConstruct;
import fr.afcepf.ai103.dao.IDaoStock;

import fr.afcepf.ai103.data.Utilisateur;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.DragDropEvent;

import fr.afcepf.ai103.data.Produit;
import fr.afcepf.ai103.data.SousCategorie;
import fr.afcepf.ai103.data.Categorie;
import fr.afcepf.ai103.data.Conservation;
import fr.afcepf.ai103.data.Consommation;

@ManagedBean
@ViewScoped
public class StockBean
{
	@EJB
	private IStockService stockService;
	
	@EJB
	private IDaoStock daoStock;
	
	@EJB
	private IUtilisateurService utilisateurService;
	
	private int id_prod;
	private String id_prod_stock;
	private Produit produit;
	private Produit prodConsomme;
	private Stock stk;
	private Consommation cons;
	private List<Produit> prodConsommes;
	private List<Stock> stocks;
	private List<Stock> stockDrop = new ArrayList<Stock>();
	private List<Consommation> consoDrop = new ArrayList<Consommation>();
	private String titre;
	private String description;
	private Utilisateur user = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	private Stock stock;
	private String quantite;
	private List<Categorie> categories;
	private List<SousCategorie> sous_categories;
	private List<Produit> produits;
	private List<Conservation> conservations;
	private List<Unite> unites;
	private Date date_peremption;
	private Integer duree_ext_stock ;
	private Double prix;
	private Double qte_initiale;		 
	private int id_cat;
	private String libelle_cat;
	private int id_sous_cat;
	private String libelle_sous_cat;
	private Conservation conservation;
	private int id_conserv;
	private String label;
	private Unite unite;
	private int id_unite;
	private String progressBarColor;
	List<Stock> listNbPerime;
	List<Stock> listNbPerimeBientot;
	private String booleanAlerteQuantitePerime;
	private String booleanAlerteQuantitePerimeBientot;
	private String labelNbProduit;
	private String labelNbPerime;
	private String labelNbPerimeBientot;
	private List<Stock> listFiltree = new ArrayList<Stock>();
	
	public StockBean ()
	{
		
	}
	
	@PostConstruct
	public void init()
	{
		categories = stockService.getAllCategorie();
		unites = stockService.getAllUnite();
		stocks = stockService.listerProdDispo(user.getIdUser());
		listNbPerime = definirNbPerime(stocks);
		listNbPerimeBientot = definirNbPerimeBientot(stocks);
		construireLabelnbProd();
		construireLabelNbPerimeBientot();
		construireLabelNbPerime();

	}
	
	
	public String choisirFiltreAfficheDansStock(int numeroDeFiltre)
	{
		String suite = null;
		if (numeroDeFiltre == 1)
		{
			listFiltree = stocks;
		}
		else if (numeroDeFiltre == 2)
		{
			listFiltree = listNbPerime;
		}
		else if (numeroDeFiltre == 3)
		{
			listFiltree = listNbPerimeBientot;
		}
		suite = "/archeVide.xhtml?faces-redirect=true";
		return suite;
	}
	
	public void construireLabelnbProd()
	{
		try 
		{
			if (stocks.size() == 1) { labelNbProduit = "Bienvenue, vous avez " + stocks.size() + " produit dans votre stock Food2Eat"; }
			else if (stocks.size() > 1) {labelNbProduit = "Bienvenue, vous avez " + stocks.size() + " produits dans votre stock Food2Eat";}
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public void construireLabelNbPerimeBientot()
	{
		try 
		{
			if (listNbPerimeBientot.size() == 1) { labelNbPerimeBientot = "Attention !! " + listNbPerimeBientot.size() + " produit se périme bientôt !"; }
			else if (listNbPerimeBientot.size() > 1) { labelNbPerimeBientot = "Attention !! " + listNbPerimeBientot.size() + " produits se périment bientôt !";}
		} catch (Exception e) { e.printStackTrace(); }
		
	}
	
	public void construireLabelNbPerime()
	{
		try 
		{
			if (listNbPerime.size() == 1) { labelNbPerime = "OUPS .. " + listNbPerime.size() + " produit est périmé :("; }
			else if (listNbPerime.size() > 1) { labelNbPerime = "OUPS .. " + listNbPerime.size() + " produits sont périmés :(";}
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public List<Stock> definirNbPerime(List<Stock> stocks)
	{
		listNbPerime = new ArrayList<Stock>();

		for (Stock stock : stocks)
		{
			if (joursRestants(stock.getIdProdStock()) <= 0)
			{
				listNbPerime.add(stock);
			}
		}
		
		if (listNbPerime.size() == 0) { booleanAlerteQuantitePerime = "false"; }
		else { booleanAlerteQuantitePerime = "true"; }	
		
		return listNbPerime;
	}
	
	
	public List<Stock> definirNbPerimeBientot(List<Stock> stocks)
	{
		listNbPerimeBientot = new ArrayList<Stock>();
		
		for (Stock stock : stocks)
		{
			if (joursRestants(stock.getIdProdStock()) <= user.getBatParamP())
			{
				if (joursRestants(stock.getIdProdStock()) > 0)
				{
					listNbPerimeBientot.add(stock);
				}
			}
		}
		
		if (listNbPerimeBientot.size() == 0) { booleanAlerteQuantitePerimeBientot = "false"; }
		else { booleanAlerteQuantitePerimeBientot = "true"; }	
		
		return listNbPerimeBientot;
	}
	
	
	public String dureeProgressBar(int id_prod_stock)
	{
		String pourcentage;
		Date date = stockService.getStockById(id_prod_stock).getDatePeremption();
		Date date2 = stockService.getStockById(id_prod_stock).getDateAjout();
		int diffInDays = (int)( (date.getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24) );
		int diffInDays2 = (int)( (date.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24) );
		
		int pourcent = 100 * diffInDays / diffInDays2;
		pourcent = 100 - pourcent;
		if (pourcent > 100) { pourcent = 100; }
		pourcentage = Integer.toString(pourcent);
		
		return pourcentage;
		
	}
	
	public String dureePeremption(int id_prod_stock)
	{
		String label;
		
		int diffInDays = joursRestants(id_prod_stock);
		
		if (diffInDays < 0) { label = "produit périmé"; }
		else if (diffInDays >= 365)
		{ 
			int diffInYears = diffInDays/365;
			if (diffInYears == 1) { label = "périme dans " + diffInYears + " an"; }
			else { label = "périme dans " + diffInYears + " ans"; }
		}
		else if (diffInDays >= 30) 
		{ 
			int diffInMonth = diffInDays/30;
			label = "périme dans " + diffInMonth + " mois";
		}
		else if (diffInDays == 1) { label = "périme demain"; }
		else if (diffInDays == 0) { label = "périme aujourd'hui"; }
		else { label = "périme dans " + diffInDays + " jours"; }
		
		return label;
	}
	
	public int joursRestants(int id_prod_stock)
	{
		Date date = stockService.getStockById(id_prod_stock).getDatePeremption();
		int diffInDays = (int)((new Date().getTime() -  date.getTime()) / (1000 * 60 * 60 * 24) );		
		diffInDays = -1*diffInDays;
		return diffInDays;
	}
	
	
	public String couleurProgressBar(int id_prod_stock)
	{
		String couleur = "#007AFF";
		int duree = joursRestants(id_prod_stock);
		int pourcent = Integer.parseInt(dureeProgressBar(id_prod_stock));
		
		if (pourcent >= 40) { couleur ="#686CE8"; }
		if (duree <= user.getBatParamE()) { couleur ="#FF4A2E"; }
		if (pourcent == 100) {couleur ="#696969"; }
		
		return couleur;	
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
        consommerProduit(stk.getIdProdStock());       
    }
	
	//methode permet d'ajouter un produit dans la table consommation
	public void consommerProduit(Integer id_prod_stock)
	{
		stockService.consommerProduitStock(id_prod_stock, 2, new Date(), 1.0, user.getIdUser());
	}

	
    public void ajouterProduit()  
	  {
		 Stock stock = new Stock();
		 unite = stockService.GetUniteByIDUnite(id_unite);
		 stock.setUnite(unite);
		 conservation = stockService.GetConservationByIDConservation(id_conserv);
		 stock.setConservation(conservation);
		 stock.setUtilisateur(user);
		 produit = stockService.GetProduitbyIDProduit(id_prod);
		 stock.setProduit(produit);
		 stock.setDatePeremption(date_peremption);
		 stock.setDureeExtStock(duree_ext_stock);
		 stock.setPrix(prix);
		 stock.setQteInitiale(qte_initiale);
		 stock.setDateAjout(new Date()); 
		 
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
		stockService.consommerProduitStock(1, 1, date , 50.00, user.getIdUser());
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


	public List<Unite> getUnites() {
		return unites;
	}


	public void setUnites(List<Unite> unites) {
		this.unites = unites;
	}


	public Unite getUnite() {
		return unite;
	}


	public void setUnite(Unite unite) {
		this.unite = unite;
	}


	public int getId_unite() {
		return id_unite;
	}


	public void setId_unite(int id_unite) {
		this.id_unite = id_unite;
	}

	public String getProgressBarColor() {
		return progressBarColor;
	}

	public void setProgressBarColor(String progressBarColor) {
		this.progressBarColor = progressBarColor;
	}

	public List<Stock> getListNbPerime() {
		return listNbPerime;
	}

	public void setListNbPerime(List<Stock> listNbPerime) {
		this.listNbPerime = listNbPerime;
	}

	public List<Stock> getListNbPerimeBientot() {
		return listNbPerimeBientot;
	}

	public void setListNbPerimeBientot(List<Stock> listNbPerimeBientot) {
		this.listNbPerimeBientot = listNbPerimeBientot;
	}

	public String getBooleanAlerteQuantitePerime() {
		return booleanAlerteQuantitePerime;
	}

	public void setBooleanAlerteQuantitePerime(String booleanAlerteQuantitePerime) {
		this.booleanAlerteQuantitePerime = booleanAlerteQuantitePerime;
	}

	public String getBooleanAlerteQuantitePerimeBientot() {
		return booleanAlerteQuantitePerimeBientot;
	}

	public void setBooleanAlerteQuantitePerimeBientot(String booleanAlerteQuantitePerimeBientot) {
		this.booleanAlerteQuantitePerimeBientot = booleanAlerteQuantitePerimeBientot;
	}

	public String getLabelNbProduit() {
		return labelNbProduit;
	}

	public void setLabelNbProduit(String labelNbProduit) {
		this.labelNbProduit = labelNbProduit;
	}

	public String getLabelNbPerime() {
		return labelNbPerime;
	}

	public void setLabelNbPerime(String labelNbPerime) {
		this.labelNbPerime = labelNbPerime;
	}

	public String getLabelNbPerimeBientot() {
		return labelNbPerimeBientot;
	}

	public void setLabelNbPerimeBientot(String labelNbPerimeBientot) {
		this.labelNbPerimeBientot = labelNbPerimeBientot;
	}

	public List<Stock> getListFiltree() {
		return listFiltree;
	}

	public void setListFiltree(List<Stock> listFiltree) {
		this.listFiltree = listFiltree;
	}

	
}
