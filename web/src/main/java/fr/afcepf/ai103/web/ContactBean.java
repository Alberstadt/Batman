package fr.afcepf.ai103.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Contact;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IContactService;
import fr.afcepf.ai103.service.IUtilisateurService;


@ManagedBean
@ViewScoped
public class ContactBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	IContactService contactService;

	@EJB
	IUtilisateurService utilisateurService;

	private Utilisateur user = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
			.get("user");
	private Utilisateur utilisateur2;
	private Contact contact = new Contact();
	private String photoFf;
	private String pseudoFf;
	private Adresse adress;
	private Date dateInvitation;
	List<Contact> listeFoodF = new ArrayList<Contact>();
	List<Contact> listeFoodFE;
	List<Contact> listeFoodFR;
	private List<Utilisateur> currentlySelectedUser = new ArrayList<Utilisateur>();
	private List<Utilisateur> listeUtilisateurs;
    private String boolMap = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("boolMap");

	public ContactBean() {
	}

	@PostConstruct
	public void init() {
	
		//pseudoFf = this.utilisateur2.getLogin();
		//photoFf = this.utilisateur2.getPortrait();
		listeUtilisateurs = utilisateurService.getAllUsers();
		System.err.println("liste FF :: " + listeUtilisateurs);
		// adress = stockService.recupererAdresseById(1);
		//listeFoodFR = afficherMaListeDeDemandesDeFoodFriendsRecues();
		listeFoodFE = contactService.getListDemandeFfEnvoyeesUser(this.user);
		
		listeFoodFR =  contactService.getListDemandeRecueFfUtilisateur2(this.user);
		System.out.println("Liste dans le bean des demandes recues" + listeFoodFR);
	
	}
		//Methode pour recuperer un utilisateur en le selctionnant dans la datatable
	/*public void onSelect(Utilisateur utilisateur2) {
		System.out.println("OnSelect:" + utilisateur2.getLogin());
		this.utilisateur2 = utilisateur2;
		if (null != utilisateur2) {
			getCurrentlySelectedUser().add(utilisateur2);
		}
	}
	
	 public void onDeselect(Utilisateur utilisateur2) {
		 
		    if (null != utilisateur2) 
		    {
		      getCurrentlySelectedUser().remove(utilisateur2);
		    } 
		 }
              */
	public void envoyerDemandeDeFoodFriend() {
		
		System.out.println("passage contact bean - contact");

		//utilisateur2 = utilisateurService.getUtilisateur2ByIdUser(utilisateur2.getIdUser());
		System.out.println("demandeFF" + utilisateur2.getLogin());
		Contact nouvContact = new Contact();
		dateInvitation = new Date();

		nouvContact.setUtilisateur1(this.user);
		nouvContact.setUtilisateur2(this.utilisateur2);
		nouvContact.setDateInvitation(this.dateInvitation);
		nouvContact.setDateRefus(null);
		nouvContact.setDateAcceptation(null);
		nouvContact.setDateSuppression(null);

		contactService.creerNouveauContact(nouvContact);
		
	}

	public void accepterDemandeDeFoodFriend() {
		contact = contactService.recupererContactByIdFriend(user.getIdUser());

		contact.setDateAcceptation(new Date());
		

		contactService.mettreAjourContact(contact);
	}

	public void refuserDemandeDeFoodFriend() {
		contact = contactService.recupererContactByIdFriend(user.getIdUser());
		

		contact.setDateRefus(new Date());
		
		contactService.mettreAjourContact(contact);
	}

	// mettre a jour le champs date de suppression, la suppression
	// elle ne sera effective que dans la methode afficherLaListeDeMesFoodFriends
	public void supprimerFoodFriend(int id_contact) {
		contact = contactService.recupererContactById(id_contact);

		contact.setDateSuppression(new Date());

		contactService.mettreAjourContact(contact);
	}

	public List<Contact> afficherLaListeDeMesFoodFriends() {
		List<Contact> contacts = this.user.getContacts1();

		for (Contact contact : contacts) {
			if (contact.getDateInvitation() != null && contact.getDateAcceptation() != null
					&& contact.getDateRefus() == null && contact.getDateSuppression() == null) {
				listeFoodF.add(contact);
			}
		}
		return listeFoodF;
	}

	/*public List<Contact> afficherMaListeDeDemandesDeFoodFriendsRecues() {
		// List<Contact> contacts1 = utilisateur1.getContacts1();
		List<Contact> contacts = utilisateur2.getContacts2();

		for (Contact contact : contacts) {
			if (contact.getDateInvitation() != null && contact.getDateAcceptation() == null
					&& contact.getDateRefus() == null && contact.getDateSuppression() == null) {
				listeFoodFR.add(contact);
			}
		}
		return listeFoodFR;
	}

	public List<Contact> afficherMaListeDeDemandesDeFoodFriendsEnvoyees() {

		List<Contact> contacts = user.getContacts1();

		for (Contact contact : contacts) {
			if (contact.getDateInvitation() != null && contact.getDateAcceptation() == null
					&& contact.getDateRefus() == null && contact.getDateSuppression() == null) {
				listeFoodFE.add(contact);
			}
		}
		return listeFoodFE;
	}*/

	public IContactService getContactService() {
		return contactService;
	}

	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}

	public Utilisateur getUtilisateur2() {
		return utilisateur2;
	}

	public void setUtilisateur2(Utilisateur utilisateur2) {
		this.utilisateur2 = utilisateur2;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getPhotoFf() {
		return photoFf;
	}

	public void setPhotoFf(String photoFf) {
		this.photoFf = photoFf;
	}

	public String getPseudoFf() {
		return pseudoFf;
	}

	public void setPseudoFf(String pseudoFf) {
		this.pseudoFf = pseudoFf;
	}

	public Adresse getAdress() {
		return adress;
	}

	public void setAdress(Adresse adress) {
		this.adress = adress;
	}

	public Date getDateInvitation() {
		return dateInvitation;
	}

	public void setDateInvitation(Date dateInvitation) {
		this.dateInvitation = dateInvitation;
	}

	public List<Contact> getListeFoodF() {
		return listeFoodF;
	}

	public void setListeFoodF(List<Contact> listeFoodF) {
		this.listeFoodF = listeFoodF;
	}

	public List<Contact> getListeFoodFE() {
		return listeFoodFE;
	}

	public void setListeFoodFE(List<Contact> listeFoodFE) {
		this.listeFoodFE = listeFoodFE;
	}

	public List<Contact> getListeFoodFR() {
		return listeFoodFR;
	}

	public void setListeFoodFR(List<Contact> listeFoodFR) {
		this.listeFoodFR = listeFoodFR;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public List<Utilisateur> getCurrentlySelectedUser() {
		return currentlySelectedUser;
	}

	public void setCurrentlySelectedUser(List<Utilisateur> currentlySelectedUser) {
		this.currentlySelectedUser = currentlySelectedUser;
	}

	public List<Utilisateur> getListeUtilisateurs() {
		return listeUtilisateurs;
	}

	public void setListeUtilisateurs(List<Utilisateur> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}

	public String getBoolMap() {
		return boolMap;
	}

	public void setBoolMap(String boolMap) {
		this.boolMap = boolMap;
	}
	  

}
