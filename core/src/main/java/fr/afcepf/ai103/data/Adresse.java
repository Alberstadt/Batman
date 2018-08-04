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
@NamedQuery(name="Adresse.findAll", query="SELECT a FROM Adresse a")
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_adresse;

	private short adr_principale;

	private String code_postal;

	@Temporal(TemporalType.DATE)
	private Date date_ajout_adr;

	@Temporal(TemporalType.DATE)
	private Date date_retrait_adr;

	private String latitude;

	private String longitude;

	private String ville;

	private String voirie;

	//bi-directional many-to-many association to Utilisateur
	@ManyToMany(mappedBy="adresses")
	private List<Utilisateur> utilisateurs;

	public Adresse() {
	}

	public int getId_adresse() {
		return this.id_adresse;
	}

	public void setId_adresse(int id_adresse) {
		this.id_adresse = id_adresse;
	}

	public short getAdr_principale() {
		return this.adr_principale;
	}

	public void setAdr_principale(short adr_principale) {
		this.adr_principale = adr_principale;
	}

	public String getCode_postal() {
		return this.code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public Date getDate_ajout_adr() {
		return this.date_ajout_adr;
	}

	public void setDate_ajout_adr(Date date_ajout_adr) {
		this.date_ajout_adr = date_ajout_adr;
	}

	public Date getDate_retrait_adr() {
		return this.date_retrait_adr;
	}

	public void setDate_retrait_adr(Date date_retrait_adr) {
		this.date_retrait_adr = date_retrait_adr;
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

	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}