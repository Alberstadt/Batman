package fr.afcepf.ai103.dao;

import java.util.List;
import java.util.Date;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai103.data.Consommation;

@Stateless
@Local
public class DaoConsommation implements IDaoConsommation
{

	@PersistenceContext(unitName = "core")
	private EntityManager em;

	// constructeur vide
	public DaoConsommation(){}

	@Override
	public List<Consommation> listeProduitConsommerByIdUtilisateur(int id_user)
	{

		return em.createQuery("select conso from Consommation conso where conso.stock.utilisateur.id_user = :id_user ",
				Consommation.class).setParameter("id_user", id_user).getResultList();
	}

	@Override
	public Consommation create(Consommation c)
	{
		em.persist(c);
		return c;
	}

	@Override
	public Double getQuantiteConsoById(Integer id_prod_stock)
	{
		List<Consommation> list;
		try
		{
			list = em.createQuery("SELECT c FROM Consommation c WHERE stock.id_prod_stock = :id_prod_stock",
					Consommation.class).setParameter("id_prod_stock", id_prod_stock).getResultList();
		} catch (Exception e)
		{
			list = null;
		}
		Double quantiteConsommee = 0.00;

		if (list == null)
		{
		} 
		else
		{
			for (Consommation conso : list)
			{
				quantiteConsommee = quantiteConsommee + (Double) conso.getQte_conso();

			}
		}
		return quantiteConsommee;
	}
}
