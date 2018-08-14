package fr.afcepf.ai103.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Stock;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IStockService;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean
@SessionScoped
public class FormAnnonceBean implements Serializable
	{

		private static final long serialVersionUID = 1L;
		
		@EJB
		private IStockService stockService;
		
		@EJB
		private IUtilisateurService utilisateurService;
		
		@ManagedProperty(value="#{sessionMB}")
		private LoginBean sessionMB;
		
		private String libelle;
		private Double qte_stock;
		private Double qte_publi;
		private String description;
		private String unite;
		private Stock stock;
		private Utilisateur user;
		private Integer id_prod_stock = 3;
		private Integer id_adresse;
		private List<Adresse> adresses;
		
		
		public FormAnnonceBean(){}
		
		@PostConstruct
		public void init()
		{
			this.user = this.sessionMB.getSessionUtilisateur();
			this.stock = stockService.getStockById(id_prod_stock);
			this.libelle = stock.getProduit().getLibelle_prod();
			this.qte_stock = stockService.calculerQteReelle(id_prod_stock);
			this.unite = stock.getUnite().getLibelle_unite();
			this.adresses = utilisateurService.recupererAdresses(user.getId_user());	
		}
				
		public void validerAnnonce()
		{
			Adresse adresse = stockService.recupererAdresseById(this.id_adresse);
			
			Annonce annonce = new Annonce();

			annonce.setStock(this.stock);
			annonce.setUtilisateur(user);
			annonce.setAdresse(adresse);
			annonce.setTitre(this.libelle);
			annonce.setDescription(this.description);
			annonce.setQte_publi(this.qte_publi);
			annonce.setDate_publication(new Date());
			annonce.setMotifRetrait(null);
			annonce.setDate_retrait(null);
			
			stockService.partagerProduit(annonce);
		}

		public String getLibelle()
		{
			return libelle;
		}

		public void setLibelle(String libelle)
		{
			this.libelle = libelle;
		}

		public String getDescription()
		{
			return description;
		}

		public void setDescription(String description)
		{
			this.description = description;
		}

		public String getUnite()
		{
			return unite;
		}

		public void setUnite(String unite)
		{
			this.unite = unite;
		}

		public Stock getStock()
		{
			return stock;
		}

		public void setStock(Stock stock)
		{
			this.stock = stock;
		}

		public Utilisateur getUser()
		{
			return user;
		}

		public void setUser(Utilisateur user)
		{
			this.user = user;
		}

		public Integer getId_prod_stock()
		{
			return id_prod_stock;
		}

		public void setId_prod_stock(Integer id_prod_stock)
		{
			this.id_prod_stock = id_prod_stock;
		}

		public Integer getId_adresse()
		{
			return id_adresse;
		}

		public void setId_adresse(Integer id_adresse)
		{
			this.id_adresse = id_adresse;
		}

		public List<Adresse> getAdresses()
		{
			return adresses;
		}

		public void setAdresses(List<Adresse> adresses)
		{
			this.adresses = adresses;
		}

		public Double getQte_stock()
		{
			return qte_stock;
		}

		public void setQte_stock(Double qte_stock)
		{
			this.qte_stock = qte_stock;
		}

		public Double getQte_publi()
		{
			return qte_publi;
		}

		public void setQte_publi(Double qte_publi)
		{
			this.qte_publi = qte_publi;
		}

		public LoginBean getSessionMB()
		{
			return sessionMB;
		}

		public void setSessionMB(LoginBean sessionMB)
		{
			this.sessionMB = sessionMB;
		}
}
