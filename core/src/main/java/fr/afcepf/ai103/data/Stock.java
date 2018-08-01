package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
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
	private int id_prod_stock;

	@Temporal(TemporalType.DATE)
	private Date date_ajout;

	@Temporal(TemporalType.DATE)
	private Date date_peremption;

	private int duree_ext_stock;

	private BigDecimal prix;

	private BigDecimal qte_initiale;

	//bi-directional many-to-one association to Annonce
	@OneToMany(mappedBy="stock")
	private List<Annonce> annonces;

	//bi-directional many-to-one association to Consommation
	@OneToMany(mappedBy="stock")
	private List<Consommation> consommations;

	//bi-directional many-to-one association to Produit
	@ManyToOne
	@JoinColumn(name="Id_prod")
	private Produit produit;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="Id_user")
	private Utilisateur utilisateur;

	public Stock() {
	}

	public int getId_prod_stock() {
		return this.id_prod_stock;
	}

	public void setId_prod_stock(int id_prod_stock) {
		this.id_prod_stock = id_prod_stock;
	}

	public Date getDate_ajout() {
		return this.date_ajout;
	}

	public void setDate_ajout(Date date_ajout) {
		this.date_ajout = date_ajout;
	}

	public Date getDate_peremption() {
		return this.date_peremption;
	}

	public void setDate_peremption(Date date_peremption) {
		this.date_peremption = date_peremption;
	}

	public int getDuree_ext_stock() {
		return this.duree_ext_stock;
	}

	public void setDuree_ext_stock(int duree_ext_stock) {
		this.duree_ext_stock = duree_ext_stock;
	}

	public BigDecimal getPrix() {
		return this.prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public BigDecimal getQte_initiale() {
		return this.qte_initiale;
	}

	public void setQte_initiale(BigDecimal qte_initiale) {
		this.qte_initiale = qte_initiale;
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

	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}