package fr.afcepf.ai103.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{session}")
	private SessionBean session;
	
	@EJB
	private IUtilisateurService utilisateurService;

	public LoginBean(){}
	
	private Utilisateur user;
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
	
	
	public String inscription()
	{
		String suite = null;
		
		
		if (!password.equals(passwordConfirm))
		{
		}
		else
		{
			user = new Utilisateur();
			user.setPassword(password);
			user.setDate_inscription(dateInscription);
			user.setDate_naissance(dateDeNaissance);
			user.setLogin(pseudo);
			user.setMail(mail);
			user.setNom(nom);
			user.setPassword(password);
			user.setPrenom(prenom);
			user.setSexe(sexe);
			user.setTelephone(telephone);
			user.setPortrait(portrait);
			utilisateurService.inscription(user);
			suite = "archeVide.xhtml?faces-redirect=true";

		}
		
		return suite;
	}

	
	public void ajouterAdresse()
	{
	/*
	System.out.println("BEAN passage ajouterAdresse");
	System.out.println("BEAN checkbox : "+ chckboxAdr_principale);
	
		if(chckboxAdr_principale)
		{
			Adresse Adresse = getAdressePrincipale(sessionUtilisateur.getAdresses());
			Adresse.setCode_postal(codePostal);
			Adresse.setDate_ajout_adr(dateInscription);
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
			newAdresse.setCode_postal(codePostal);
			newAdresse.setDate_ajout_adr(dateInscription);
			newAdresse.setLatitude(latitude);
			newAdresse.setLongitude(longitude);
			newAdresse.setVille(ville);
			newAdresse.setVoirie(voirie);
			newAdresse.setAdr_principale((short)0);
			//create
			System.out.println("BEAN boucle ajouter une adresse");
			utilisateurService.ajouterAdresse(newAdresse,sessionUtilisateur);
		}
	*/
	}
	
	public Adresse getAdressePrincipale(List<Adresse> list)
	{
		System.out.println(list);
		
		for (Adresse adresse : list)
		{
			if (adresse.getAdr_principale() == 1)
			{
				return adresse;
			}
		}
		return null;
	}


	public Utilisateur updateMail()
	{
		user.setMail(mail);
		return utilisateurService.update(user);
	}
	
	public Utilisateur updateTelephone()
	{
		user.setTelephone(telephone);
		return user = utilisateurService.update(user);
	}
	
	public Utilisateur updateDateNaissance()
	{
		user.setMail(mail);
		return user = utilisateurService.update(user);

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
}
