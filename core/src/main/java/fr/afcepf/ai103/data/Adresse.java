package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the adresse database table.
 * 
 */
@Entity
@Table(name="adresse")
@NamedQuery(name="Adresse.findAll", query="SELECT a FROM Adresse a")
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_adresse")
	private int idAdresse;

	@Column(name="adr_principale")
	private short adrPrincipale;

	@Column(name="code_postal")
	private String codePostal;

	@Temporal(TemporalType.DATE)
	@Column(name="date_ajout_adr")
	private Date dateAjoutAdr;

	@Temporal(TemporalType.DATE)
	@Column(name="date_retrait_adr")
	private Date dateRetraitAdr;

	private String latitude;

	private String longitude;

	private String ville;

	private String voirie;

	//bi-directional many-to-one association to Annonce
	@OneToMany(mappedBy="adresse")
	private List<Annonce> annonces;

	//bi-directional many-to-many association to Utilisateur
	@ManyToMany(mappedBy="adresses")
	private List<Utilisateur> utilisateurs;

	public Adresse() {
	}

	public int getIdAdresse() {
		return this.idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public short getAdrPrincipale() {
		return this.adrPrincipale;
	}

	public void setAdrPrincipale(short adrPrincipale) {
		this.adrPrincipale = adrPrincipale;
	}

	public String getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public Date getDateAjoutAdr() {
		return this.dateAjoutAdr;
	}

	public void setDateAjoutAdr(Date dateAjoutAdr) {
		this.dateAjoutAdr = dateAjoutAdr;
	}

	public Date getDateRetraitAdr() {
		return this.dateRetraitAdr;
	}

	public void setDateRetraitAdr(Date dateRetraitAdr) {
		this.dateRetraitAdr = dateRetraitAdr;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getVoirie() {
		return this.voirie;
	}

	public void setVoirie(String voirie) {
		this.voirie = voirie;
	}

	public List<Annonce> getAnnonces() {
		return this.annonces;
	}

	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}

	public Annonce addAnnonce(Annonce annonce) {
		getAnnonces().add(annonce);
		annonce.setAdresse(this);

		return annonce;
	}

	public Annonce removeAnnonce(Annonce annonce) {
		getAnnonces().remove(annonce);
		annonce.setAdresse(null);

		return annonce;
	}

	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}