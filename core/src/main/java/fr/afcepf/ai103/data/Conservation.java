package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the conservation database table.
 * 
 */
@Entity
@Table(name="conservation")
@NamedQuery(name="Conservation.findAll", query="SELECT c FROM Conservation c")
public class Conservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_conserv")
	private int idConserv;

	@Column(name="duree_ext_conserv")
	private int dureeExtConserv;

	private String type;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="conservation")
	private List<Stock> stocks;

	public Conservation() {
	}

	public int getIdConserv() {
		return this.idConserv;
	}

	public void setIdConserv(int idConserv) {
		this.idConserv = idConserv;
	}

	public int getDureeExtConserv() {
		return this.dureeExtConserv;
	}

	public void setDureeExtConserv(int dureeExtConserv) {
		this.dureeExtConserv = dureeExtConserv;
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