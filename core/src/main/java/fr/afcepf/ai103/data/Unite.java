package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the unite database table.
 * 
 */
@Entity
@NamedQuery(name="Unite.findAll", query="SELECT u FROM Unite u")
public class Unite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_unite;

	private String libelle_unite;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="unite")
	private List<Stock> stocks;

	public Unite() {
	}

	public int getId_unite() {
		return this.id_unite;
	}

	public void setId_unite(int id_unite) {
		this.id_unite = id_unite;
	}

	public String getLibelle_unite() {
		return this.libelle_unite;
	}

	public void setLibelle_unite(String libelle_unite) {
		this.libelle_unite = libelle_unite;
	}

	public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setUnite(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setUnite(null);

		return stock;
	}

}