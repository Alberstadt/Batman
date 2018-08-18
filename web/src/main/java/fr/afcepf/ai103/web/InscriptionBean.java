package fr.afcepf.ai103.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean
@ViewScoped
public class InscriptionBean
{
	private String nom;
	private String prenom;
	private String pseudo;
	private String password;
	private String passVerif;
	private String voirie;
	private String codePostal;
	private String ville;
	private String mail;
	
	@EJB
	private IUtilisateurService utilisateurService;
	
	public InscriptionBean(){}

	public String enregistrer()
	{
		if(password.equals(passVerif))
		{
			Adresse adresse = new Adresse();
			adresse.setVoirie(voirie);
			adresse.setCodePostal(codePostal);
			adresse.setVille(ville);
			adresse.setAdrPrincipale((short) 1);
			adresse.setDateAjoutAdr(new Date());
			List<Adresse> adresses = new ArrayList<Adresse>();
			adresses.add(adresse);
			Utilisateur user = new Utilisateur();
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setLogin(pseudo);
			user.setPassword(password);
			user.setMail(mail);
			user.setDateInscription(new Date());
			user.setAdresses(adresses);
			utilisateurService.inscription(user);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", utilisateurService.GetLastUser());
			return "/archeVide.xhtml?faces-redirect=true";
		}
		else
		{
			return null;
		}
	}
	
	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getPrenom()
	{
		return prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
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

	public String getPassVerif()
	{
		return passVerif;
	}

	public void setPassVerif(String passVerif)
	{
		this.passVerif = passVerif;
	}

	public String getVoirie()
	{
		return voirie;
	}

	public void setVoirie(String voirie)
	{
		this.voirie = voirie;
	}

	public String getCodePostal()
	{
		return codePostal;
	}

	public void setCodePostal(String codePostal)
	{
		this.codePostal = codePostal;
	}

	public String getVille()
	{
		return ville;
	}

	public void setVille(String ville)
	{
		this.ville = ville;
	}

	public String getMail()
	{
		return mail;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}
}
