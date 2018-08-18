package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sous_categorie database table.
 * 
 */
@Entity
@Table(name="sous_categorie")
@NamedQuery(name="SousCategorie.findAll", query="SELECT s FROM SousCategorie s")
public class SousCategorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sous_cat")
	private int idSousCat;

	@Column(name="duree_ext_scat")
	private int dureeExtScat;

	@Column(name="libelle_sous_cat")
	private String libelleSousCat;

	//bi-directional many-to-one association to Produit
	@OneToMany(mappedBy="sousCategorie")
	private List<Produit> produits;

	//bi-directional many-to-one association to Categorie
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cat")
	private Categorie categorie;

	public SousCategorie() {
	}

	public int getIdSousCat() {
		return this.idSousCat;
	}

	public void setIdSousCat(int idSousCat) {
		this.idSousCat = idSousCat;
	}

	public int getDureeExtScat() {
		return this.dureeExtScat;
	}

	public void setDureeExtScat(int dureeExtScat) {
		this.dureeExtScat = dureeExtScat;
	}

	public String getLibelleSousCat() {
		return this.libelleSousCat;
	}

	public void setLibelleSousCat(String libelleSousCat) {
		this.libelleSousCat = libelleSousCat;
	}

	public List<Produit> getProduits() {
		return this.produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Produit addProduit(Produit produit) {
		getProduits().add(produit);
		produit.setSousCategorie(this);

		return produit;
	}

	public Produit removeProduit(Produit produit) {
		getProduits().remove(produit);
		produit.setSousCategorie(null);

		return produit;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

}