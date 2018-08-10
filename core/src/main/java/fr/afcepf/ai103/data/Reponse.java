package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the reponse database table.
 * 
 */
@Entity
@NamedQuery(name="Reponse.findAll", query="SELECT r FROM Reponse r")
public class Reponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_reponse;

	@Temporal(TemporalType.DATE)
	private Date date_annulation;

	@Temporal(TemporalType.DATE)
	private Date date_demande;

	@Temporal(TemporalType.DATE)
	private Date date_selection;

	@Temporal(TemporalType.DATE)
	private Date date_transaction;

	//bi-directional many-to-one association to Evaluation
	@OneToMany(mappedBy="reponse")
	private List<Evaluation> evaluations;

	//bi-directional many-to-one association to Annonce
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id_publi")
	private Annonce annonce;

	//bi-directional many-to-one association to MotifAnnulation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id_motif_annul")
	private MotifAnnulation motifAnnulation;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id_user")
	private Utilisateur utilisateur;

	public Reponse() {
	}

	public int getId_reponse() {
		return this.id_reponse;
	}

	public void setId_reponse(int id_reponse) {
		this.id_reponse = id_reponse;
	}

	public Date getDate_annulation() {
		return this.date_annulation;
	}

	public void setDate_annulation(Date date_annulation) {
		this.date_annulation = date_annulation;
	}

	public Date getDate_demande() {
		return this.date_demande;
	}

	public void setDate_demande(Date date_demande) {
		this.date_demande = date_demande;
	}

	public Date getDate_selection() {
		return this.date_selection;
	}

	public void setDate_selection(Date date_selection) {
		this.date_selection = date_selection;
	}

	public Date getDate_transaction() {
		return this.date_transaction;
	}

	public void setDate_transaction(Date date_transaction) {
		this.date_transaction = date_transaction;
	}

	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public Evaluation addEvaluation(Evaluation evaluation) {
		getEvaluations().add(evaluation);
		evaluation.setReponse(this);

		return evaluation;
	}

	public Evaluation removeEvaluation(Evaluation evaluation) {
		getEvaluations().remove(evaluation);
		evaluation.setReponse(null);

		return evaluation;
	}

	public Annonce getAnnonce() {
		return this.annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}

	public MotifAnnulation getMotifAnnulation() {
		return this.motifAnnulation;
	}

	public void setMotifAnnulation(MotifAnnulation motifAnnulation) {
		this.motifAnnulation = motifAnnulation;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}