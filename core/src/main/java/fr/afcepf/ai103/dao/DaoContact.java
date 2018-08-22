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
	public void delete(int id_contact)
	{
		Contact contact = entityManager.find(Contact.class, id_contact);
		entityManager.remove(contact);
	}

	@Override
	public Contact getContactById(int id_contact)
	{
		return entityManager.find(Contact.class, id_contact);
	}
	
	@Override
	public Contact getContactByIdUser(int id_user)
	{
		return entityManager.find(Contact.class, id_user);
	}
	
	@Override
	public Contact getContactByIdFriend(int id_friend)
	{
		return entityManager.find(Contact.class, id_friend);
	}
	
	@Override
	public List<Contact> listeDesContactsDeUser(int id_user)
	{
		return entityManager.createQuery("select c from Contact c where c.utilisateur1.idUser = :id_user ",
				Contact.class).setParameter("id_user", id_user).getResultList();
	}
	
}
