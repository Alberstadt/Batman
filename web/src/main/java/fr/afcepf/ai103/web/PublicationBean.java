package fr.afcepf.ai103.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IAnnonceService;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean
@ViewScoped

public class PublicationBean {
	
	
	@EJB
	private IAnnonceService annonceService;
	
	private Utilisateur user = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	
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
	

}
