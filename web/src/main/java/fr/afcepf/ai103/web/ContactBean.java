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

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Contact;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IContactService;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean
@ViewScoped
public class ContactBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	IContactService contactService;
	
	@EJB
	IUtilisateurService utilisateurService;
	
	private Utilisateur utilisateur1;
	private Utilisateur utilisateur2;
	private Contact contact;
	private String photoFf;
	private String pseudoFf;
	private Adresse adress;
	private Date dateInvitation;
	List<Contact> listeFoodF = new ArrayList<Contact>();
    List<Contact> listeFoodFE = new ArrayList<Contact>();
    List<Contact> listeFoodFR = new ArrayList<Contact>();
    List<Utilisateur> listeUtilisateurs;
	

	public ContactBean() 
	{
	}
	
	@PostConstruct
	public void init()
	{
		//pseudoFf = utilisateurService.getUserById(id_user).getLogin();
		//photoFf =utilisateurService.getUtilisateur1ByIdUser(4).getPortrait();
		listeUtilisateurs = utilisateurService.getAllUsers();
		//adress = stockService.recupererAdresseById(1);	
	}
	
	/*public List<Utilisateur> afficherListeUtilisateurs()
	{
		List<Utilisateur> listeUtilisateurs = utilisateurService.getAllUsers();
		
		return listeUtilisateurs;
	}
	*/
	
	public void envoyerDemandeDeFoodFriend(int id_friend, int id_user)
	{
		 /*utilisateur1 = utilisateurService.getUtilisateur1ByIdUser(id_friend);
	 
		 utilisateur2 = utilisateurService.getUtilisateur2ByIdUser(id_user);*/
		 
		 utilisateur1 = utilisateurService.getUtilisateur1ByIdUser(4);
		 utilisateur2 = utilisateurService.getUtilisateur2ByIdUser(1);
		 dateInvitation = new Date();
		
		contact.setUtilisateur2(utilisateur2);
		contact.setUtilisateur1(utilisateur1);
		contact.setDateInvitation(dateInvitation);
		contact.setDateRefus(null);
		contact.setDateAcceptation(null);
		contact.setDateSuppression(null);
		
		contactService.creerNouveauContact(contact);
	}
	
	public void accepterDemandeDeFoodFriend(int id_friend)
	{
		//contact = contactService.recupererContactByIdFriend(id_friend);
		contact = contactService.recupererContactByIdFriend(4);
		
		contact.setDateAcceptation(new Date());
		
		contactService.mettreAjourContact(contact);
	}
	
	 public void refuserDemandeDeFoodFriend(int id_friend)
	 {
		 //contact = contactService.recupererContactByIdFriend(id_friend);
		 contact = contactService.recupererContactByIdFriend(1);
			
			contact.setDateRefus(new Date());
			
			contactService.mettreAjourContact(contact);
	 }
	 
	 //mettre a jour le champs date de suppression, la suppression 
	 //elle ne sera effective que dans la methode afficherLaListeDeMesFoodFriends
	  public void supprimerFoodFriend(int id_contact)
	  {
		  contact = contactService.recupererContactById(id_contact);
		  
		  contact.setDateSuppression(new Date());
			
			contactService.mettreAjourContact(contact);
	  }
	  
	  
	  public List<Contact> afficherLaListeDeMesFoodFriends(int id_user)
		{
		  List<Contact> contacts = contactService.recupererListeDeMesFoodF(id_user);
		  
		  for(Contact contact : contacts )
		    { 
		  if( contact.getDateInvitation()!= null
			  && contact.getDateAcceptation() != null
			  && contact.getDateRefus() == null 
			  && contact.getDateSuppression() == null )
				  {
			  		listeFoodF.add(contact);
				  }
		    }
		  return listeFoodF;
		}
		
	  public List<Contact> afficherMaListeDeDemandesDeFoodFriendsRecues(int id_friend)
	   {	
		  //List<Contact> contacts1 = utilisateur1.getContacts1();
		  List<Contact> contacts = contactService.recupererListeDeMesFoodF(id_friend);
		  
		  for(Contact contact : contacts )
		    { 
		  if( contact.getDateInvitation()!= null
			  && contact.getDateAcceptation() == null
			  && contact.getDateRefus() == null 
			  && contact.getDateSuppression() == null )
				  {
			  		listeFoodF.add(contact);
				  }
		  }
		   return listeFoodF;
	  }
	  
	  public List<Contact> afficherMaListeDeDemandesDeFoodFriendsEnvoyees(int id_user)
	  {   
		  // List<Contact> contacts2 = utilisateur2.getContacts2();
		  
		  List<Contact> contacts = contactService.recupererListeDeMesFoodF(id_user);
		  
		  for(Contact contact : contacts )
		  { 
		  if( contact.getDateInvitation()!= null
			  && contact.getDateAcceptation() == null
			  && contact.getDateRefus() == null 
			  && contact.getDateSuppression() == null )
				  {
			  		listeFoodF.add(contact);
				  }
		  }
		  return listeFoodF;
	  }


	public IContactService getContactService() {
		return contactService;
	}


	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}


	public Utilisateur getUtilisateur1() {
		return utilisateur1;
	}


	public void setUtilisateur1(Utilisateur utilisateur1) {
		this.utilisateur1 = utilisateur1;
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

	public List<Utilisateur> getListeUtilisateurs() {
		return listeUtilisateurs;
	}

	public void setListeUtilisateurs(List<Utilisateur> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
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
	  
	
}
