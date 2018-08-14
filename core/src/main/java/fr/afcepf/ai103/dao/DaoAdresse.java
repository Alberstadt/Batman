package fr.afcepf.ai103.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Utilisateur;

@Stateless
@Local

public class DaoAdresse implements IDaoAdresse
{

	@PersistenceContext(unitName = "core")
	private EntityManager entityManager;

	public DaoAdresse(){}
	
	@Override
	public Adresse update(Adresse adresse)
	{
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

