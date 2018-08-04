package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation database table.
 * 
 */
@Entity
@NamedQuery(name="Evaluation.findAll", query="SELECT e FROM Evaluation e")
public class Evaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_eval;

	private String com_eval;

	private int note;

	//bi-directional many-to-one association to Reponse
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id_reponse")
	private Reponse reponse;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id_user")
	private Utilisateur utilisateur;

	public Evaluation() {
	}

	public int getId_eval() {
		return this.id_eval;
	}

	public void setId_eval(int id_eval) {
		this.id_eval = id_eval;
	}

	public String getCom_eval() {
		return this.com_eval;
	}

	public void setCom_eval(String com_eval) {
		this.com_eval = com_eval;
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