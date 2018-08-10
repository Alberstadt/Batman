package fr.afcepf.ai103.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Annonce;

@Stateless
@Local
public class DaoAnnonce implements IDaoAnnonce
{

	@PersistenceContext(unitName = "core")
	private EntityManager entityManager;

	public DaoAnnonce(){}

	@Override
	public Annonce create(Annonce annonce)
	{
		entityManager.persist(annonce);
		return annonce;
	}

	@Override
	public Annonce update(Annonce annonce)
	{
		entityManager.merge(annonce);
		return annonce;
	}

	@Override
	public void delete(Integer id_publi)
	{
		Annonce annonce = entityManager.find(Annonce.class, id_publi);
		entityManager.remove(annonce);
	}

	@Override
	public Annonce getAnnonceById(Integer id_publi)
	{
		return entityManager.find(Annonce.class, id_publi);
	}
}
