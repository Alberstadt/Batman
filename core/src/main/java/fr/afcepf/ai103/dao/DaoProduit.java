package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Produit;

@Stateless
@Local

public class DaoProduit implements IDaoProduit {

	@PersistenceContext(unitName="core")
	private EntityManager entityManager;

	public DaoProduit(){}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	@Override
	public List<Produit> GetProduitbyIDSousCategorie (int id_sous_cat)
	{
		
		return entityManager.createQuery("select pdt from Produit pdt where pdt.sousCategorie.id_sous_cat = :id_sous_cat",Produit.class)
				.setParameter("id_sous_cat", id_sous_cat)
				.getResultList();
		 	
	}
	
	@Override
	public Produit GetProduitbyIDProduit (int id_prod)
	{
		
		return entityManager.find(Produit.class, id_prod); 
		 	
	}
	
	@Override
	public List<Produit> listeProduitByIdUtilisateur(int id_user) {
		
		return entityManager.createQuery("select pr from Stock st, Produit pr where pr.id_prod = st.produit.id_prod and st.utilisateur.id_user = :id_user" , Produit.class )
				.setParameter("id_user", id_user)
				.getResultList();
		}
	
					
}
