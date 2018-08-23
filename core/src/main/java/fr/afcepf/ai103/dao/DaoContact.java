package fr.afcepf.ai103.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Consommation;
import fr.afcepf.ai103.data.Contact;
import fr.afcepf.ai103.data.Utilisateur;

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
	
	@Override
	public List<Contact> getListDemandeFfEnvoyeesUser(Utilisateur user)
	{
		return entityManager.createQuery("SELECT c FROM Contact c WHERE c.dateInvitation IS NOT NULL AND c.dateAcceptation IS NULL AND "
				+ "c.dateRefus IS NULL AND c.dateSuppression IS NULL AND c.utilisateur1 = :user", Contact.class)
				.setParameter("user", user)
				.getResultList();
	}
	
	@Override
	public List<Contact> getListDemandeRecueFfUtilisateur2(Utilisateur user)
	{
		return entityManager.createQuery("SELECT c FROM Contact c WHERE "
				+ "c.dateInvitation IS NOT NULL AND  c.dateAcceptation IS NULL AND c.dateRefus IS NULL AND c.dateSuppression IS NULL AND "
				+ "c.utilisateur2 = :user", Contact.class)
				.setParameter("user", user)
				.getResultList();

	}
	
}
