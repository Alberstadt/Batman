package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mode_conso database table.
 * 
 */
@Entity
@Table(name="mode_conso")
@NamedQuery(name="ModeConso.findAll", query="SELECT m FROM ModeConso m")
public class ModeConso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_mode")
	private int idMode;

	@Column(name="libelle_mode")
	private String libelleMode;

	//bi-directional many-to-one association to Consommation
	@OneToMany(mappedBy="modeConso")
	private List<Consommation> consommations;

	public ModeConso() {
	}

	public int getIdMode() {
		return this.idMode;
	}

	public void setIdMode(int idMode) {
		this.idMode = idMode;
	}

	public String getLibelleMode() {
		return this.libelleMode;
	}

	public void setLibelleMode(String libelleMode) {
		this.libelleMode = libelleMode;
	}

	public List<Consommation> getConsommations() {
		return this.consommations;
	}

	public void setConsommations(List<Consommation> consommations) {
		this.consommations = consommations;
	}

	public Consommation addConsommation(Consommation consommation) {
		getConsommations().add(consommation);
		consommation.setModeConso(this);

		return consommation;
	}

	public Consommation removeConsommation(Consommation consommation) {
		getConsommations().remove(consommation);
		consommation.setModeConso(null);

		return consommation;
	}

}