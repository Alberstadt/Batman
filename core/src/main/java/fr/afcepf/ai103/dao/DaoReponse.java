package fr.afcepf.ai103.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Reponse;
import fr.afcepf.ai103.data.Utilisateur;


@Stateless
@Local
public class DaoReponse implements IDaoReponse
{
	@PersistenceContext(unitName = "core")
	private EntityManager entityManager;
	
	public DaoReponse(){}
	
	
	
	@Override
	public Reponse create(Reponse reponse)
	{
		entityManager.persist(reponse);
		return reponse;
	}

	@Override
	public Reponse update(Reponse reponse)
	{
		entityManager.merge(reponse);
		return reponse;
	}

	@Override
	public void delete(Integer id_reponse)
	{
		Reponse reponse = entityManager.find(Reponse.class, id_reponse);
		entityManager.remove(reponse);
	}

	
	@Override
	public List<Date> getDateTransByStockId(Integer id_prod_stock)
	{
		return entityManager.createQuery("SELECT rep.dateTransaction FROM Reponse rep WHERE rep.dateTransaction IS NOT NULL AND rep.annonce.stock.idProdStock = :id_prod_stock", Date.class)
				.setParameter("id_prod_stock", id_prod_stock)
				.getResultList();
	}
	
	@Override
	public int getNombreReponseByIdPubli(Integer id_publi)
	
	{
		return ((Long) entityManager.createQuery("SELECT COUNT (rep.idReponse) FROM Reponse rep WHERE rep.dateDemande IS NOT NULL AND rep.dateAnnulation IS NULL AND rep.annonce.idPubli = :id_publi")
                .setParameter("id_publi", id_publi).getSingleResult()).intValue();
		
	}
	
	@Override
	public List<Reponse> getListeReponseByIdPubli(Integer id_publi)
	{
		return entityManager.createQuery("select rep from Reponse rep where rep.annonce.idPubli = :id_publi ", Reponse.class)
				.setParameter("id_publi", id_publi).getResultList();
		
	}

	@Override
	public List<Reponse> reponseByUser(Utilisateur user)
	{
		return entityManager.createQuery("select rep from Reponse rep where rep.utilisateur = :user", Reponse.class)
				.setParameter("user", user)
				.getResultList();
	}
	
	
	
	@Override
	public List<Reponse> reponseAutreUser()
	{
		return entityManager.createQuery("select rep from Reponse rep ", Reponse.class)
				.getResultList();
	}
	
	
	
	@Override
	public Reponse getReponseByUser(Utilisateur user)
	{
		return entityManager.find(Reponse.class, user);
	}
	
	
	
	@Override
	public Reponse ajouterReponseAnnonce(Reponse reponse) throws Exception
	{
		try
		{
			entityManager.persist(reponse);
			
		}catch (Exception e)
		{
			throw new Exception("Impossible d'envoyer une demande pour cette annonce !");
		}
		
		return reponse;

	}
	
}
