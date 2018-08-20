package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the annonce database table.
 * 
 */
@Entity
@Table(name="annonce")
@NamedQuery(name="Annonce.findAll", query="SELECT a FROM Annonce a")
public class Annonce implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_publi")
	private int idPubli;

	@Temporal(TemporalType.DATE)
	@Column(name="date_publication")
	private Date datePublication;

	@Temporal(TemporalType.DATE)
	@Column(name="date_retrait")
	private Date dateRetrait;

	private String description;

	@Column(name="qte_publi")
	private double qtePubli;

	private String titre;

	//bi-directional many-to-one association to Adresse
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_adresse")
	private Adresse adresse;

	//bi-directional many-to-one association to MotifRetrait
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_motif_retrait")
	private MotifRetrait motifRetrait;

	//bi-directional many-to-one association to Stock
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_prod_stock")
	private Stock stock;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	private Utilisateur utilisateur;

	//bi-directional many-to-one association to Reponse
	@OneToMany(mappedBy="annonce")
	private List<Reponse> reponses;

	public Annonce() {
	}

	public int getIdPubli() {
		return this.idPubli;
	}

	public void setIdPubli(int idPubli) {
		this.idPubli = idPubli;
	}

	public Date getDatePublication() {
		return this.datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public Date getDateRetrait() {
		return this.dateRetrait;
	}

	public void setDateRetrait(Date dateRetrait) {
		this.dateRetrait = dateRetrait;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getQtePubli() {
		return this.qtePubli;
	}

	public void setQtePubli(double qtePubli) {
		this.qtePubli = qtePubli;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public MotifRetrait getMotifRetrait() {
		return this.motifRetrait;
	}

	public void setMotifRetrait(MotifRetrait motifRetrait) {
		this.motifRetrait = motifRetrait;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Reponse> getReponses() {
		return this.reponses;
	}

	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}

	public Reponse addRepons(Reponse repons) {
		getReponses().add(repons);
		repons.setAnnonce(this);

		return repons;
	}

	public Reponse removeRepons(Reponse repons) {
		getReponses().remove(repons);
		repons.setAnnonce(null);

		return repons;
	}

}