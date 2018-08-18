package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contact")
	private int idContact;

	@Temporal(TemporalType.DATE)
	@Column(name="date_acceptation")
	private Date dateAcceptation;

	@Temporal(TemporalType.DATE)
	@Column(name="date_invitation")
	private Date dateInvitation;

	@Temporal(TemporalType.DATE)
	@Column(name="date_refus")
	private Date dateRefus;

	@Temporal(TemporalType.DATE)
	@Column(name="date_suppression")
	private Date dateSuppression;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	private Utilisateur utilisateur1;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id_friend")
	private Utilisateur utilisateur2;

	public Contact() {
	}

	public int getIdContact() {
		return this.idContact;
	}

	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}

	public Date getDateAcceptation() {
		return this.dateAcceptation;
	}

	public void setDateAcceptation(Date dateAcceptation) {
		this.dateAcceptation = dateAcceptation;
	}

	public Date getDateInvitation() {
		return this.dateInvitation;
	}

	public void setDateInvitation(Date dateInvitation) {
		this.dateInvitation = dateInvitation;
	}

	public Date getDateRefus() {
		return this.dateRefus;
	}

	public void setDateRefus(Date dateRefus) {
		this.dateRefus = dateRefus;
	}

	public Date getDateSuppression() {
		return this.dateSuppression;
	}

	public void setDateSuppression(Date dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Utilisateur getUtilisateur1() {
		return this.utilisateur1;
	}

	public void setUtilisateur1(Utilisateur utilisateur1) {
		this.utilisateur1 = utilisateur1;
	}

	public Utilisateur getUtilisateur2() {
		return this.utilisateur2;
	}

	public void setUtilisateur2(Utilisateur utilisateur2) {
		this.utilisateur2 = utilisateur2;
	}

}