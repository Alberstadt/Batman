package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the consommation database table.
 * 
 */
@Entity
@NamedQuery(name="Consommation.findAll", query="SELECT c FROM Consommation c")
public class Consommation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_conso;

	@Temporal(TemporalType.DATE)
	private Date date_conso;

	private double qte_conso;

	//bi-directional many-to-one association to ModeConso
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Id_mode")
	private ModeConso modeConso;

	//bi-directional many-to-one association to Stock
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Id_prod_stock")
	private Stock stock;

	public Consommation() {
	}

	public int getId_conso() {
		return this.id_conso;
	}

	public void setId_conso(int id_conso) {
		this.id_conso = id_conso;
	}

	public Date getDate_conso() {
		return this.date_conso;
	}

	public void setDate_conso(Date date_conso) {
		this.date_conso = date_conso;
	}

	public double getQte_conso() {
		return this.qte_conso;
	}

	public void setQte_conso(double qte_conso) {
		this.qte_conso = qte_conso;
	}

	public ModeConso getModeConso() {
		return this.modeConso;
	}

	public void setModeConso(ModeConso modeConso) {
		this.modeConso = modeConso;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}