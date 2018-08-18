package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the stock database table.
 * 
 */
@Entity
@NamedQuery(name="Stock.findAll", query="SELECT s FROM Stock s")
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prod_stock")
	private int idProdStock;

	@Temporal(TemporalType.DATE)
	@Column(name="date_ajout")
	private Date dateAjout;

	@Temporal(TemporalType.DATE)
	@Column(name="date_peremption")
	private Date datePeremption;

	@Column(name="duree_ext_stock")
	private int dureeExtStock;

	private double prix;

	@Column(name="qte_initiale")
	private double qteInitiale;

	//bi-directional many-to-one association to Annonce
	@OneToMany(mappedBy="stock")
	private List<Annonce> annonces;

	//bi-directional many-to-one association to Consommation
	@OneToMany(mappedBy="stock")
	private List<Consommation> consommations;

	//bi-directional many-to-one association to Conservation
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conserv")
	private Conservation conservation;

	//bi-directional many-to-one association to Produit
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_prod")
	private Produit produit;

	//bi-directional many-to-one association to Unite
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_unite")
	private Unite unite;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_user")
	private Utilisateur utilisateur;

	public Stock() {
	}

	public int getIdProdStock() {
		return this.idProdStock;
	}

	public void setIdProdStock(int idProdStock) {
		this.idProdStock = idProdStock;
	}

	public Date getDateAjout() {
		return this.dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	public Date getDatePeremption() {
		return this.datePeremption;
	}

	public void setDatePeremption(Date datePeremption) {
		this.datePeremption = datePeremption;
	}

	public int getDureeExtStock() {
		return this.dureeExtStock;
	}

	public void setDureeExtStock(int dureeExtStock) {
		this.dureeExtStock = dureeExtStock;
	}

	public double getPrix() {
		return this.prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public double getQteInitiale() {
		return this.qteInitiale;
	}

	public void setQteInitiale(double qteInitiale) {
		this.qteInitiale = qteInitiale;
	}

	public List<Annonce> getAnnonces() {
		return this.annonces;
	}

	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}

	public Annonce addAnnonce(Annonce annonce) {
		getAnnonces().add(annonce);
		annonce.setStock(this);

		return annonce;
	}

	public Annonce removeAnnonce(Annonce annonce) {
		getAnnonces().remove(annonce);
		annonce.setStock(null);

		return annonce;
	}

	public List<Consommation> getConsommations() {
		return this.consommations;
	}

	public void setConsommations(List<Consommation> consommations) {
		this.consommations = consommations;
	}

	public Consommation addConsommation(Consommation consommation) {
		getConsommations().add(consommation);
		consommation.setStock(this);

		return consommation;
	}

	public Consommation removeConsommation(Consommation consommation) {
		getConsommations().remove(consommation);
		consommation.setStock(null);

		return consommation;
	}

	public Conservation getConservation() {
		return this.conservation;
	}

	public void setConservation(Conservation conservation) {
		this.conservation = conservation;
	}

	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Unite getUnite() {
		return this.unite;
	}

	public void setUnite(Unite unite) {
		this.unite = unite;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}