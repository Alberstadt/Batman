package fr.afcepf.ai103.web;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean
@ViewScoped
public class GestionCompteBean implements Serializable 
{

	private static final long serialVersionUID = 1L;

	@EJB
	private IUtilisateurService utilisateurService;
	
	@ManagedProperty(value="#{sessionMB}")
	private LoginBean sessionMB;
	
	private Adresse adresse;
	private int bat_param_e;
	private int bat_param_p;

	@PostConstruct
	public void init() 
	{
		adresse = sessionMB.getAdressePrincipale(sessionMB.getSessionUtilisateur().getAdresses());	
		bat_param_p = sessionMB.getSessionUtilisateur().getBat_param_p();
		bat_param_e = sessionMB.getSessionUtilisateur().getBat_param_e();
	}

	
	public void updateBatParam(int bat_param_e, int bat_param_p, Utilisateur sessionUtilisateur)
	{
		sessionUtilisateur.setBat_param_e(bat_param_e);
		sessionUtilisateur.setBat_param_p(bat_param_p);
		utilisateurService.update(sessionUtilisateur);
	}
	
	
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public int getBat_param_e() {
		return bat_param_e;
	}

	public void setBat_param_e(int bat_param_e) {
		this.bat_param_e = bat_param_e;
	}

	public int getBat_param_p() {
		return bat_param_p;
	}

	public void setBat_param_p(int bat_param_p) {
		this.bat_param_p = bat_param_p;
	}


	public LoginBean getSessionMB() {
		return sessionMB;
	}


	public void setSessionMB(LoginBean sessionMB) {
		this.sessionMB = sessionMB;
	}
	
	
}
