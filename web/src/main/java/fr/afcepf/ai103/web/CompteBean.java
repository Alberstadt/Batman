package fr.afcepf.ai103.web;

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
public class CompteBean
{
	private Utilisateur user = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	private List<Adresse> adresses = user.getAdresses();
	private Adresse adrPrincipale;
	private String voirie;
	private String codePostal;
	private String ville;
	private boolean chkBoxAdrPrinc;
	
	@EJB
	private IUtilisateurService utilisateurService;
	
	public CompteBean(){}
	
	public void enregistrer()
	{
		utilisateurService.update(user);
	}
	
	public void ajouterAdresse()
	{
		if(!voirie.equals(null) && !codePostal.equals(null) && !ville.equals(null))
		{
			Adresse nvlAdresse = new Adresse();
			nvlAdresse.setVoirie(voirie);
			nvlAdresse.setCodePostal(codePostal);
			nvlAdresse.setVille(ville);
			nvlAdresse.setDateAjoutAdr(new Date());
			if(chkBoxAdrPrinc == true)
			{
				nvlAdresse.setAdrPrincipale((short) 1);
				int indAdrPrincipale = adresses.indexOf(adrPrincipale);
				adresses.get(indAdrPrincipale).setAdrPrincipale((short) 0);
			}
			else
			{
				nvlAdresse.setAdrPrincipale((short) 0);
			}
			adresses.add(nvlAdresse);
			user.setAdresses(adresses);
			enregistrer();
			adrPrincipale = nvlAdresse;
		}
		voirie = null;
		codePostal = null;
		ville = null;
		chkBoxAdrPrinc = false;
	}
	
	public Adresse getAdrPrincipale()
	{
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
		return ville;
	}

	public void setVille(String ville)
	{
		this.ville = ville;
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
