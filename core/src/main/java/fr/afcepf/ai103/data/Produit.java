package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the produit database table.
 * 
 */
@Entity
@Table(name="produit")
@NamedQuery(name="Produit.findAll", query="SELECT p FROM Produit p")
public class Produit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prod")
	private int idProd;

	private String code;

	private String image;

	@Column(name="libelle_prod")
	private String libelleProd;

	//bi-directional many-to-one association to SousCategorie
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_sous_cat")
	private SousCategorie sousCategorie;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="produit")
	private List<Stock> stocks;

	public Produit() {
	}

	public int getIdProd() {
		return this.idProd;
	}

	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLibelleProd() {
		return this.libelleProd;
	}

	public void setLibelleProd(String libelleProd) {
		this.libelleProd = libelleProd;
	}

	public SousCategorie getSousCategorie() {
		return this.sousCategorie;
	}

	public void setSousCategorie(SousCategorie sousCategorie) {
		this.sousCategorie = sousCategorie;
	}

	public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setProduit(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setProduit(null);

		return stock;
	}

}