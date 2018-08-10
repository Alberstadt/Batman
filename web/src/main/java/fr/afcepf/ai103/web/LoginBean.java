package fr.afcepf.ai103.web;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean(name="sessionMB")
@SessionScoped
public class LoginBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@EJB
	private IUtilisateurService utilisateurService;
	
	public LoginBean()
	{
		
	}
	
	private Utilisateur sessionUtilisateur;
	
	private String pseudo;
	private String password;
	private String nom;
	private String prenom;
	private String telephone;
	private String mail;
	private String adresse;
	private String codePostal;
	private String ville;
	private short sexe;
	private Date dateDeNaissance;
	private Date dateInscription;
	private String portrait;

	
	
	


	public Utilisateur getSessionUtilisateur() {
		return sessionUtilisateur;
	}


	public void setSessionUtilisateur(Utilisateur sessionUtilisateur) {
		this.sessionUtilisateur = sessionUtilisateur;
	}


	public String verifPassword()
	{
		sessionUtilisateur = utilisateurService.verifierMotDePasse(pseudo);
		System.out.println(sessionUtilisateur.getNom());
		String suite = null;
		
		if (sessionUtilisateur == null)
		{
			System.out.println("erreur de connexion");
		}
		else
		{
			suite = "/archeVide.xhtml?faces-redirect=true";
			System.out.println("connexion réussie");

		}
		return suite;
	}
	
	
	public String inscription()
	{
		sessionUtilisateur = new Utilisateur();
		sessionUtilisateur.setDate_inscription(new Date());
		sessionUtilisateur.setDate_naissance(dateDeNaissance);
		sessionUtilisateur.setLogin(pseudo);
		sessionUtilisateur.setMail(mail);
		sessionUtilisateur.setNom(nom);
		sessionUtilisateur.setPassword(password);
		sessionUtilisateur.setPrenom(prenom);
		sessionUtilisateur.setSexe(sexe);
		sessionUtilisateur.setTelephone(telephone);
		sessionUtilisateur.setPortrait(portrait);
		
		utilisateurService.inscription(sessionUtilisateur);
		System.out.println("methode inscription() LoginBean");
		String suite = "archeVidexhtml?faces-redirect=true";
		return suite;
	}
	
	public void Listener()
	{
		
		
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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
	
	
	
}
