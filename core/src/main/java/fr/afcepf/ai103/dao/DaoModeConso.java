package fr.afcepf.ai103.dao;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.afcepf.ai103.data.ModeConso;

@Stateless
@Local
public class DaoModeConso implements IDaoModeConso 
{
	@PersistenceContext(unitName="core")
	private EntityManager em;
	
	public DaoModeConso(){}
	
	@Override
	public ModeConso create(ModeConso m)
	{
		em.persist(m);
		return m;
	}
	
	@Override
	public ModeConso update(ModeConso m)
	{
		em.merge(m);
		return m;
	}
	
	@Override
	public void delete(Integer id)
	{
		ModeConso m = getModeConsoById(id);
		em.remove(m);
	}
	
	@Override
	public ModeConso getModeConsoById(Integer id)
	{
		return em.createQuery("SELECT m FROM ModeConso m WHERE m.id_mode= :id", ModeConso.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public List<ModeConso> getAll()
	{
		return em.createNamedQuery("ModeConso.findAll", ModeConso.class).getResultList();
	}

	@Override
	public EntityManager getEm() {
		return em;
	}

	@Override
	public void setEm(EntityManager em) {
		this.em = em;
	}
}
