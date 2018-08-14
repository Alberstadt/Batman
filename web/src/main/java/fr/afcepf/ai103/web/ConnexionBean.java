package fr.afcepf.ai103.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean(name="connexion")
@SessionScoped
public class ConnexionBean
{
	@ManagedProperty(value="#{session}")
	private SessionBean session;
	
	@EJB
	private IUtilisateurService utilisateurService;
	
	private Utilisateur user;
	private String pseudo;
	private String password;
	
	public ConnexionBean() {}
	
	public String verifPassword()
	{
		user = utilisateurService.verifierMotDePasse(pseudo,password);
		String suite = null;
		
		if (user == null)
		{
			
		}
		else
		{
			session.setUser(user);
			suite = "/archeVide.xhtml?faces-redirect=true";
		}
		return suite;
	}
	
	public String deconnexion()
	{
		String suite = "/login.xhtml?faces-redirect=true";
		return suite;
	}
	
	public SessionBean getSession()
	{
		return session;
	}

	public void setSession(SessionBean session)
	{
		this.session = session;
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
