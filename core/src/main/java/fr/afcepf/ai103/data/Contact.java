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
	private int id_contact;

	@Temporal(TemporalType.DATE)
	private Date date_acceptation;

	@Temporal(TemporalType.DATE)
	private Date date_invitation;

	@Temporal(TemporalType.DATE)
	private Date date_refus;

	@Temporal(TemporalType.DATE)
	private Date date_suppression;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Id_friend")
	private Utilisateur utilisateur1;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Id_user")
	private Utilisateur utilisateur2;

	public Contact() {
	}

	public int getId_contact() {
		return this.id_contact;
	}

	public void setId_contact(int id_contact) {
		this.id_contact = id_contact;
	}

	public Date getDate_acceptation() {
		return this.date_acceptation;
	}

	public void setDate_acceptation(Date date_acceptation) {
		this.date_acceptation = date_acceptation;
	}

	public Date getDate_invitation() {
		return this.date_invitation;
	}

	public void setDate_invitation(Date date_invitation) {
		this.date_invitation = date_invitation;
	}

	public Date getDate_refus() {
		return this.date_refus;
	}

	public void setDate_refus(Date date_refus) {
		this.date_refus = date_refus;
	}

	public Date getDate_suppression() {
		return this.date_suppression;
	}

	public void setDate_suppression(Date date_suppression) {
		this.date_suppression = date_suppression;
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