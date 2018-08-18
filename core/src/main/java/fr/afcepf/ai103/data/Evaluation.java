package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation database table.
 * 
 */
@Entity
@Table(name="evaluation")
@NamedQuery(name="Evaluation.findAll", query="SELECT e FROM Evaluation e")
public class Evaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_eval")
	private int idEval;

	@Column(name="auteur_eval")
	private short auteurEval;

	@Column(name="com_eval")
	private String comEval;

	private int note;

	//bi-directional many-to-one association to Reponse
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_reponse")
	private Reponse reponse;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	private Utilisateur utilisateur;

	public Evaluation() {
	}

	public int getIdEval() {
		return this.idEval;
	}

	public void setIdEval(int idEval) {
		this.idEval = idEval;
	}

	public short getAuteurEval() {
		return this.auteurEval;
	}

	public void setAuteurEval(short auteurEval) {
		this.auteurEval = auteurEval;
	}

	public String getComEval() {
		return this.comEval;
	}

	public void setComEval(String comEval) {
		this.comEval = comEval;
	}

	public int getNote() {
		return this.note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Reponse getReponse() {
		return this.reponse;
	}

	public void setReponse(Reponse reponse) {
		this.reponse = reponse;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}