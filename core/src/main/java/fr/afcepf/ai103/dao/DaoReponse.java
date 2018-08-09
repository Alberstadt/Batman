package fr.afcepf.ai103.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Reponse;

@Stateless
@Local
public class DaoReponse implements IDaoReponse
{
	@PersistenceContext(unitName = "core")
	private EntityManager entityManager;
	
	public DaoReponse() {}
	
	@Override
	public List<Date> getDateTransByStockId(Integer id_prod_stock)
	{
		return entityManager.createQuery("SELECT rep.date_transaction FROM Reponse rep WHERE rep.date_transaction IS NOT NULL AND rep.annonce.stock.id_prod_stock = :id_prod_stock", Date.class)
				.setParameter("id_prod_stock", id_prod_stock)
				.getResultList();
	}
}
