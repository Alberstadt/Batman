package fr.afcepf.ai103.web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Contact;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IAdresseService;
import fr.afcepf.ai103.service.IContactService;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean(name="connexion")
@RequestScoped
public class ConnexionBean
{
	
	@EJB
	private IUtilisateurService utilisateurService;
	
	@EJB
	private IContactService contactService;
	
	@EJB
	private IAdresseService adresseService;
	
	private FacesContext context = FacesContext.getCurrentInstance();
	private Utilisateur user;
	private String pseudo;
	private String password;
	private String boolFiltre;
	private String insertJSMap = "true";
	
	public ConnexionBean() {}
	
	public String login()
	{
		user = utilisateurService.verifierMotDePasse(pseudo,password);
		
		if (user == null)
		{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", " Mauvais identifiant et/ou mot de passe"));
            pseudo = null;
            password = null;
            return null;
		}
		else
		{
			context.getExternalContext().getSessionMap().put("user", user);
			boolFiltre = "stocks";
			context.getExternalContext().getSessionMap().put("boolFiltre", boolFiltre);
			
			
			return "/archeVide.xhtml?faces-redirect=true";
		}
	}
	


	
	public String logout()
	{
		context.getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}

	
	public String choisirFiltreAfficheDansStock(int numeroDeFiltre)
	{
		System.out.println("passage stockBean");
		String suite = null;
		switch (numeroDeFiltre)
		{
		case 4:
			boolFiltre = "perimeBientot";
			context.getExternalContext().getSessionMap().put("boolFiltre", boolFiltre);

			suite = "/stockAmar.xhtml?faces-redirect=true";
			break;
		case 5:
			boolFiltre = "perime";
			context.getExternalContext().getSessionMap().put("boolFiltre", boolFiltre);
			suite = "/stockAmar.xhtml?faces-redirect=true";
			break;
		case 6:
			boolFiltre = "none";
			context.getExternalContext().getSessionMap().put("boolFiltre", boolFiltre);
			suite = "/stockAmar.xhtml?faces-redirect=true";
		}
		return suite;
	}
	
	public Utilisateur getUser()
	{
		return user;
	}

	public void setUser(Utilisateur user)
	{
		this.user = user;
	}

	public String getPseudo()
	{
		return pseudo;
	}

	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getBoolFiltre() {
		return boolFiltre;
	}

	public void setBoolFiltre(String boolFiltre) {
		this.boolFiltre = boolFiltre;
	}

	public String getInsertJSMap() {
		return insertJSMap;
	}

	public void setInsertJSMap(String insertJSMap) {
		this.insertJSMap = insertJSMap;
	}

}
