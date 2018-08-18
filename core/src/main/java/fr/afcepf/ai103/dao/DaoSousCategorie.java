package fr.afcepf.ai103.dao;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.afcepf.ai103.data.SousCategorie;


@Stateless
@Local
public class DaoSousCategorie implements IDaoSousCategorie {

	@PersistenceContext(unitName="core")
	private EntityManager entityManager;
	

	public DaoSousCategorie(){}
	
	@Override
	public List<SousCategorie> getAllSousCategories()
	{
		List<SousCategorie> sousCategories = entityManager.createNamedQuery("SousCategorie.findAll",SousCategorie.class).getResultList();
		return sousCategories;	
	}
	
	@Override
	public List<SousCategorie> getSousCategoriebyIDCategorie(int id_cat)
	{
		return entityManager.createQuery("select souscat from SousCategorie souscat where souscat.categorie.idCat = :id_cat",SousCategorie.class)
				.setParameter("id_cat", id_cat)
				.getResultList(); 
	}
}

