package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import fr.afcepf.ai103.data.Produit;
import fr.afcepf.ai103.data.Stock;

@Stateless
@Local
public class DaoStock implements IDaoStock
{

	@PersistenceContext(unitName = "core")
	private EntityManager entityManager;

	public DaoStock() {}

	@Override
	public Stock getStockById(Integer id_prod_stock)
	{
		return entityManager.find(Stock.class, id_prod_stock);
	}

	@Override
	public List<Stock> getStockByUserId(Integer id_user)
	{
		return entityManager.createQuery("SELECT st FROM Stock st WHERE st.utilisateur.id_user = :id_user", Stock.class)
				.setParameter("id_user", id_user)
				.getResultList();
	}

}
