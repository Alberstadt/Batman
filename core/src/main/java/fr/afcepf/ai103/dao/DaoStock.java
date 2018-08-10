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

	public DaoStock()
	{
	}

	@Override
	public List<Stock> listeStockTotalByIdUtilisateur(int id_user)
	{
		return entityManager.createQuery("select st from Stock st where st.utilisateur.id_user = :id_user ", Stock.class)
				.setParameter("id_user", id_user).getResultList();
	}

	@Override
	public Stock ajouterProduit(Stock s) throws Exception
	{
		try
		{
			entityManager.persist(s);
		} catch (Exception e)
		{
			throw new Exception("Impossible d'ajouter le produit");
		}
		return s;
	}

	@Override
	public String recupererProduit(Integer id_prod)
	{
		return entityManager
				.createQuery("SELECT prod FROM Produit prod INNER JOIN prod.stock st WHERE prod.id_prod = :id_prod",
						Produit.class)
				.setParameter("id_prod", id_prod).getSingleResult().getLibelle_prod();
	}

	@Override
	public Stock getStockById(Integer id_prod_stock)
	{
		return entityManager.find(Stock.class, id_prod_stock);
	}

	@Override
	public List<Stock> getStockByUserId(Integer id_user)
	{
		return entityManager.createQuery("SELECT st FROM Stock st WHERE st.utilisateur.id_user = :id_user", Stock.class)
				.setParameter("id_user", id_user).getResultList();
	}

	@Override
	public List<Stock> getAll()
	{
		return entityManager.createNamedQuery("Stock.findAll", Stock.class).getResultList();
	}

	@Override
	public Double getQuantiteById(Integer id_prod_stock, Integer id_user)
	{
		Stock prod = entityManager
				.createQuery("SELECT s FROM Stock s WHERE s.id_prod_stock = :id_prod_stock", Stock.class)
				.setParameter("id_prod_stock", id_prod_stock).getSingleResult();
		return prod.getQte_initiale();
	}
}
