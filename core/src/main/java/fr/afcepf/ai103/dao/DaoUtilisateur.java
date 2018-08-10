package fr.afcepf.ai103.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Utilisateur;

@Stateless
@Local
public class DaoUtilisateur implements IDaoUtilisateur 
{
	@PersistenceContext(unitName="core")
	private EntityManager entityManager;
	
	public DaoUtilisateur(){}
	
	@Override
	public Utilisateur getUtilisateurByIDUser(int id_user)
	{
		return entityManager.find(Utilisateur.class, id_user);
	}

	@Override
	public Utilisateur verifierMotDePasse (String Pseudo)
	{
		Utilisateur u = null;
		try {
			u = entityManager.createQuery("SELECT p FROM Utilisateur p WHERE p.login = :Pseudo", Utilisateur.class).setParameter("Pseudo", Pseudo).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	@Override
	public Utilisateur getUserById(Integer id_user)
	{
		return entityManager.find(Utilisateur.class, id_user);
	}
	
	@Override
	public Utilisateur create(Utilisateur u)
	{
		System.out.println("methode inscription() DaoUtilisateur");

		entityManager.persist(u);
		return u;
	}
}
