package fr.afcepf.ai103.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Contact;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IContactService;

@ManagedBean
@SessionScoped
public class MapBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Utilisateur user = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	private String coordoUser;
	private String portraitUser;
	private String pseudoUser;
	private String coordoFF;
	
	@EJB
	IContactService contactService;
	
	public MapBean() {}
	
	
	@PostConstruct
	public void init()
	{
		portraitUser = user.getPortrait();
		System.out.println("portraitUser : " + portraitUser);
		pseudoUser = user.getLogin();
		System.out.println("pseudoUser : " + pseudoUser);
		coordoUser = recuCoordoUser();
		System.out.println("coordoUser : " + coordoUser);
		coordoFF = recupCoordoFF();
		System.out.println("coordoFF : " + coordoFF);
		
	}
	
	public String recupCoordoFF()
	{		

		List<Contact> ff1 = contactService.recupererListeDeMesFoodF(user.getIdUser());

		List<Adresse> adrFF = new ArrayList<Adresse>();
		
		for (Contact ct : ff1)
		{
			List<Adresse> ad = ct.getUtilisateur2().getAdresses();
			
			for (Adresse adresse : ad)
			{
				if(adresse.getAdrPrincipale() == 1)
				{
					adrFF.add(adresse);
				}
			}	
		}
		int i = 0;
		for (Adresse adresse : adrFF)
		{
			if (i != 0)
			{
				coordoFF = coordoFF + ",";
			}
			coordoFF = coordoFF + "{ lat: " + adresse.getLatitude() + ", lng: " + adresse.getLongitude() + ", title: " + "toto" +"}";
			i++;
		}
			
		return coordoFF;
	}
	
	
	public String recuCoordoUser()
	{
		List<Adresse> userAdr = user.getAdresses();
		
		for (Adresse adr : userAdr)
		{
			if(adr.getAdrPrincipale() == 1)
			{
				coordoUser = adr.getLatitude() + "," + adr.getLongitude();
			}
		}
		return coordoUser;
		
	}		
	
	
	
	public String getCoordoUser() {
		return coordoUser;
	}

	public void setCoordoUser(String coordoUser) {
		this.coordoUser = coordoUser;
	}

	public String getPortraitUser() {
		return portraitUser;
	}

	public void setPortraitUser(String portraitUser) {
		this.portraitUser = portraitUser;
	}

	public String getPseudoUser() {
		return pseudoUser;
	}

	public void setPseudoUser(String pseudoUser) {
		this.pseudoUser = pseudoUser;
	}

	public String getCoordoFF() {
		return coordoFF;
	}

	public void setCoordoFF(String coordoFF) {
		this.coordoFF = coordoFF;
	}
	
}
