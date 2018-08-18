package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the motif_annulation database table.
 * 
 */
@Entity
@Table(name="motif_annulation")
@NamedQuery(name="MotifAnnulation.findAll", query="SELECT m FROM MotifAnnulation m")
public class MotifAnnulation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_motif_annul")
	private int idMotifAnnul;

	@Column(name="libelle_annul")
	private String libelleAnnul;

	//bi-directional many-to-one association to Reponse
	@OneToMany(mappedBy="motifAnnulation")
	private List<Reponse> reponses;

	public MotifAnnulation() {
	}

	public int getIdMotifAnnul() {
		return this.idMotifAnnul;
	}

	public void setIdMotifAnnul(int idMotifAnnul) {
		this.idMotifAnnul = idMotifAnnul;
	}

	public String getLibelleAnnul() {
		return this.libelleAnnul;
	}

	public void setLibelleAnnul(String libelleAnnul) {
		this.libelleAnnul = libelleAnnul;
	}

	public List<Reponse> getReponses() {
		return this.reponses;
	}

	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}

	public Reponse addRepons(Reponse repons) {
		getReponses().add(repons);
		repons.setMotifAnnulation(this);

		return repons;
	}

	public Reponse removeRepons(Reponse repons) {
		getReponses().remove(repons);
		repons.setMotifAnnulation(null);

		return repons;
	}

}