package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoContact;
import fr.afcepf.ai103.data.Contact;

@Stateless
@Local
public class ContactService implements IContactService {

	@EJB
	IDaoContact daoContact;
	
	@Override
	public List<Contact> recupererListeDeMesFoodF(int id_user)
	{
		return daoContact.listeDesContactsDeUser(id_user);
	}
	
	@Override
	public Contact creerNouveauContact(Contact contact)
	{
		System.out.println("passage contact service - contact : " + contact);
		return daoContact.create(contact);
	}
	
	@Override
	public Contact mettreAjourContact(Contact contact)
	{
		return daoContact.update(contact);
	}
	
	@Override
	public Contact recupererContactByIdFriend(int id_friend)
	{
		return daoContact.getContactByIdFriend(id_friend);
	}
	
	@Override
	public Contact recupererContactByIdUser(int id_user)
	{
		return daoContact.getContactByIdUser(id_user);
	}
	
	@Override
	public Contact recupererContactById(int id_contact)
	{
		return daoContact.getContactByIdFriend(id_contact);
	}
	
	/*@Override
	public void supprimerContact(int id_contact)
	{
		 daoContact.delete(id_contact);
	}
	*/

}
