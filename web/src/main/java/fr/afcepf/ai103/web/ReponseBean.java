package fr.afcepf.ai103.web;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.MotifAnnulation;
import fr.afcepf.ai103.data.Reponse;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IAnnonceService;

@ManagedBean
@ViewScoped
public class ReponseBean {
	
	private List<Annonce> annonces;
	private List<Annonce> annoncesFinale;
	private List<Annonce> annoncesFavorites;
	private List<Annonce> demandeAnnonceAccepte; 
	//private List<Reponse> reponses;
	private List<Annonce> annoncesTotales;
	private Utilisateur user = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	private Annonce annonce;
	private Reponse reponse;
	private MotifAnnulation motifAnnulation;
	private Date dateDemande;
	private Date dateSelection;
	private Date dateAnnulation;
	private Date dateTransaction;
	private Integer id_publi;
	private Integer id_motif_annul;
	private Boolean flag;
	
	
	
	@EJB
	private IAnnonceService annonceService;
	
	
	@PostConstruct
	public void init()
	{
		
		
		annoncesTotales = annonces = annonceService.getAnnonces(user);
				
		annoncesFavorites = annonceService.recupDemandeEnCours(user);
		
		demandeAnnonceAccepte = annonceService.recupDemandeAnnonceAccepte(user);
		
		//afficherButtonLibelle( annonces);
		
	}
	
/*	public Boolean afficherButtonLibelle(List<Annonce> annonces)
	{
		Reponse reponse = new Reponse();
		if(reponse.getDateSelection() != null)
		{   flag = true;
			return flag;
		}
		else
		{   flag = false;
			return flag;
		}
	}  */
	

	public void choisirFiltreAfficheDansStock(int numeroDeFiltre)
	{
		if (numeroDeFiltre == 1)
		{
			annonces = annoncesTotales;
			
				flag = true;
			
		}
		else if (numeroDeFiltre == 2)
		{
			annonces = annoncesFavorites;
			flag = false;
		}
		
		else if (numeroDeFiltre == 3)
		{
			annonces = demandeAnnonceAccepte;
			flag = false;
		}
	}
	
	
	
	public void ajouterReponseAnnonce()
	{
		Reponse reponse = new Reponse();
		
		reponse.setUtilisateur(user);
		
		motifAnnulation = null;	
		reponse.setMotifAnnulation(null);
		reponse.setAnnonce(annonce);
		
		dateDemande = new Date();
		reponse.setDateDemande(dateDemande);
		reponse.setDateSelection(null);
		reponse.setDateAnnulation(null);
		reponse.setDateTransaction(null);
		
		annonceService.ajouterReponseAnnonce(reponse);		
		
	}
	
	
	
	
	
	public List<Annonce> getAnnonces() {
		return annonces;
	}


	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}


	public Utilisateur getUser() {
		return user;
	}


	public void setUser(Utilisateur user) {
		this.user = user;
	}


	public Annonce getAnnonce() {
		return annonce;
	}


	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}


	public MotifAnnulation getMotifAnnulation() {
		return motifAnnulation;
	}


	public void setMotifAnnulation(MotifAnnulation motifAnnulation) {
		this.motifAnnulation = motifAnnulation;
	}


	public Date getDateDemande() {
		return dateDemande;
	}


	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}


	public Date getDateSelection() {
		return dateSelection;
	}


	public void setDateSelection(Date dateSelection) {
		this.dateSelection = dateSelection;
	}


	public Date getDateAnnulation() {
		return dateAnnulation;
	}


	public void setDateAnnulation(Date dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
	}


	public Date getDateTransaction() {
		return dateTransaction;
	}


	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}


	public Integer getId_publi() {
		return id_publi;
	}


	public void setId_publi(Integer id_publi) {
		this.id_publi = id_publi;
	}


	public Integer getId_motif_annul() {
		return id_motif_annul;
	}


	public void setId_motif_annul(Integer id_motif_annul) {
		this.id_motif_annul = id_motif_annul;
	}


	public IAnnonceService getAnnonceService() {
		return annonceService;
	}


	public void setAnnonceService(IAnnonceService annonceService) {
		this.annonceService = annonceService;
	}

	public List<Annonce> getAnnoncesFinale() {
		return annoncesFinale;
	}

	public void setAnnoncesFinale(List<Annonce> annoncesFinale) {
		this.annoncesFinale = annoncesFinale;
	}

	public List<Annonce> getAnnoncesFavorites() {
		return annoncesFavorites;
	}

	public void setAnnoncesFavorites(List<Annonce> annoncesFavorites) {
		this.annoncesFavorites = annoncesFavorites;
	}


	public List<Annonce> getAnnoncesTotales() {
		return annoncesTotales;
	}


	public void setAnnoncesTotales(List<Annonce> annoncesTotales) {
		this.annoncesTotales = annoncesTotales;
	}


	public List<Annonce> getDemandeAnnonceAccepte() {
		return demandeAnnonceAccepte;
	}


	public void setDemandeAnnonceAccepte(List<Annonce> demandeAnnonceAccepte) {
		this.demandeAnnonceAccepte = demandeAnnonceAccepte;
	}


	public Boolean getFlag() {
		return flag;
	}


	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Reponse getReponse() {
		return reponse;
	}

	public void setReponse(Reponse reponse) {
		this.reponse = reponse;
	}

	
	
	
}
