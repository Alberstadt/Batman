package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the consommation database table.
 * 
 */
@Entity
@Table(name="consommation")
@NamedQuery(name="Consommation.findAll", query="SELECT c FROM Consommation c")
public class Consommation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_conso")
	private int idConso;

	@Temporal(TemporalType.DATE)
	@Column(name="date_conso")
	private Date dateConso;

	@Column(name="qte_conso")
	private double qteConso;

	//bi-directional many-to-one association to ModeConso
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_mode")
	private ModeConso modeConso;

	//bi-directional many-to-one association to Stock
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_prod_stock")
	private Stock stock;

	public Consommation() {
	}

	public int getIdConso() {
		return this.idConso;
	}

	public void setIdConso(int idConso) {
		this.idConso = idConso;
	}

	public Date getDateConso() {
		return this.dateConso;
	}

	public void setDateConso(Date dateConso) {
		this.dateConso = dateConso;
	}

	public double getQteConso() {
		return this.qteConso;
	}

	public void setQteConso(double qteConso) {
		this.qteConso = qteConso;
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