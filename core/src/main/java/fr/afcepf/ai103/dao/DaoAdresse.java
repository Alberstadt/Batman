package fr.afcepf.ai103.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Adresse;

@Stateless
@Local
public class DaoAdresse implements IDaoAdresse {
	
	@PersistenceContext(unitName="core")
	private EntityManager em;
	
	public DaoAdresse(){}
	
	@Override
	public Adresse create(Adresse adresse)
	{
		em.persist(adresse);
		return adresse;
	}
	

	@Override
	public Adresse update(Adresse adresse)
	{
		em.merge(adresse);
		return adresse;
	}
	

	@Override
	public void delete(Integer id)
	{
		Adresse adresse = getAdresseById(id);
		em.remove(adresse);
	}
	
    @Override
	public Adresse getAdresseById(Integer id_adresse) {
		return em.find(Adresse.class, id_adresse);
	}
}