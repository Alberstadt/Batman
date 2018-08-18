package fr.afcepf.ai103.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IAdresseService;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@EJB
	private IUtilisateurService utilisateurService;
	
	@EJB
	private IAdresseService adresseService;
		
	
	public LoginBean()
	{
	
	}
	
	private Utilisateur sessionUtilisateur;
	private boolean chckboxAdr_principale = false;
	private String longitude;
	private String latitude;
	private String voirie;
	private String codePostal;
	private String ville;
	private String pseudo;
	private String password;
	private String passwordConfirm;
	private String nom;
	private String prenom;
	private String telephone;
	private String mail;
	private short sexe;
	private Date dateDeNaissance;
	private Date dateInscription = new Date();
	private String portrait;


	public String deconnexion()
	{
		String suite = "/login.xhtml?faces-redirect=true";
		return suite;
	}
	
	public Utilisateur getSessionUtilisateur() {
		return sessionUtilisateur;
	}


	public void setSessionUtilisateur(Utilisateur sessionUtilisateur) {
		this.sessionUtilisateur = sessionUtilisateur;
	}


	public String verifPassword()
	{
		sessionUtilisateur = utilisateurService.verifierMotDePasse(pseudo,password);
		String suite = null;
		
		if (sessionUtilisateur == null)
		{
			
		}
		else
		{
			suite = "/archeVide.xhtml?faces-redirect=true";

		}
		return suite;
	}
	
	
	public String inscription()
	{
		String suite = null;
		
		
		if (!password.equals(passwordConfirm))
		{
		}
		else
		{
			sessionUtilisateur = new Utilisateur();
			sessionUtilisateur.setPassword(password);
			sessionUtilisateur.setDateInscription(dateInscription);
			sessionUtilisateur.setDateNaissance(dateDeNaissance);
			sessionUtilisateur.setLogin(pseudo);
			sessionUtilisateur.setMail(mail);
			sessionUtilisateur.setNom(nom);
			sessionUtilisateur.setPassword(password);
			sessionUtilisateur.setPrenom(prenom);
			sessionUtilisateur.setSexe(sexe);
			sessionUtilisateur.setTelephone(telephone);
			sessionUtilisateur.setPortrait(portrait);
			utilisateurService.inscription(sessionUtilisateur);
			suite = "archeVide.xhtml?faces-redirect=true";

		}
		
		return suite;
	}

	
	public void ajouterAdresse()
	{
	System.out.println("BEAN passage ajouterAdresse");
	System.out.println("BEAN checkbox : "+ chckboxAdr_principale);
	
		if(chckboxAdr_principale)
		{
			Adresse Adresse = getAdressePrincipale(sessionUtilisateur.getAdresses());
			Adresse.setCodePostal(codePostal);
			Adresse.setDateAjoutAdr(dateInscription);
			Adresse.setLatitude(latitude);
			Adresse.setLongitude(longitude);
			Adresse.setVille(ville);
			Adresse.setVoirie(voirie);
			// update
			System.out.println("BEAN boucle maj adresse");
			adresseService.majAdresse(Adresse);
		}
		else
		{
			Adresse newAdresse = new Adresse();
			newAdresse.setCodePostal(codePostal);
			newAdresse.setDateAjoutAdr(dateInscription);
			newAdresse.setLatitude(latitude);
			newAdresse.setLongitude(longitude);
			newAdresse.setVille(ville);
			newAdresse.setVoirie(voirie);
			newAdresse.setAdrPrincipale((short)0);
			//create
			System.out.println("BEAN boucle ajouter une adresse");
			//utilisateurService.ajouterAdresse(newAdresse,sessionUtilisateur);
		}

	}
	
	public Adresse getAdressePrincipale(List<Adresse> list)
	{
		System.out.println(list);
		
		for (Adresse adresse : list)
		{
			if (adresse.getAdrPrincipale() == 1)
			{
				return adresse;
			}
		}
		return null;

	}


	public Utilisateur updateMail()
	{
		sessionUtilisateur.setMail(mail);
		return utilisateurService.update(sessionUtilisateur);
	}
	
	public Utilisateur updateTelephone()
	{
		sessionUtilisateur.setTelephone(telephone);
		return sessionUtilisateur = utilisateurService.update(sessionUtilisateur);
	}
	
	public Utilisateur updateDateNaissance()
	{
		sessionUtilisateur.setMail(mail);
		return sessionUtilisateur = utilisateurService.update(sessionUtilisateur);

	}
	
	
	public IUtilisateurService getUtilisateurService() {
		return utilisateurService;
	}

	public void setUtilisateurService(IUtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getSexe() {
		return sexe;
	}

	public void setSexe(short sexe) {
		this.sexe = sexe;
	}
	

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Date getDateInscription() {
		return dateInscription;
	}


	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}


	public IAdresseService getAdresseService() {
		return adresseService;
	}


	public void setAdresseService(IAdresseService adresseService) {
		this.adresseService = adresseService;
	}


	public boolean isChckboxAdr_principale() {
		return chckboxAdr_principale;
	}


	public void setChckboxAdr_principale(boolean chckboxAdr_principale) {
		this.chckboxAdr_principale = chckboxAdr_principale;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getVoirie() {
		return voirie;
	}


	public void setVoirie(String voirie) {
		this.voirie = voirie;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}