package fr.afcepf.ai103.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Utilisateur;

@ManagedBean
@ViewScoped
public class CompteBean
{
	private Utilisateur user = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	private Adresse adrPrincipale;
	private String voirie;
	private String codePostal;
	private String Ville;
	private boolean chkBoxAdrPrinc;
	
	public CompteBean(){}
	
	public void enregistrer()
	{
		
	}
	
	public void ajouterAdresse()
	{
		
	}
	
	public Adresse getAdrPrincipale()
	{
		List<Adresse> adresses = user.getAdresses();
		for (Adresse adresse : adresses)
		{
			if(adresse.getAdrPrincipale() == 1)
			{
				adrPrincipale = adresse;
			}
		}
		return adrPrincipale;
	}

	public void setAdrPrincipale(Adresse adrPrincipale)
	{
		this.adrPrincipale = adrPrincipale;
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
		return Ville;
	}

	public void setVille(String ville)
	{
		Ville = ville;
	}

	public boolean isChkBoxAdrPrinc()
	{
		return chkBoxAdrPrinc;
	}

	public void setChkBoxAdrPrinc(boolean chkBoxAdrPrinc)
	{
		this.chkBoxAdrPrinc = chkBoxAdrPrinc;
	}
}
