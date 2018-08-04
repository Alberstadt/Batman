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
	private int id_sous_cat;

	private int duree_ext_scat;

	private String libelle_sous_cat;

	//bi-directional many-to-one association to Produit
	@OneToMany(mappedBy="sousCategorie")
	private List<Produit> produits;

	//bi-directional many-to-one association to Categorie
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id_cat")
	private Categorie categorie;

	public SousCategorie() {
	}

	public int getId_sous_cat() {
		return this.id_sous_cat;
	}

	public void setId_sous_cat(int id_sous_cat) {
		this.id_sous_cat = id_sous_cat;
	}

	public int getDuree_ext_scat() {
		return this.duree_ext_scat;
	}

	public void setDuree_ext_scat(int duree_ext_scat) {
		this.duree_ext_scat = duree_ext_scat;
	}

	public String getLibelle_sous_cat() {
		return this.libelle_sous_cat;
	}

	public void setLibelle_sous_cat(String libelle_sous_cat) {
		this.libelle_sous_cat = libelle_sous_cat;
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