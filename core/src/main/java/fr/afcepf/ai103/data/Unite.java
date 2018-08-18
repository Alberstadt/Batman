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
	@Column(name="id_unite")
	private int idUnite;

	@Column(name="libelle_unite")
	private String libelleUnite;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="unite")
	private List<Stock> stocks;

	public Unite() {
	}

	public int getIdUnite() {
		return this.idUnite;
	}

	public void setIdUnite(int idUnite) {
		this.idUnite = idUnite;
	}

	public String getLibelleUnite() {
		return this.libelleUnite;
	}

	public void setLibelleUnite(String libelleUnite) {
		this.libelleUnite = libelleUnite;
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