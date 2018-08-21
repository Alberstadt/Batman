package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Consommation;
import fr.afcepf.ai103.data.Contact;

@Stateless
@Local
public class DaoContact implements IDaoContact {

	@PersistenceContext(unitName = "core")
	private EntityManager entityManager;
	
	
	
	public DaoContact() {
		
	}

	@Override
	public Contact create(Contact contact)
	{
		System.out.println("passage contact dao - contact : " + contact);
		entityManager.persist(contact);
		return contact;
	}

	@Override
	public Contact update(Contact contact)
	{
		entityManager.merge(contact);
		return contact;
	}

	@Override
	public void delete(int idContact)
	{
		Contact contact = entityManager.find(Contact.class, idContact);
		entityManager.remove(contact);
	}

	@Override
	public Contact getContactById(int idContact)
	{
		return entityManager.find(Contact.class, idContact);
	}
	
	@Override
	public Contact getContactByIdUser(int idUser)
	{
		return entityManager.find(Contact.class, idUser);
	}
	
	@Override
	public Contact getContactByIdFriend(int id_friend)
	{
		return entityManager.find(Contact.class, id_friend);
	}
	
	@Override
	public List<Contact> listeDesContactsDeUser(int idUser)
	{
		return entityManager.createQuery("select c from Contact c where c.utilisateur1.idUser = :idUser ",Contact.class)
				.setParameter("idUser", idUser)
				.getResultList();
	}
	
}
