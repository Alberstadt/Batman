package fr.afcepf.ai103.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Contact;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IContactService;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean
@SessionScoped
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
	

	public ContactBean() 
	{
	}
	
	@PostConstruct
	public void init()
	{
		pseudoFf = utilisateurService.getUtilisateur1ByIdUser(4).getLogin();
		photoFf =utilisateurService.getUtilisateur1ByIdUser(4).getPortrait();
	
		//adress = stockService.recupererAdresseById(1);	
	}
	
	public List<Utilisateur> afficherListeUtilisateurs()
	{
		List<Utilisateur> listeUtilisateurs = utilisateurService.getAllUsers();
		
		return listeUtilisateurs;
	}
	
	public void envoyerDemandeDeFoodFriend(int id_friend, int id_user)
	{
		 /*utilisateur1 = utilisateurService.getUtilisateur1ByIdUser(id_friend);
	 
		 utilisateur2 = utilisateurService.getUtilisateur2ByIdUser(id_user);*/
		 
		 utilisateur1 = utilisateurService.getUtilisateur1ByIdUser(4);
		 utilisateur2 = utilisateurService.getUtilisateur2ByIdUser(1);
		 dateInvitation = new Date();
		
		contact.setUtilisateur2(utilisateur2);
		contact.setUtilisateur1(utilisateur1);
		contact.setDate_invitation(dateInvitation);
		contact.setDate_refus(null);
		contact.setDate_acceptation(null);
		contact.setDate_suppression(null);
		
		contactService.creerNouveauContact(contact);
	}
	
	public void accepterDemandeDeFoodFriend(int id_friend)
	{
		//contact = contactService.recupererContactByIdFriend(id_friend);
		contact = contactService.recupererContactByIdFriend(4);
		
		contact.setDate_acceptation(new Date());
		
		contactService.mettreAjourContact(contact);
	}
	
	 public void refuserDemandeDeFoodFriend(int id_friend)
	 {
		 //contact = contactService.recupererContactByIdFriend(id_friend);
		 contact = contactService.recupererContactByIdFriend(1);
			
			contact.setDate_refus(new Date());
			
			contactService.mettreAjourContact(contact);
	 }
	 
	 //mettre a jour le champs date de suppression, la suppression 
	 //elle ne sera effective que dans la methode afficherLaListeDeMesFoodFriends
	  public void supprimerFoodFriend(int id_contact)
	  {
		  contact = contactService.recupererContactById(id_contact);
		  
		  contact.setDate_suppression(new Date());
			
			contactService.mettreAjourContact(contact);
	  }
	  
	  
	  public List<Contact> afficherLaListeDeMesFoodFriends(int id_user)
		{
		  List<Contact> contacts = contactService.recupererListeDeMesFoodF(id_user);
		  
		  for(Contact contact : contacts )
		    { 
		  if( contact.getDate_invitation()!= null
			  && contact.getDate_acceptation() != null
			  && contact.getDate_refus() == null 
			  && contact.getDate_suppression() == null )
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
//		  
		  for(Contact contact : contacts )
		    { 
		  if( contact.getDate_invitation()!= null
			  && contact.getDate_acceptation() == null
			  && contact.getDate_refus() == null 
			  && contact.getDate_suppression() == null )
				  {
			  		listeFoodF.add(contact);
				  }
		  }
		   return listeFoodF;
	  }
	  
	  public List<Contact> afficherMaListeDeDemandesDeFoodFriendsEnvoyees(int id_user)
	  {   // List<Contact> contacts2 = utilisateur2.getContacts2();
		  
		  List<Contact> contacts = contactService.recupererListeDeMesFoodF(id_user);
		  
		  for(Contact contact : contacts )
		  { 
		  if( contact.getDate_invitation()!= null
			  && contact.getDate_acceptation() == null
			  && contact.getDate_refus() == null 
			  && contact.getDate_suppression() == null )
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
	  
}
