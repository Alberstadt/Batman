package fr.afcepf.ai103.dao;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.afcepf.ai103.data.Conservation;


@Stateless
@Local
public class DaoConservation implements IDaoConservation 
{
	@PersistenceContext(unitName="core")
	private EntityManager entityManager;

	public DaoConservation(){}
	
	@Override
	public List<Conservation> getAllConservation()
	{
		return entityManager.createNamedQuery("Conservation.findAll",Conservation.class).getResultList();	
	}
	
	@Override
	public Conservation GetConservationByIDConservation (int id_conserv)
	{
		return entityManager.find(Conservation.class, id_conserv);	 	
	}
}
