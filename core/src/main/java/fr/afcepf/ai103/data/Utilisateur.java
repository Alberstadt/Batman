package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_user;

	@Temporal(TemporalType.DATE)
	private Date date_desinscription;

	@Temporal(TemporalType.DATE)
	private Date date_inscription;

	@Temporal(TemporalType.DATE)
	private Date date_naissance;

	private String login;

	private String mail;

	private String nom;

	private String password;

	private String portrait;

	private String prenom;

	private short sexe;

	private String telephone;

	//bi-directional many-to-one association to Annonce
	@OneToMany(mappedBy="utilisateur")
	private List<Annonce> annonces;

	//bi-directional many-to-one association to Contact
	@OneToMany(mappedBy="utilisateur1")
	private List<Contact> contacts1;

	//bi-directional many-to-one association to Contact
	@OneToMany(mappedBy="utilisateur2")
	private List<Contact> contacts2;

	//bi-directional many-to-one association to Evaluation
	@OneToMany(mappedBy="utilisateur")
	private List<Evaluation> evaluations;

	//bi-directional many-to-one association to Reponse
	@OneToMany(mappedBy="utilisateur")
	private List<Reponse> reponses;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="utilisateur")
	private List<Stock> stocks;

	//bi-directional many-to-many association to Adresse
	@ManyToMany
	@JoinTable(
		name="resider"
		, joinColumns={
			@JoinColumn(name="Id_user")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Id_adresse")
			}
		)
	private List<Adresse> adresses;

	public Utilisateur() {
	}

	public int getId_user() {
		return this.id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public Date getDate_desinscription() {
		return this.date_desinscription;
	}

	public void setDate_desinscription(Date date_desinscription) {
		this.date_desinscription = date_desinscription;
	}

	public Date getDate_inscription() {
		return this.date_inscription;
	}

	public void setDate_inscription(Date date_inscription) {
		this.date_inscription = date_inscription;
	}

	public Date getDate_naissance() {
		return this.date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPortrait() {
		return this.portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public short getSexe() {
		return this.sexe;
	}

	public void setSexe(short sexe) {
		this.sexe = sexe;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Annonce> getAnnonces() {
		return this.annonces;
	}

	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}

	public Annonce addAnnonce(Annonce annonce) {
		getAnnonces().add(annonce);
		annonce.setUtilisateur(this);

		return annonce;
	}

	public Annonce removeAnnonce(Annonce annonce) {
		getAnnonces().remove(annonce);
		annonce.setUtilisateur(null);

		return annonce;
	}

	public List<Contact> getContacts1() {
		return this.contacts1;
	}

	public void setContacts1(List<Contact> contacts1) {
		this.contacts1 = contacts1;
	}

	public Contact addContacts1(Contact contacts1) {
		getContacts1().add(contacts1);
		contacts1.setUtilisateur1(this);

		return contacts1;
	}

	public Contact removeContacts1(Contact contacts1) {
		getContacts1().remove(contacts1);
		contacts1.setUtilisateur1(null);

		return contacts1;
	}

	public List<Contact> getContacts2() {
		return this.contacts2;
	}

	public void setContacts2(List<Contact> contacts2) {
		this.contacts2 = contacts2;
	}

	public Contact addContacts2(Contact contacts2) {
		getContacts2().add(contacts2);
		contacts2.setUtilisateur2(this);

		return contacts2;
	}

	public Contact removeContacts2(Contact contacts2) {
		getContacts2().remove(contacts2);
		contacts2.setUtilisateur2(null);

		return contacts2;
	}

	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public Evaluation addEvaluation(Evaluation evaluation) {
		getEvaluations().add(evaluation);
		evaluation.setUtilisateur(this);

		return evaluation;
	}

	public Evaluation removeEvaluation(Evaluation evaluation) {
		getEvaluations().remove(evaluation);
		evaluation.setUtilisateur(null);

		return evaluation;
	}

	public List<Reponse> getReponses() {
		return this.reponses;
	}

	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}

	public Reponse addRepons(Reponse repons) {
		getReponses().add(repons);
		repons.setUtilisateur(this);

		return repons;
	}

	public Reponse removeRepons(Reponse repons) {
		getReponses().remove(repons);
		repons.setUtilisateur(null);

		return repons;
	}

	public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setUtilisateur(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setUtilisateur(null);

		return stock;
	}

	public List<Adresse> getAdresses() {
		return this.adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

}