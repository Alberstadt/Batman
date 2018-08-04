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
@NamedQuery(name="Annonce.findAll", query="SELECT a FROM Annonce a")
public class Annonce implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_publi;

	@Temporal(TemporalType.DATE)
	private Date date_publication;

	@Temporal(TemporalType.DATE)
	private Date date_retrait;

	private String description;

	private double qte_publi;

	private String titre;

	//bi-directional many-to-one association to MotifRetrait
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id_motif_retrait")
	private MotifRetrait motifRetrait;

	//bi-directional many-to-one association to Stock
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id_prod_stock")
	private Stock stock;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id_user")
	private Utilisateur utilisateur;

	//bi-directional many-to-one association to Reponse
	@OneToMany(mappedBy="annonce")
	private List<Reponse> reponses;

	public Annonce() {
	}

	public int getId_publi() {
		return this.id_publi;
	}

	public void setId_publi(int id_publi) {
		this.id_publi = id_publi;
	}

	public Date getDate_publication() {
		return this.date_publication;
	}

	public void setDate_publication(Date date_publication) {
		this.date_publication = date_publication;
	}

	public Date getDate_retrait() {
		return this.date_retrait;
	}

	public void setDate_retrait(Date date_retrait) {
		this.date_retrait = date_retrait;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getQte_publi() {
		return this.qte_publi;
	}

	public void setQte_publi(double qte_publi) {
		this.qte_publi = qte_publi;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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