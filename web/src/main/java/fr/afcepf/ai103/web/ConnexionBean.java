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
	//@ManagedProperty(value="#{sessionBean}")
	//private SessionBean session;
	
	@EJB
	private IUtilisateurService utilisateurService;
	
	private FacesContext context;
	private Utilisateur user;
	private String pseudo;
	private String password;
	
	public ConnexionBean()
	{
		context = FacesContext.getCurrentInstance();
	}
	
	public String login()
	{
		user = utilisateurService.verifierMotDePasse(pseudo,password);
		
		if (user == null)
		{
            context.addMessage(null, new FacesMessage("Erreur d'identifiant et/ou de mot de passe"));
            pseudo = null;
            password = null;
            return null;
		}
		else
		{
			//session.setUser(user);
			context.getExternalContext().getSessionMap().put("user", user);
			return "/archeVide.xhtml?faces-redirect=true";
		}
	}
	
	public String logout()
	{
		context.getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
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
