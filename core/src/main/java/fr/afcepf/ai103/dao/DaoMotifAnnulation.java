package fr.afcepf.ai103.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.afcepf.ai103.data.MotifAnnulation;

@Stateless
@Local
public class DaoMotifAnnulation implements IDaoMotifAnnulation 
{
	
	@PersistenceContext(unitName="core")
	private EntityManager entityManager;
	
	public DaoMotifAnnulation() {};
	
	@Override
	public MotifAnnulation GetMotifAnnulationByIdMotifAnnul(Integer id_motif_annul)
	{
		return entityManager.find(MotifAnnulation.class,id_motif_annul);	 	
	}

}
