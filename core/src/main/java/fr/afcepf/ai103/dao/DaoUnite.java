package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Conservation;
import fr.afcepf.ai103.data.Unite;

@Stateless
@Local

public class DaoUnite implements IDaoUnite {

	@PersistenceContext(unitName = "core")
	private EntityManager entityManager;

	public DaoUnite() {
		
	}
	
	@Override
	public List<Unite> getAllUnite() {
		
		return entityManager.createNamedQuery("Unite.findAll", Unite.class).getResultList();
	}
	
	@Override
	public Unite GetUniteByIDUnite (int id_unite)
	{
		return entityManager.find(Unite.class, id_unite);	 	
	}
	
}
