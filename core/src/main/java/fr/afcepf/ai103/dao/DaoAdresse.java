package fr.afcepf.ai103.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Adresse;

@Stateless
@Local
public class DaoAdresse implements IDaoAdresse
{

	@PersistenceContext(unitName = "core")
	private EntityManager entityManager;

	public DaoAdresse(){}

	@Override
	public Adresse create(Adresse adresse, int id_user)
	{
		System.out.println("DAO ajouter adresse : " + adresse);

		entityManager.persist(adresse);
		entityManager.merge(adresse);
		return adresse;
	}

	@Override
	public Adresse update(Adresse adresse)
	{
		System.out.println("DAO maj adresse : " + adresse);

		entityManager.merge(adresse);
		return adresse;
	}

	@Override
	public void delete(Adresse adresse)
	{
		entityManager.remove(adresse);
	}

	@Override
	public Adresse getAdresseById(Integer id_adresse)
	{
		return entityManager.find(Adresse.class, id_adresse);
	}

}
