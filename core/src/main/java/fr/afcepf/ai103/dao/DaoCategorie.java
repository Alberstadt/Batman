package fr.afcepf.ai103.dao;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.afcepf.ai103.data.Categorie;

@Stateless
@Local
public class DaoCategorie implements IDaoCategorie
{

	@PersistenceContext(unitName = "core")
	private EntityManager entityManager;

	public DaoCategorie(){}

	@Override

	public List<Categorie> getAllCategorie()
	{
		return entityManager.createNamedQuery("Categorie.findAll", Categorie.class).getResultList();
	}
}
