package fr.afcepf.ai103.dao;

import java.util.List;

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
	
	
	@Override
	public List<Annonce> getAnnonceByUserId(int id_user)
	{
		return entityManager.createQuery("select annonce from Annonce annonce where annonce.dateRetrait IS NULL AND annonce.utilisateur.idUser = :id_user ", Annonce.class)
				.setParameter("id_user", id_user).getResultList();
	}
	
	
	
}
