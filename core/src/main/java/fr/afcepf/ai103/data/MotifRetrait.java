package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the motif_retrait database table.
 * 
 */
@Entity
@Table(name="motif_retrait")
@NamedQuery(name="MotifRetrait.findAll", query="SELECT m FROM MotifRetrait m")
public class MotifRetrait implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_motif_retrait")
	private int idMotifRetrait;

	@Column(name="libelle_retrait")
	private String libelleRetrait;

	//bi-directional many-to-one association to Annonce
	@OneToMany(mappedBy="motifRetrait")
	private List<Annonce> annonces;

	public MotifRetrait() {
	}

	public int getIdMotifRetrait() {
		return this.idMotifRetrait;
	}

	public void setIdMotifRetrait(int idMotifRetrait) {
		this.idMotifRetrait = idMotifRetrait;
	}

	public String getLibelleRetrait() {
		return this.libelleRetrait;
	}

	public void setLibelleRetrait(String libelleRetrait) {
		this.libelleRetrait = libelleRetrait;
	}

	public List<Annonce> getAnnonces() {
		return this.annonces;
	}

	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}

	public Annonce addAnnonce(Annonce annonce) {
		getAnnonces().add(annonce);
		annonce.setMotifRetrait(this);

		return annonce;
	}

	public Annonce removeAnnonce(Annonce annonce) {
		getAnnonces().remove(annonce);
		annonce.setMotifRetrait(null);

		return annonce;
	}

}