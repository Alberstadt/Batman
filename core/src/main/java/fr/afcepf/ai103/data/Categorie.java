package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categorie database table.
 * 
 */
@Entity
@NamedQuery(name="Categorie.findAll", query="SELECT c FROM Categorie c")
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cat")
	private int idCat;

	@Column(name="libelle_cat")
	private String libelleCat;

	//bi-directional many-to-one association to SousCategorie
	@OneToMany(mappedBy="categorie")
	private List<SousCategorie> sousCategories;

	public Categorie() {
	}

	public int getIdCat() {
		return this.idCat;
	}

	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}

	public String getLibelleCat() {
		return this.libelleCat;
	}

	public void setLibelleCat(String libelleCat) {
		this.libelleCat = libelleCat;
	}

	public List<SousCategorie> getSousCategories() {
		return this.sousCategories;
	}

	public void setSousCategories(List<SousCategorie> sousCategories) {
		this.sousCategories = sousCategories;
	}

	public SousCategorie addSousCategory(SousCategorie sousCategory) {
		getSousCategories().add(sousCategory);
		sousCategory.setCategorie(this);

		return sousCategory;
	}

	public SousCategorie removeSousCategory(SousCategorie sousCategory) {
		getSousCategories().remove(sousCategory);
		sousCategory.setCategorie(null);

		return sousCategory;
	}

}