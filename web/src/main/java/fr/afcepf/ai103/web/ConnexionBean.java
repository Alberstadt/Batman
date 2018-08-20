package fr.afcepf.ai103.web;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean(name="connexion")
@RequestScoped
public class ConnexionBean
{
	
	@EJB
	private IUtilisateurService utilisateurService;
	
	private FacesContext context;
	private Utilisateur user;
	private String pseudo;
	private String password;
	private String boolFiltre;
	
	public ConnexionBean()
	{
		context = FacesContext.getCurrentInstance();
	}
	
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
			System.out.println("passage case bientot perimeBientot");
			boolFiltre = "perimeBientot";
			context.getExternalContext().getSessionMap().put("boolFiltre", boolFiltre);

			suite = "/stockAmar.xhtml?faces-redirect=true";
			break;
		case 5:
			System.out.println("passage case bientot perime");
			boolFiltre = "perime";
			context.getExternalContext().getSessionMap().put("boolFiltre", boolFiltre);
			suite = "/stockAmar.xhtml?faces-redirect=true";
			break;
		}
		System.out.println("suite retourné : " + suite);
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
	
	
	
}
