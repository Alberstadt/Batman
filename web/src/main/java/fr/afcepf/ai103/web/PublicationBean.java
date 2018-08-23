package fr.afcepf.ai103.web;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Reponse;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IAnnonceService;


@ManagedBean
@ViewScoped

public class PublicationBean {
	
	
	@EJB
	private IAnnonceService annonceService;
		
	@ManagedProperty(value="#{contactBean}")
	private ContactBean contactBean;
	
	
	private Annonce annonce;
		
	private List <Reponse> reponses; 
			
	private Utilisateur user = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		
	private List<Adresse> adresses;
	
	private Adresse adrPrincipale;
	
	private int nombreReponse;	
	private List<Annonce> annonces;
	
	
	
	public PublicationBean() {
		
	}
	
	
	@PostConstruct
	public void init()
	{
		annonces = annonceService.getAnnonceByUserId(user.getIdUser());
	}
	
		
	public void updateDateDeRetraitAnnonce(int id_publi)
	
	{
		annonces = annonceService.getAnnonceByUserId(user.getIdUser());
		
		for (Annonce annonce : annonces)
			
		{
			if (annonce.getIdPubli() == id_publi)
			{
				annonce.setDateRetrait(new Date());;
				annonceService.update(annonce);							
			}
			
			else {
				 
			}
			
		}
		
	}
	
	public int getNombreReponseAnnonce(int id_publi) {

		nombreReponse= annonceService.getNombreReponseByIdPubli(id_publi);
		
		return nombreReponse;
	}
	
	
	public void getListeReponseByIdPubli(Integer id_publi)
	{
		reponses = annonceService.getListeReponseByIdPubli(id_publi);				
	} 
	
	
	public void envoyerDemandeDeFoodFriend(Utilisateur user1, Utilisateur user2) {
		
		contactBean.envoyerDemandeDeFoodFriend(user1, user2);
		
	}
	
	
	public String getAdrPrincipale(Utilisateur utilisateur)
	{
		
		
		adresses = utilisateur.getAdresses();
		for (Adresse adresse : adresses)
		{
			if(adresse.getAdrPrincipale() == 1)
			{
				adrPrincipale = adresse;
			}
		}
		return adrPrincipale.getVille();
	}
	
	
	public void updateDateDeSelectionAnnonce(int idReponse)
	
	{
			
		for (Reponse reponse : reponses)
			
		{
			if (reponse.getIdReponse() == idReponse)
			{
				reponse.setDateSelection(new Date());
				annonceService.update(reponse);								
			}
			
			else {
				 
			}
			
		}
	
	}
	 	
	public IAnnonceService getAnnonceService() {
		return annonceService;
	}


	public int getNombreReponse() {
		return nombreReponse;
	}


	public void setNombreReponse(int nombreReponse) {
		this.nombreReponse = nombreReponse;
	}


	public void setAnnonceService(IAnnonceService annonceService) {
		this.annonceService = annonceService;
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


	public List<Reponse> getReponses() {
		return reponses;
	}


	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}


	public ContactBean getContactBean() {
		return contactBean;
	}


	public void setContactBean(ContactBean contactBean) {
		this.contactBean = contactBean;
	}
		

}
