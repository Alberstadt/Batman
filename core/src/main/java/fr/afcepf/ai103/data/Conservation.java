package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the conservation database table.
 * 
 */
@Entity
@NamedQuery(name="Conservation.findAll", query="SELECT c FROM Conservation c")
public class Conservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_conserv;

	private int duree_ext_conserv;

	private String type;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="conservation")
	private List<Stock> stocks;

	public Conservation() {
	}

	public int getId_conserv() {
		return this.id_conserv;
	}

	public void setId_conserv(int id_conserv) {
		this.id_conserv = id_conserv;
	}

	public int getDuree_ext_conserv() {
		return this.duree_ext_conserv;
	}

	public void setDuree_ext_conserv(int duree_ext_conserv) {
		this.duree_ext_conserv = duree_ext_conserv;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setConservation(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setConservation(null);

		return stock;
	}

}