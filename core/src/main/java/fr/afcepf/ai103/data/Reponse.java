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
@Table(name="reponse")
@NamedQuery(name="Reponse.findAll", query="SELECT r FROM Reponse r")
public class Reponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_reponse")
	private int idReponse;

	@Temporal(TemporalType.DATE)
	@Column(name="date_annulation")
	private Date dateAnnulation;

	@Temporal(TemporalType.DATE)
	@Column(name="date_demande")
	private Date dateDemande;

	@Temporal(TemporalType.DATE)
	@Column(name="date_selection")
	private Date dateSelection;

	@Temporal(TemporalType.DATE)
	@Column(name="date_transaction")
	private Date dateTransaction;

	//bi-directional many-to-one association to Evaluation
	@OneToMany(mappedBy="reponse")
	private List<Evaluation> evaluations;

	//bi-directional many-to-one association to Annonce
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_publi")
	private Annonce annonce;

	//bi-directional many-to-one association to MotifAnnulation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_motif_annul")
	private MotifAnnulation motifAnnulation;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	private Utilisateur utilisateur;

	public Reponse() {
	}

	public int getIdReponse() {
		return this.idReponse;
	}

	public void setIdReponse(int idReponse) {
		this.idReponse = idReponse;
	}

	public Date getDateAnnulation() {
		return this.dateAnnulation;
	}

	public void setDateAnnulation(Date dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
	}

	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Date getDateSelection() {
		return this.dateSelection;
	}

	public void setDateSelection(Date dateSelection) {
		this.dateSelection = dateSelection;
	}

	public Date getDateTransaction() {
		return this.dateTransaction;
	}

	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
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